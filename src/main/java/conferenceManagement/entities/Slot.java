package conferenceManagement.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Slot {
	private List<Event> events;
    private int remainingTime;
    private Calendar startTime;

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
        this.remainingTime -= event.getDurationInMinutes();
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Slot(int duration, Calendar startTime){
        events = new ArrayList<Event>();
        this.remainingTime = duration;
        this.startTime = startTime;
    }

    // check if the talk can be accommodated in the current slot.
    public boolean hasTimeFor(Talk talk) {
        return remainingTime >= talk.getDurationInMinutes();
    }

}
