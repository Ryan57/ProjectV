package com.company;

import java.util.ArrayList;

public class WashoosEvent extends Event{

    boolean hasAutoWinStick;
    int numWashoos;

    WashoosEvent(ArrayList<Team> teams){this.queue = teams; name = "Washoos";
    };

    public String getExtraInfo(){
        return "";
    };

}// end Washoos
