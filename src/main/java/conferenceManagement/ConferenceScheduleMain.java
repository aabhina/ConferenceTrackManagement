package conferenceManagement;

import java.util.List;

import conferenceManagement.entities.Conference;
import conferenceManagement.entities.Talk;
import conferenceManagement.utilities.ConferenceUtils;

public class ConferenceScheduleMain {

	public static void main(String[] args) {
		
		ConferenceScheduleManager conferenceScheduleManager = new ConferenceScheduleManager();
		
        List<Talk> talkList = null;
        
        // Fetch the input talk list from a disk location.
        //Replace this value with the path to the input file on your local machine.
        String pathToInputTalksListFile = "/Users/anshul/Documents/workspace/ConferenceTrackManagement/src/main/resources/Talks-Input-List2.txt";
        
        try {
            talkList = conferenceScheduleManager.parseInputTalksListFile(pathToInputTalksListFile);
        } 
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        
        if(talkList == null || talkList.size() == 0) {
        		System.out.println("The talk list input file is empty");
        	    return;
        }
        
        
        // Prints the talks list from the input text file.
        ConferenceUtils.printTalks(talkList);

        // Processes and schedules talks
        Conference conference = conferenceScheduleManager.processAndScheduleTalks(talkList);

        // Outputs the scheduled talks
        conferenceScheduleManager.printFinalConferenceSchedule(conference);
	}

}