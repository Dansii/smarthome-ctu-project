package cz.cvut.fel.omo.SmartHome.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Event report
 *
 */
public class EventReport extends AbstractReport {
    private List<Event> events;


    public EventReport() {
        events = new ArrayList<>();
    }


    /**
     * Generates a string that contains information about all events in house
     * @return string with report
     */
    public String report(){
        StringBuilder sb = new StringBuilder();

        if(events.isEmpty()){
            sb.append("There is no events");
        } else {

            for (Event e : events) {
                sb.append("  ").append(e.toString()).append("\n");
            }
        }
        return sb.toString();
    }



    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void accept(ReportGenerator generator) {
        generator.generateEventReport(this);
    }

}
