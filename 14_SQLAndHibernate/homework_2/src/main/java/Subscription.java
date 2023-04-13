import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by a.sosnina on 1/8/2022.
 */
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey key;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Override
    public String toString() {
        return "Subscription{" +
                "studentId = " + studentId +
                ", courseId = " + courseId +
                ", subscriptionDate = " + subscriptionDate +
                '}';
    }
}
