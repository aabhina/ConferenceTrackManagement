package conferenceManagement.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConferenceManagementConstants {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm a");

    public static final String LIGHTNING_TALK = "lightning";

    
    public static final int MORNING_SLOT_DURATION_MINUTES = 180;
    public static final int AFTERNOON_SLOT_DURATION_MINUTES = 240;

    public static final int LIGHTNING_TALK_DURATION_MINUTES = 5;

    public static Calendar TRACK_START_TIME = ConferenceUtils.getCalendarTime(9, 0);
    public static Calendar LUNCH_START_TIME = ConferenceUtils.getCalendarTime(12, 0);
    public static Calendar POST_LUNCH_SLOT_START_TIME = ConferenceUtils.getCalendarTime(13, 0);
    public static Calendar NETWORKING_START_TIME = ConferenceUtils.getCalendarTime(17, 0);

    public static final int LUNCH_DURATION_MINUTES = 60;
    public static final int NETWORKING_DURATION_MINUTES = 60;

}
