package com.company;

import java.util.ArrayList;

public class StickGameEvent extends Event{

    int frisbeeSize;

    StickGameEvent(ArrayList<Team> teams){this.queue = teams; name = "StickGame"; }

    public String getExtraInfo() {
        return null;
    }

}// end StickGameEvent
