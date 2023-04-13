import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

/**
 * Created by a.sosnina on 1/5/2022.
 */
public class Main {
    private static SessionFactory sessionFactory;
    private static Session session;
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();

        gettingTeachersList();

        gettingCourseFromDbTest();

        gettingSubscriptionFromDbTest();

        gettingPurchaseFromDbTest();

        createTable();

        session.close();

    }

    public static void gettingTeachersList() {
        //Проверка получения списка учителей из базы
        /*List<Teacher> teachers = session.createCriteria(Teacher.class).list();
        teachers.forEach(System.out::println);*/
        System.out.println("\nПроверка получения списка учителей из базы");

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);
        query.select(root);
        List<Teacher> teachersList = session.createQuery(query).getResultList();
        teachersList.forEach(System.out::println);

        //Проверка получения списка курсов учителя
        Teacher teacher = session.get(Teacher.class, 20);
        System.out.println(teacher);
        System.out.println(teacher.getCourses());

    }

    public static void gettingCourseFromDbTest() {
        System.out.println("\nПроверка получения курса из базы и впоследствии получения списка студентов и преподавателя при помощи связей таблиц\n");
        Course course = session.get(Course.class, 42);
        System.out.println("Teacher is - " + course.getTeacher().getName());
        course.getStudents().forEach(System.out::println);
    }

    public static void gettingSubscriptionFromDbTest(){
        System.out.println("\nПроверка получения объекта соответствующего подписке при помощи составного ключа");
        Subscription subscription = session.get(Subscription.class, new SubscriptionKey(3, 12));
        System.out.println("Подписка - " + subscription.toString());
        Student student = session.get(Student.class, 17);
        //Проверка связей. Получение списка курсов у студента
        System.out.println("У студента следующие курсы :" + student.getCourses());
    }


    public static void gettingPurchaseFromDbTest(){
        System.out.println("\nПроверка получения данных из таблицы purchase");
        String stName = "Жариков Афанасий";
        String courseName = "Веб-разработчик c 0 до PRO";
        Purchase purchase = session.get(Purchase.class, new PurchaseKey(stName, courseName));
        System.out.println(purchase.getPrice());
    }

    public static void createTable() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);
        List<Purchase> purchaseList = session.createQuery(query).getResultList();

        session.beginTransaction();
        for(Purchase purchase:purchaseList) {
            String hqlStudent = String.format("From %s where name = '%s'", Student.class.getSimpleName(),  purchase.getStudentName());
            Student student = (Student) session.createQuery(hqlStudent).getSingleResult();
            String hqlCourse = String.format("From %s where name = '%s'", Course.class.getSimpleName(),  purchase.getCourseName());
            Course course = (Course) session.createQuery(hqlCourse).getSingleResult();
            int stId = student.getId();
            int courseId = course.getId();
            LinkedPurchase linkedPurchase = new LinkedPurchase(new LinkedPurchaseKey(stId,courseId));
            session.saveOrUpdate(linkedPurchase);
        }
        session.getTransaction().commit();

    }
}
