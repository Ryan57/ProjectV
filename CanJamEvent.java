package com.company;

import java.util.ArrayList;

public class CanJamEvent extends Event{


    CanJamEvent(ArrayList<Team> teams){this.queue = teams;
        name = "CanJam";
    };

    public String getExtraInfo(){
        return "";
    };


}// end CanJanEvent
