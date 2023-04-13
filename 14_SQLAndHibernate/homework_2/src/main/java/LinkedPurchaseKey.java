import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by a.sosnina on 1/12/2022.
 */
@Embeddable
public class LinkedPurchaseKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_Id")
    private int courseId;


    public LinkedPurchaseKey() {
    }

    public LinkedPurchaseKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedPurchaseKey)) return false;
        LinkedPurchaseKey that = (LinkedPurchaseKey) o;
        return studentId == that.studentId &&
                courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
