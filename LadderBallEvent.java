package com.company;

import java.util.ArrayList;

public class LadderBallEvent extends Event {

    int numRungs;

    LadderBallEvent(ArrayList<Team> teams){this.queue = teams; name = "LadderBall"; }


    public String getExtraInfo() {
        return null;
    }


}// end LadderBallEvent
