package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Test {

    private static MongoCollection<Document> collection;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void main(String[] args) {
        String path = "c:\\projects\\skillbox\\java_basics\\17_NoSQL\\mongo.csv";
        List<Student> students = new CsvFileReader(path).parseFile();

        createConnection();
        insertDataToDB(students);
        readDataFromDB();

        getInfoFromDB();

    }

    public static void createConnection() {
        mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        collection = database.getCollection("students");
        // Удалим из нее все документы
        collection.drop();
    }

    public static void insertDataToDB (List<Student> students) {
        for(Student student : students) {
            Document studentBean = new Document()
                    .append("name", student.getName())
                    .append("age", student.getAge())
                    .append("courses", student.getCourses());
            collection.insertOne(studentBean);
        }
    }

    public static void readDataFromDB() {
        collection.find().forEach((Consumer<Document>) document -> {
            System.out.println(document);
        });
    }

    public static void getInfoFromDB() {

        //общее количество студентов в базе.
        long count = collection.countDocuments();
        System.out.println("общее количество студентов в базе - " + count);

        //— количество студентов старше 40 лет.
        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        AtomicInteger c = new AtomicInteger();
        System.out.println("Студенты старше 40 лет: ");
        collection.find(query).forEach((Consumer<Document>) document -> {
                    System.out.println(document);
                    c.getAndIncrement();
        });
        System.out.print("Количество студентов старше 40 лет - ");
        System.out.println(c.get());


        //— имя самого молодого студента.
        BsonDocument queryYoungest = BsonDocument.parse(("{age: 1}"));
        System.out.print("Самый молодой студент: ");
        collection.find().sort(queryYoungest).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println(document.getString("name"));});

        //— список курсов самого старого студента.
        BsonDocument queryOldest = BsonDocument.parse(("{age: -1}"));
        System.out.print("Курсы самого возрастного студента: ");
        collection.find().sort(queryOldest).limit(1).forEach((Consumer<Document>) document -> {
            List<String> courses = (ArrayList<String>)document.get("courses");
            System.out.println(courses);});
        }
}
