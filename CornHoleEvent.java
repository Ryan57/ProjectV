package com.company;

import java.util.ArrayList;

public class CornHoleEvent extends Event{

    int numBeanBags;

    CornHoleEvent(ArrayList<Team> teams){this.queue = teams;name = "CornHole";
    }

    public String getExtraInfo(){
        return null;
    }


}// end CornHoleEvent
