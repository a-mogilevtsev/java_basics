/**
 * Created by a.sosnina on 1/4/2022.
 */
public class Course {

    private int id;
    private String name;

    public Course(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
