/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2004051
 * Name: Amanda Quek Yan Ling
 */
package JPRG;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileInputandOutput {

    public static void writeObject(Event[] allEvents) {
        String directory = System.getProperty("user.dir");
        File f = new File(directory + "\\Event.dat");
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(
                    new FileOutputStream(f));
            outStream.writeObject(allEvents);
            outStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Event[] readObject() {
        String directory = System.getProperty("user.dir");
        File f = new File(directory + "\\Event.dat");
        Event[] eventObjects = new Event[]{
            new OnlineEvent("Dancing", "SP Dance Club", 19.80, LocalDateTime.parse("2021-10-28 09:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 2),
            new FaceEvent("Crafting", "SP Craft Club", 20.80, LocalDateTime.parse("2021-10-28 10:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 3, 20, 0),
            new FaceEvent("Cooking", "SP Cook Club", 5.60, LocalDateTime.parse("2021-10-16 10:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 4, 40, 0),
            new OnlineEvent("Reading", "SP Book Club", 14.30, LocalDateTime.parse("2021-05-07 09:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 2),
            new FaceEvent("Singing", "SP Sing Club", 6.60, LocalDateTime.parse("2021-06-13 09:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 3, 10, 0)
        };
        try {
            ObjectInputStream inStream = new ObjectInputStream(
                    new FileInputStream(f));
            eventObjects = (Event[]) inStream.readObject();
            inStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventObjects;
    }

    public static Event[] readTextFile() {
        String directory = System.getProperty("user.dir");
        BufferedReader br;
        String s;
        int index = 0;
        Event[] events = {};
        try {
            br = new BufferedReader(new FileReader(directory + "\\events.txt"));
            s = br.readLine();
            events = new Event[Integer.parseInt(s)];
            while ((s = br.readLine()) != null) {
                String[] eventObject = s.split(";");
                if (eventObject[0].equals("OnlineEvent")) {
                    events[index] = new OnlineEvent(eventObject[1], eventObject[2], Double.parseDouble(eventObject[3]), LocalDateTime.parse(eventObject[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), Double.parseDouble(eventObject[5]));
                } else {
                    events[index] = new FaceEvent(eventObject[1], eventObject[2], Double.parseDouble(eventObject[3]), LocalDateTime.parse(eventObject[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), Double.parseDouble(eventObject[5]), Integer.parseInt(eventObject[6]), Integer.parseInt(eventObject[7]));
                }

                index++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

}
