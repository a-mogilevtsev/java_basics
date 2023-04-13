import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Voter {

    private String name;
    private short[] birthDay;

    public Voter(String name, short[] birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter)) return false;
        Voter voter = (Voter) o;
        return name.equals(voter.name) &&
                Arrays.equals(birthDay, voter.birthDay);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(birthDay);
        return result;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "name='" + name + '\'' +
                ", birthDay=" + Arrays.toString(birthDay) +
                '}';
    }

    public String getName() {
        return name;
    }

    public short[] getBirthDay() {
        return birthDay;
    }
}
