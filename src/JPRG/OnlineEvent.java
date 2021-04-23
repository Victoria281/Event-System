
package JPRG;

import java.time.LocalDateTime;
import java.io.*;
public class OnlineEvent extends Event implements Serializable{

    public OnlineEvent() {
    }

    public OnlineEvent(String name, String organiser, double fees, LocalDateTime dateTime, double timePeriod) {
        super(name, organiser, fees, dateTime, timePeriod);
    }
    
    public String eventType(){
        return "Online";
    }
    
    public String eventInfo(){
        String output = "This is an Online Event. The number of participants is unlimited";
        return output;
    }
}
