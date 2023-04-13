import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import com.mongodb.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Created by a.sosnina on 3/30/2022.
 */
public class CommandReader {

    private static MongoCollection<Document> shopsCollection;
    private static MongoCollection<Document> productsCollection;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void main(String[] args) {
        createConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean exitFlag = false;
            while(!exitFlag) {
                String line = bf.readLine();
                exitFlag = process(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createConnection() {
        mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        database = mongoClient.getDatabase("local");

        shopsCollection = database.getCollection("shops");
        productsCollection = database.getCollection("products");
    }

    public static boolean process(String line) {
        boolean flag = false;
        if(line.toUpperCase().startsWith("ДОБАВИТЬ_МАГАЗИН")) addShop(line.substring(16));
        else if(line.toUpperCase().startsWith("ДОБАВИТЬ_ТОВАР")) addProduct(line.substring(14));
        else if(line.toUpperCase().startsWith("ВЫСТАВИТЬ_ТОВАР")) putProduct(line.substring(15));
        else if(line.toUpperCase().startsWith("ВЫХОД")) flag = true;
        else if(line.toUpperCase().startsWith("СТАТИСТИКА_ТОВАРОВ")) getInfo();
        return flag;
    }

    public static void addShop(String shopName) {
        List<String> products = new ArrayList();
        Document shop = new Document()
                .append("name", shopName)
                .append("products", products);
        shopsCollection.insertOne(shop);
    }

    public static void addProduct(String productInfo) {
        int index = 0;
        while (!Character.isDigit(productInfo.charAt(index))) index++;
        String productName = productInfo.substring(0,index);
        int price = Integer.parseInt(productInfo.substring(index));
        Document product = new Document()
                .append("name", productName)
                .append("price", price);
        productsCollection.insertOne(product);
    }

    public static void putProduct(String info) {
        int index = 1;
        while (!Character.isUpperCase(info.charAt(index))) {
            index++;
        }
        String product = info.substring(0, index);
        String shop = info.substring(index);
        String req = String.format("{name: \"%s\"}", shop);
        BsonDocument query = BsonDocument.parse(req);
        for(Document doc : shopsCollection.find(query)) {
            ObjectId id = (ObjectId) doc.get("_id");
            Bson filter = eq("_id", id);
            Bson update = Updates.push("products", product);
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                    .returnDocument(ReturnDocument.AFTER);
            Document result = shopsCollection.findOneAndUpdate(filter, update, options);
            System.out.println(result.toJson());
        }
    }

    public static void getInfo() {
        System.out.println("Количество товаров в магазине:");
        countProducts();
        System.out.println("\nМинимальная максимальная и средняя стоимость товаров:");
        countAvgMinMax();
        System.out.println("\nКоличество товаров дешевле 100 рублей:");
        countLessThen100();
    }

    public static void countProducts() {
        AggregateIterable<Document> result = shopsCollection.aggregate(
                Collections.singletonList(
                        project(
                                Projections.fields(
                                        Projections.excludeId(),
                                        Projections.include("name"),
                                        Projections.computed(
                                                "count",
                                                new Document("$size", "$products"))
                                )
                        )
                ));
        for(Document doc : result) {
            System.out.println(doc);
        }
    }


    public static void countLessThen100() {
        AggregateIterable<Document> output = shopsCollection.aggregate(Arrays.asList(
                unwind("$products"),
                lookup("products", "products", "name", "info"),
                unwind("$info")
        ));
        Map<String, Integer> map = new HashMap<>();
        for(Document doc : output) {
            Document info = (Document) doc.get("info");
            String name = (String) doc.get("name");
            int price = (int)info.get("price");
            if(price < 100) {
                int count = map.containsKey(name) ? map.get(name) : 0;
                map.put(name, count + 1);
            }
        }
        for(String shopName : map.keySet()) {
            System.out.println(shopName + " - " + map.get(shopName));
        }
    }


    public static void countAvgMinMax() {
            AggregateIterable<Document> prices = shopsCollection.aggregate(Arrays.asList(
                    unwind("$products"),
                    lookup("products", "products", "name", "info"),
                    unwind("$info"),
                    group("$name",
                            min("minPrice", "$info.price"),
                            max("maxPrice", "$info.price"),
                            avg("avgPrice", "$info.price")),
                    out("prices")));
            for (Document doc : prices) {
                System.out.println(doc);
            }
        }
}
