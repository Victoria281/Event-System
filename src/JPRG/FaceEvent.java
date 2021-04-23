

package JPRG;

import java.time.LocalDateTime;
import java.io.*;

public class FaceEvent extends Event implements Serializable{
    private int participants;
    private int limit;
    
    public FaceEvent() {
    }

    public FaceEvent(String name, String organiser, double fees, LocalDateTime dateTime, double timePeriod, int limit, int participants) {
        super(name, organiser, fees, dateTime, timePeriod);
        this.participants = participants;
        this.limit = limit;
    }
    public int getParticipants(){
        return this.participants;
    }
    
    public int getLimit(){
        return this.limit;
    }
        
    public void setParticipants(int participants){
        this.participants = participants;
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
    
    public boolean addParticipants(){
        boolean added = true;
        if (this.participants < this.limit){
            this.participants+=1;
        }
        else {
            added = false;
        }
        return added;
    }
    
    
    public void removeParticipants(){
        this.participants-=1;
    }
    
    public String eventType(){
        return "Offline";
    }
    
    public String eventInfo(){
        String output = "This is a Face to Face Event. The number of participants is limited to "+ this.limit +"\nPlease wear your masks and maintain proper social distancing.";
        return output;
    }
}
