import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "graveonmysql";
    private static StringBuilder insertQuery = new StringBuilder();
    private static long count = 0;
    private Object lock;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    //"id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL)");
                    //"PRIMARY KEY name_date(name(50), birthDate))");
                //        "UNIQUE KEY name_date(name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void appendToInsertQuery(String name, String birthDay) throws SQLException {
        boolean isFirst = insertQuery.length() == 0;
        insertQuery.append(isFirst ? "('" : ", ('").append(name).append("', '").append(birthDay).append("', 1)");
        count++;
        if(count == 10000) {
            executeMultiInsert(insertQuery.toString());
            insertQuery = new StringBuilder();
            count = 0;
        }
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, count) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE count = count + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }


    public static void executeMultiInsert(String query) throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, count) " +
                "VALUES" + query;
                //"ON DUPLICATE KEY UPDATE count = count + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, count FROM voter_count WHERE count > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }
}
