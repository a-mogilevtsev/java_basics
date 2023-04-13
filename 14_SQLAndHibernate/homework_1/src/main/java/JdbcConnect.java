import java.sql.*;
import java.util.*;

/**
 * Created by a.sosnina on 1/4/2022.
 */
public class JdbcConnect {
    public static Connection connection;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя");
        String user = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        try {
            connection = DriverManager.getConnection(url, user, password);
            List<Course> courses = getAllCourses();
            calcAvrPurchases(courses);
            closeConnection();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from courses");
        while(rs.next()) {
            String name = rs.getString("name");
            String id = rs.getString("id");
            courses.add(new Course(Integer.parseInt(id), name));
        }
        return courses;
    }

    public static void calcAvrPurchases(List<Course> courses) throws SQLException {
        for(Course c : courses) {
            Statement statement = connection.createStatement();
            String query = String.format("Select count(*) / (month(Max(subscription_date)) - month(min(subscription_date))+1)" +
                    " as coef from subscriptions where course_id = %d;", c.getId());
            ResultSet rs = statement.executeQuery(query);
            String coef = "";
            if(rs.next()) {
                coef = rs.getString("coef");
            }
            System.out.println(c.getName() + " - " + coef);
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }


}
