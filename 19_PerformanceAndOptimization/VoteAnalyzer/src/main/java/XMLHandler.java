import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by a.sosnina on 4/11/2022.
 */
public class XMLHandler extends DefaultHandler{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                String birthday = attributes.getValue("birthDay");
                birthday.replace(".", "-");
                DBConnection.appendToInsertQuery(name, birthday);
            } else if(qName.equals("visit")) {
                int station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (Exception e) {

        }
    }

    public static HashMap<Voter, Integer> getVoterCounts() {
        return voterCounts;
    }

    public short[] getBirthdayInArray(String birthday) {
        short[] array =  new short[3];
        byte i = 0;
        String[] birthdayParts = birthday.split("\\.");
        for(String part : birthdayParts ) {
            array[i] = Short.parseShort(part);
            i++;
        }
        return array;
    }

    public static String getBirthDayToSQL(short[] birthDay) {
        StringBuilder date = new StringBuilder();
        date.append(birthDay[0]).append("-").append(birthDay[1]).append("-").append(birthDay[2]);
        return date.toString();
    }

    public static HashMap<Integer, WorkTime> getVoteStationWorkTimes() {
        return voteStationWorkTimes;
    }
}
