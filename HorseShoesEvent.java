package com.company;

import java.util.ArrayList;

public class HorseShoesEvent extends Event{

    int numHorseShoes;

    HorseShoesEvent(ArrayList<Team> teams){this.queue = teams; name = "HorseShoes";
    }

    public String getExtraInfo(){
        return null;
    }


}// end HorseShoesEvent
