import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod> {

    private long from;
    private long to;
    private final static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private final static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private final static String illegalArgExcMess = "Dates 'from' and 'to' must be within ONE day!";

    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to) {
        this.from = from;
        this.to = to;
        if (!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to)))) {
            throw new IllegalArgumentException(illegalArgExcMess);
        }
    }

    public TimePeriod(Date from, Date to) {
        this.from = from.getTime();
        this.to = to.getTime();
        if (!dayFormat.format(from).equals(dayFormat.format(to))) {
            throw new IllegalArgumentException(illegalArgExcMess);
        }
    }

    public void appendTime(Date visitTime) {

        if (visitTime.getDay() != new Date(from).getDay()) {
            throw new IllegalArgumentException(illegalArgExcMess);
        }
        long visitTimeTs = visitTime.getTime();
        if (visitTimeTs < from) {
            from = visitTimeTs;
        }
        if (visitTimeTs > to) {
            to = visitTimeTs;
        }
    }

    public String toString() {
        String from = dateFormat.format(this.from);
        String to = timeFormat.format(this.to);
        StringBuilder result = new StringBuilder();
        result.append(from);
        result.append("-");
        result.append(to);
        return result.toString();
    }

    @Override
    public int compareTo(TimePeriod period) {
        Date current = new Date();
        Date compared = new Date();
        try {
            current = dayFormat.parse(dayFormat.format(new Date(from)));
            compared = dayFormat.parse(dayFormat.format(new Date(period.from)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return current.compareTo(compared);
    }
}
