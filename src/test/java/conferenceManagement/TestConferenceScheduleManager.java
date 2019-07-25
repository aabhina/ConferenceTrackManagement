package conferenceManagement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import conferenceManagement.entities.Conference;
import conferenceManagement.entities.Talk;
import junit.framework.Assert;

public class TestConferenceScheduleManager {

	@Test
	public void testParseInputTalksListFile() {
		ConferenceScheduleManager mgr =  new ConferenceScheduleManager();
		
		String inputFile = "/Users/anshul/Documents/workspace/ConferenceTrackManagement/src/main/resources/Talks-Input-List-Light.txt";
		//Writing Fast Tests Against Enterprise Rails 60min
		
		List<Talk> talks = mgr.parseInputTalksListFile(inputFile);
		Assert.assertEquals(1, talks.size());
		Assert.assertEquals(60, talks.get(0).getDurationInMinutes());
		Assert.assertEquals("Writing Fast Tests Against Enterprise Rails", talks.get(0).getTitle());
	}
	
	@Test
	public void testProcessAndScheduleTalks() {
		ConferenceScheduleManager mgr =  new ConferenceScheduleManager();
		
		String inputFile = "/Users/anshul/Documents/workspace/ConferenceTrackManagement/src/main/resources/Talks-Input-List-Light.txt";
		//Writing Fast Tests Against Enterprise Rails 60min
		
		List<Talk> talks = new ArrayList<>();
		Talk talk = new Talk("Writing Fast Tests Against Enterprise Rails", 60);
		talks.add(talk);
		
		Conference cnf = mgr.processAndScheduleTalks(talks);
		
		String title = cnf.getTracks().get(0).getSlots().get(0).getEvents().get(0).getTitle();
		int duration = cnf.getTracks().get(0).getSlots().get(0).getEvents().get(0).getDurationInMinutes();
		
		Assert.assertEquals("Writing Fast Tests Against Enterprise Rails", title);
		Assert.assertEquals(60, duration);
		
	}

}
