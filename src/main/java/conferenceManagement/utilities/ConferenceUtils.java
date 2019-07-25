package conferenceManagement.utilities;

import java.util.Calendar;
import java.util.List;

import conferenceManagement.entities.Talk;

public class ConferenceUtils {

	public static void printTalks(List<Talk> talkList) {		
        System.out.println("Test Input:");
        System.out.println();
        
        for (Talk talk : talkList) {
            System.out.println(talk.getTitle() + " " + talk.getDurationInMinutes());
        }
        
        System.out.println();		
	}

	public static Calendar getCalendarTime(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        
        return cal;
    }

	public static Calendar getNextStartTime(Calendar currentStartTime, Talk talk) {
		Calendar newTime = Calendar.getInstance();
        newTime.set(Calendar.HOUR_OF_DAY, currentStartTime.get(Calendar.HOUR_OF_DAY));
        newTime.set(Calendar.MINUTE, currentStartTime.get(Calendar.MINUTE));
        newTime.add(Calendar.MINUTE, talk.getDurationInMinutes());
        
        return newTime;
	}

}
