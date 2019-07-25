package conferenceManagement.entities;

import conferenceManagement.utilities.ConferenceManagementConstants;

public class NetworkingEvent extends Event {

    public NetworkingEvent () {
        super(ConferenceManagementConstants.NETWORKING_START_TIME, "Networking Event", 0);
    }
}
