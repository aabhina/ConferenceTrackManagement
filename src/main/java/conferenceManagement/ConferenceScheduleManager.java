package conferenceManagement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import conferenceManagement.entities.Conference;
import conferenceManagement.entities.Event;
import conferenceManagement.entities.Lunch;
import conferenceManagement.entities.NetworkingEvent;
import conferenceManagement.entities.Slot;
import conferenceManagement.entities.Talk;
import conferenceManagement.entities.Track;
import conferenceManagement.utilities.ConferenceManagementConstants;
import conferenceManagement.utilities.ConferenceUtils;
import conferenceManagement.utilities.TalkComparator;

public class ConferenceScheduleManager {
	
	//Parse the input text file which contains the list of talks.
	//Return a structured list of Talk objects from this method.
	//which can then be used to schedule these talks.
	public List<Talk> parseInputTalksListFile(String inputFile) {
		FileInputStream fileInputStream = null;
        List<Talk> talkList = new ArrayList<Talk>();
        
        try {
            fileInputStream = new FileInputStream(inputFile);
        } 
        catch (FileNotFoundException e) {
            System.err.println("File not found ");
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        String individualLine;
        int timeDurationInMinutes = 0;
        
        // Read Input File Line By Line
        try {
            while ((individualLine = br.readLine()) != null) {
                
                if(individualLine.contains("//") || individualLine.isEmpty())
                    continue;

                String title = individualLine.substring(0, individualLine.lastIndexOf(" "));
                String minutesString = individualLine.substring(individualLine.lastIndexOf(" ") + 1);
                
                // get only the integers as string from the line.
                String minutes = individualLine.replaceAll("\\D+", "");
                
                if (ConferenceManagementConstants.LIGHTNING_TALK.equals(minutesString)) {
                    timeDurationInMinutes = ConferenceManagementConstants.LIGHTNING_TALK_DURATION_MINUTES;
                } 
                else {
                    try {
                        timeDurationInMinutes = Integer.parseInt(minutes);
                    } catch (NumberFormatException e) {
                        System.err.println("Could not parse the line : " + individualLine);                        
                    }
                }
                
                Talk singleTalk = new Talk(title, timeDurationInMinutes);
                talkList.add(singleTalk);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                fileInputStream.close();
                br.close();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
        
        return talkList;
	}

	public Conference processAndScheduleTalks(List<Talk> talkList) {
        Conference conference = new Conference();

        // sort all the talks in descending order
        Collections.sort(talkList, new TalkComparator());
        
        int trackCount = 0;

        // run this loop till all the talks are scheduled.
        while (talkList.size() > 0) {

            // create and fill morning slot.
            Slot morningSlot = new Slot(ConferenceManagementConstants.MORNING_SLOT_DURATION_MINUTES, 
            		ConferenceManagementConstants.TRACK_START_TIME);
            
            fillIndividualSlot(morningSlot, talkList);

            // create and fill lunch slot.
            Slot lunchSlot = new Slot(ConferenceManagementConstants.LUNCH_DURATION_MINUTES, 
            		ConferenceManagementConstants.LUNCH_START_TIME);
            
            lunchSlot.addEvent(new Lunch());

            // create and fill afternoon slot.
            Slot afternoonSlot = new Slot(ConferenceManagementConstants.AFTERNOON_SLOT_DURATION_MINUTES,
            		ConferenceManagementConstants.POST_LUNCH_SLOT_START_TIME);
            
            fillIndividualSlot(afternoonSlot, talkList);

            // create and fill networking slot.
            Slot networkingSlot = new Slot(ConferenceManagementConstants.NETWORKING_DURATION_MINUTES,
            		ConferenceManagementConstants.NETWORKING_START_TIME);
            
            networkingSlot.addEvent(new NetworkingEvent());

            // add all the slots for the day into the track.
            Track track = new Track(++trackCount);
            track.addSlot(morningSlot);
            track.addSlot(lunchSlot);
            track.addSlot(afternoonSlot);
            track.addSlot(networkingSlot);
            
            // add track to the conference.
            conference.addTrack(track);
        }

        return conference;
    
	}
	
	private void fillIndividualSlot(Slot slot, List<Talk> talks) {
        // initialize the slot start time.
        Calendar currentStartTime = slot.getStartTime();
        
        Iterator<Talk> talksIterator = talks.iterator();
        
        while (talksIterator.hasNext()) {
            Talk talk = talksIterator.next();
            
            if (slot.hasTimeFor(talk)) {
                // add an event to the slot at the currentStartTime calculated.
                slot.addEvent(new Event(currentStartTime, talk.getTitle(), talk.getDurationInMinutes()));
                
                // calculate the next start time based on the current start time and current talk duration.
                currentStartTime = ConferenceUtils.getNextStartTime(currentStartTime, talk);
                
                // remove the talk from the list.
                talksIterator.remove();
            }
        }
    }
	
	public void printFinalConferenceSchedule (Conference conference) {
        SimpleDateFormat sdf = ConferenceManagementConstants.DATE_FORMAT;
        
        System.out.println("Test Output: ");
        
        for(Track track : conference.getTracks()){
        		System.out.println();
            System.out.println("Track " + track.getTrackId());
            
            List<Slot> slots = track.getSlots();
            
            for (Slot slot : slots) {
                for (Event event : slot.getEvents()) {
                		if(event.getTitle().equalsIgnoreCase("Lunch") 
                				|| event.getTitle().equalsIgnoreCase("Networking Event")) {
                			System.out.println(sdf.format(event.getStartTime().getTime()) + " " + event.getTitle());
                		}
                		
                		else {
                        System.out.println(sdf.format(event.getStartTime().getTime()) + " " + event.getTitle()
                                    + " " + event.getDurationInMinutes() + "mins");
                		}
                }
            }
        }
    }

}