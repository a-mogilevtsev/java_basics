import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by a.sosnina on 1/11/2022.
 */
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey key;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_Id", insertable = false, updatable = false)
    private int courseId;


    public LinkedPurchase() {
    }

    public LinkedPurchase(LinkedPurchaseKey key) {
        this.key = key;
        this.studentId = key.getStudentId();
        this.courseId = key.getCourseId();
    }

    public LinkedPurchase(LinkedPurchaseKey key, int studentId, int courseId) {
        this.key = key;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public LinkedPurchaseKey getKey() {
        return key;
    }

    public void setKey(LinkedPurchaseKey key) {
        this.key = key;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
