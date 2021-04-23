/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2004051
 * Name: Amanda Quek Yan Ling
 */
package JPRG;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class EventManagement {

    private Event[] eventObj;
    private FileInputandOutput files = new FileInputandOutput();

    public EventManagement() {
        int read = JOptionPane.showOptionDialog(new JFrame(), "Read from new .txt file? If no, read from saved .dat file", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes (.txt)", "No (.dat)"}, JOptionPane.YES_OPTION);
        if (read == JOptionPane.YES_OPTION) {
            this.eventObj = files.readTextFile();
        } else {
            this.eventObj = files.readObject();
        }

    }

    public Event[] getEventObj() {
        return this.eventObj;
    }

    public String displayEvent() {
        String message = "S/N\t" + "Name\t" + "Organiser\t" + "Date/Time\t       " + "Period\t" + "Fees($)\t"+"Event Type\n\n";
        for (int i = 0; i < this.eventObj.length; i++) {
            message += (i + 1) + "\t" + this.eventObj[i].getName() + "\t" + this.eventObj[i].getOrganiser() + "\t" + this.eventObj[i].getDateTime() + "    " + this.eventObj[i].getTimePeriod() + "\t" + String.format("%.2f", this.eventObj[i].getFees())+ "\t" + this.eventObj[i].eventType() +"\n";
        }
        return message;
    }

    public String eventDetails() {
        String message = "";
        for (int i = 0; i < this.eventObj.length; i++) {
            message += this.eventObj[i].getName() + " by " + this.eventObj[i].getOrganiser() + "\n" + this.eventObj[i].eventInfo() + "\n\n";
        }
        return message;
    }

    public String addEvent(String name, String organiser, String dateTime, double timePeriod, double fees, int limit, String type, int participants) {

        boolean duplicated = false;
        for (int i = 0; i < this.eventObj.length; i++) {
            if (this.eventObj[i].name.equals(name)) {
                duplicated = true;
            }
        }
        if (duplicated) {
            JOptionPane.showMessageDialog(null, "Event Name \"" + name + "\" already exists! ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Event[] newEventObj = new Event[this.eventObj.length + 1];
            for (int i = 0; i < this.eventObj.length; i++) {
                newEventObj[i] = this.eventObj[i];
            }
            if (type == "Online") {
                newEventObj[this.eventObj.length] = new OnlineEvent(name, organiser, fees, LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), timePeriod);
            } else {
                newEventObj[this.eventObj.length] = new FaceEvent(name, organiser, fees, LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), timePeriod, limit, participants);
            }
            this.eventObj = newEventObj;
        }
        String message = "You have added an event.\n Name:\t" + this.eventObj[this.eventObj.length - 1].getName() + "\n Organiser:\t" + this.eventObj[this.eventObj.length - 1].getOrganiser() + "\n Date:\t" + this.eventObj[this.eventObj.length - 1].getDateTime() + "\n Period:\t" + this.eventObj[this.eventObj.length - 1].getTimePeriod() + "\n Fees:\t" + String.format("%.2f", this.eventObj[this.eventObj.length - 1].getFees());
        if (type != "Online") {
            message += "\n Limit:\t" + limit;
        }
        message += "\n\n" + this.eventObj[this.eventObj.length - 1].eventInfo();

        return message;

    }

    public String deleteEvent(String deletedName) {
        String message = "Event " + deletedName + " has been deleted.";
        int toDelete = 0;
        boolean found = false;
        for (int i = 0; i < this.eventObj.length; i++) {
            if (this.eventObj[i].name.equals(deletedName)) {
                found = true;
                toDelete = i;
            }
        }

        if (found) {
            Event[] newEventObj = new Event[this.eventObj.length - 1];
            for (int i = 0; i < this.eventObj.length; i++) {
                if (toDelete > i) {
                    newEventObj[i] = this.eventObj[i];
                } else if (toDelete < i) {
                    newEventObj[i - 1] = this.eventObj[i];
                }
            }
            this.eventObj = newEventObj;
        } else {
            JOptionPane.showMessageDialog(null, "Cannot find the event \"" + deletedName + "\" to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            message = "Event " + deletedName + " could not be found.\n No deletion occured.";
        }
        return message;
    }

    public String editEvent(int num, int eventno, String type) {
        String message = "Event number " + (eventno + 1) + " named " + this.eventObj[eventno].getName() + " was edited.\n\n";
        if (num == 1) {
            message += "Event Name was set to " + type;
            this.eventObj[eventno].setName(type);
        } else if (num == 2) {
            message += "Event Organiser was set to " + type;
            this.eventObj[eventno].setOrganiser(type);
        } else if (num == 3) {
            message += "Event DateTime was set to " + type;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(type, formatter);
            this.eventObj[eventno].setDateTime(dateTime);
        } else if (num == 4) {
            message += "Event TimePeriod was set to " + type;
            this.eventObj[eventno].setTimePeriod(Double.parseDouble(type));
        } else if (num == 5) {
            message += "Event Fees was set to " + type;
            this.eventObj[eventno].setFees(Double.parseDouble(type));
        } else if (num == 6) {
            if (this.eventObj[eventno].eventType() == "Online") {
                message += "Event Type was set to Online";
                String[] info = type.split("CHANGE");
                this.eventObj[eventno] = new FaceEvent(this.eventObj[eventno].getName(), this.eventObj[eventno].getOrganiser(), this.eventObj[eventno].getFees(), this.eventObj[eventno].getDateTime(), this.eventObj[eventno].getTimePeriod(), Integer.parseInt(info[0]), Integer.parseInt(info[1]));
            } else {
                message += "Event Type was set to Offline";
                this.eventObj[eventno] = new OnlineEvent(this.eventObj[eventno].getName(), this.eventObj[eventno].getOrganiser(), this.eventObj[eventno].getFees(), this.eventObj[eventno].getDateTime(), this.eventObj[eventno].getTimePeriod());
            }
        } else if (num == 7) {
            message += "Event Limit was set to " + type;
            this.eventObj[eventno].setLimit(Integer.parseInt(type));
        } else if (num == 8) {
            message += "Event Participants was set to " + type;
            this.eventObj[eventno].setParticipants(Integer.parseInt(type));
        }
        return message;
    }

    public String searchEventByName(String name) {
        boolean found = false;
        String message = "";
        for (int i = 0; i < this.eventObj.length; i++) {
            if (this.eventObj[i].name.equals(name)) {
                found = true;
                message = "Name\t" + "Organiser\t" + "Date/Time\t       " + "Period\t" + "Fees($)\t"+"Event Type";
                if(this.eventObj[i].eventType().equals("Offline")){
                    message+= "\tLimit\t" + "Participants" ;
                }
                message += "\n\n" +this.eventObj[i].getName() + "\t" + this.eventObj[i].getOrganiser() + "\t" + this.eventObj[i].getDateTime() + "    " + this.eventObj[i].getTimePeriod() + "\t" + String.format("%.2f", this.eventObj[i].getFees())+ "\t" + this.eventObj[i].eventType();
                if(this.eventObj[i].eventType().equals("Offline")){
                    message+= "\t"+this.eventObj[i].getLimit() + "\t" + this.eventObj[i].getParticipants();
                }
                message += "\n\n" + this.eventObj[i].eventInfo();
                break;
            }
        }

        return message;
    }

    public String searchEventByFees(double fees) {
        String message = "";
        String foundEvents = "S/N\t" + "Name\t" + "Organiser\t" + "Date/Time\t       " + "Period\t" + "Fees($)\t"+"Event Type\n\n";
        boolean found = false;
        for (int i = 0; i < this.eventObj.length; i++) {
            if (this.eventObj[i].fees <= fees) {
                found = true;
                foundEvents += (i + 1) + "\t" + this.eventObj[i].getName() + "\t" + this.eventObj[i].getOrganiser() + "\t" + this.eventObj[i].getDateTime() + "    " + this.eventObj[i].getTimePeriod() + "\t" + String.format("%.2f", this.eventObj[i].getFees())+ "\t" + this.eventObj[i].eventType() +"\n";
            }
        }
        if (!found) {
            foundEvents = "";
        }
        return foundEvents;
    }

    public Event[] registerEvent(String eventToRegister, Event[] eventsRegistered) {
        int toRegister = 0;
        boolean found = false;
        boolean timeConflict = false;
        for (int i = 0; i < this.eventObj.length; i++) {
            if (this.eventObj[i].name.equals(eventToRegister)) {
                found = true;
                toRegister = i;
            }
        }
        if (found) {
            for (int i = 0; i < eventsRegistered.length; i++) {
                LocalDateTime startOfEventToRegister = this.eventObj[toRegister].dateTime;
                LocalDateTime startOfEvents = eventsRegistered[i].dateTime;
                int time = (int) (this.eventObj[toRegister].timePeriod * 60);
                int secondTime = (int) (eventsRegistered[i].timePeriod * 60);
                LocalDateTime endOfEventToRegister = this.eventObj[toRegister].dateTime.plusMinutes((long) time);
                LocalDateTime endOfEvents = eventsRegistered[i].dateTime.plusMinutes((long) secondTime);
                if (!(endOfEventToRegister.isBefore(startOfEvents) || endOfEvents.isBefore(startOfEventToRegister))) {
                    timeConflict = true;
                }

            }

            if (timeConflict) {
                JOptionPane.showMessageDialog(null, "Event Timings Clash! Unable to register for Event!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean canJoin = this.eventObj[toRegister].addParticipants();
                if (canJoin) {
                    Event[] updatedEventsRegistered = new Event[eventsRegistered.length + 1];
                    for (int i = 0; i < eventsRegistered.length; i++) {
                        updatedEventsRegistered[i] = eventsRegistered[i];
                    }
                    updatedEventsRegistered[eventsRegistered.length] = new Event(this.eventObj[toRegister].getName(), this.eventObj[toRegister].getOrganiser(), this.eventObj[toRegister].getFees(), this.eventObj[toRegister].getDateTime(), this.eventObj[toRegister].getTimePeriod());
                    eventsRegistered = updatedEventsRegistered;
                } else {
                    JOptionPane.showMessageDialog(null, "Event Limit has been reached! Unable to register for Event!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot find the event \"" + eventToRegister + "\"!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return eventsRegistered;
    }

    public void saveChanges() {
        files.writeObject(this.eventObj);
    }

}
