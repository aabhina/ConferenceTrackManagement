package conferenceManagement;
import java.util.Calendar;

import org.junit.Test;

import conferenceManagement.entities.Talk;
import conferenceManagement.utilities.ConferenceUtils;
import junit.framework.Assert;

public class TestConferenceUtils {

	@Test
	public void testGetCalendarTime() {
		ConferenceUtils util = new ConferenceUtils();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 30);
        
        Assert.assertEquals(cal.getTime().getHours(), util.getCalendarTime(10,30).getTime().getHours());
        Assert.assertEquals(cal.getTime().getMinutes(), util.getCalendarTime(10,30).getTime().getMinutes());
        
	}
	
	@Test
	public void testGetNextStartTime() {
		ConferenceUtils util = new ConferenceUtils();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 30);
        
        Talk t1 = new Talk("Java Talk", 15);
        
        util.getNextStartTime(cal, t1);
        
        Assert.assertEquals(cal.getTime().getHours(), util.getNextStartTime(cal, t1).getTime().getHours());
        Assert.assertEquals(cal.getTime().getMinutes() + 15, util.getNextStartTime(cal,t1).getTime().getMinutes());
		
	}

}
