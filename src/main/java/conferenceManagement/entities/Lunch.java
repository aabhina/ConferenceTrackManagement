package conferenceManagement.entities;

import conferenceManagement.utilities.ConferenceManagementConstants;

public class Lunch extends Event {
    public Lunch() {
        super(ConferenceManagementConstants.LUNCH_START_TIME, "Lunch", ConferenceManagementConstants.LUNCH_DURATION_MINUTES);
    }
}
