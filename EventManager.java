package com.company;

import java.util.ArrayList;

import java.util.ArrayList;

public class EventManager{


    protected ArrayList<Team> teams = new ArrayList<Team>();

    private ArrayList<Event> events = new ArrayList<Event>();


    EventManager(ArrayList<Team> teams){

        this.teams = teams;
                events.add(new CanJamEvent(randomizeTeamOrder(teams)));
                events.add(new CornHoleEvent(randomizeTeamOrder(teams)));
                events.add(new HorseShoesEvent(randomizeTeamOrder(teams)));
                events.add(new LadderBallEvent(randomizeTeamOrder(teams)));
                events.add(new StickGameEvent(randomizeTeamOrder(teams)));
                events.add(new WashoosEvent(randomizeTeamOrder(teams)));
    }//..

    public void showEvents(){

        System.out.println("------- Events -------");

          for(Event e:events){/* looks through each event */

            System.out.println("\n"+e.name+":");

            for(Competition c :LGO_Main.competitionManager.getCompetitions()){

                if(c.event == e){
                    System.out.println("    -----------------------------------------");
                    System.out.println("    *          CURRENTLY COMPETING            ");
                    System.out.println("    -            "+c.team1.olympian1.name+" and "+c.team1.olympian2.name);
                    System.out.println("    *                  Vs.                    ");
                    System.out.println("    -            "+c.team2.olympian1.name+" and "+c.team2.olympian2.name);
                    System.out.println("    -----------------------------------------");
                }
            }
            int quesize = e.queue.size();

            for(int i=e.queue.size()-1;i>-1;i--){
                System.out.println("    "+(quesize-i)+": "+e.queue.get(i).olympian1.name+" and "+e.queue.get(i).olympian2.name);
            }
            for(int i = 0;i<e.stack.size();i++){
                System.out.println("    "+((quesize+1)+i)+": "+e.stack.get(i).olympian1.name+" and "+e.stack.get(i).olympian2.name);
            }
        }
    }//..

    public ArrayList<Event> getEvents() {
        return events;
    }//..

    protected ArrayList<Team> randomizeTeamOrder(ArrayList<Team> teams){

            ArrayList<Team> randomisedTeams = new ArrayList<Team>();
            ArrayList<Team> temp = new ArrayList<Team>();

        for(Team t:teams){
            temp.add(t);
        }

        while(temp.size()>0){
            Team t = temp.get((int)(Math.random()*temp.size()));
                randomisedTeams.add(t);
                temp.remove(t);
        }

            return randomisedTeams;
    }//..

}/* End EventManager Class */
