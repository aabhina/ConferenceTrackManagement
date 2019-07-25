package conferenceManagement.utilities;

import java.util.Comparator;

import conferenceManagement.entities.Talk;

public class TalkComparator implements Comparator<Talk> {
	
	public int compare(Talk t1, Talk t2) {
        if(t1.getDurationInMinutes() < t2.getDurationInMinutes()){
            return 1;
        } else {
            return -1;
        }
    }

}
