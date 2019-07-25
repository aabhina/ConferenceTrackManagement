package conferenceManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class Conference {
	List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public Conference(){
        this.tracks = new ArrayList<>();
    }

}
