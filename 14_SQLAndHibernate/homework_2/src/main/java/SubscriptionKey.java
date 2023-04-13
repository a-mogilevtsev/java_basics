import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by a.sosnina on 1/8/2022.
 */

@Embeddable
public class SubscriptionKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_Id")
    private int courseId;


    public SubscriptionKey() {
    }

    public SubscriptionKey(int studentId, int courseId) {
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
        if (!(o instanceof SubscriptionKey)) return false;
        SubscriptionKey that = (SubscriptionKey) o;
        return studentId == that.studentId &&
                courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
