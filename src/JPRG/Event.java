

package JPRG;
import java.time.LocalDateTime;
import java.io.*;

public class Event implements Serializable{
    String name;
    String organiser;
    double fees;
    LocalDateTime dateTime;
    double timePeriod;
    int limit;
    int participants;
    
    public Event(){
    }
    
    public Event(String name, String organiser, double fees, LocalDateTime dateTime, double timePeriod){
        this.name = name;
        this.organiser = organiser;
        this.fees = fees;
        this.dateTime = dateTime;
        this.timePeriod = timePeriod;
        this.limit = 0;
        this.participants = 0;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getOrganiser(){
        return this.organiser;
    }
    
    public double getFees(){
        return this.fees;
    }
    
    public LocalDateTime getDateTime(){
        return this.dateTime;
    }
    public double getTimePeriod(){
        return this.timePeriod;
    }
    
    public int getParticipants(){
        return this.participants;
    }
    
    public int getLimit(){
        return this.limit;
    }
    
    public void setParticipants(int participants){
    }
    
    public void setLimit(int limit){
    }
    
    public boolean addParticipants(){
        return true;
    }
    
    
    public void removeParticipants(){
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setOrganiser(String organiser){
        this.organiser = organiser;
    }
    
    public void setFees(double fees){
        this.fees = fees;
    }
    
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    
    public void setTimePeriod(double timePeriod){
        this.timePeriod = timePeriod;
    }
    
    public String eventType(){
        return "Event";
    }
    
    public String eventInfo(){
        String created = "I am an event.";
        return created;
    }
     
}
