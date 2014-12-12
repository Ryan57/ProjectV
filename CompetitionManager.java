package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CompetitionManager {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private  ArrayList<Team> teams = new ArrayList<Team>();

    protected ArrayList<Competition> competitions = new ArrayList<Competition>();

    CompetitionManager(ArrayList<Team> teams) {
        this.teams=teams;
        startRandomCompetitions();
    }//..

    public void CurrentCompetitions(){
/* Displays all currently running competitions */
        System.out.println("\n------- Showing Current Competitions -------\n");

        for (Competition c: competitions){ /* iterates through all competitions and formats the output in an organized fashion */
            System.out.println("\nEvent: "+c.event.name+
                               "\n\n    Team1: "+c.team1.olympian1.name+
                               "           "+c.team1.olympian2.name+
                               "\n\n    Team2: "+c.team2.olympian1.name+
                               "\n            "+c.team2.olympian2.name);
        }

        if(competitions.size() == 0){
            boolean allEventsComplete = true;
            System.out.println("           Events complete!\n    Enter \'e\' command to view final results.");

//            for (Event e:LGO_Main.eventManager.getEvents()){
//                if(e.queue.size()>1){
//                    allEventsComplete=false;
//                }
//            }
        }

    }//..

    public void startCompetition(Event event,Team team1,Team team2){
        
        Competition competition = new Competition();
            competition.start(event, team1, team2);
                competitions.add(competition);
    }//..

    protected Event getRandEventAvailable(){
        
        Event event = LGO_Main.eventManager.getEvents().get((int)(Math.random()*LGO_Main.eventManager.getEvents().size()));
        for (Competition c :competitions){
            if(c.event.name.equals(event.name)){
                return getRandEventAvailable();
            }
        }
        return event;
    }//..

    public void initiateEndCompetition(){
        boolean ended=false;
            System.out.print("Enter Event to End: ");

        String eventName=getChoice();

        if(eventName.toLowerCase().equals("q")){
            return;
        }

        String teamMember;

        for (Competition c:competitions){

            if(eventName.toLowerCase().equals(c.event.name.toLowerCase())){
                System.out.print("Enter an Olympian on winning team: ");
                teamMember=getChoice();

                if(teamMember.toLowerCase().equals(c.team1.olympian1.name.toLowerCase()) || teamMember.toLowerCase().equals(c.team1.olympian2.name.toLowerCase()) ){
                    endCompetition(c,c.team1);
                    ended=true;
                    break;

                }else if(teamMember.toLowerCase().equals(c.team2.olympian1.name.toLowerCase()) || teamMember.toLowerCase().equals(c.team2.olympian2.name.toLowerCase()) ){
                    endCompetition(c,c.team2);
                    ended=true;
                    break;

                }else {
                    System.out.println( eventName+" was not competing in this round of " + c.event.name);
                }
            }
        }
        if(!ended) {

            System.out.println("The " + eventName + " event currently has no competitors");

                initiateEndCompetition();
        }
    }//..

    public void endCompetition(Competition competition,Team winningTeam){
        Team losingTeam;

        if(competition.team1 != winningTeam){/* determines losing team to maintain record consistency */
            losingTeam=competition.team1;

        }else {
            losingTeam=competition.team2;
        }

        for (Team t:teams){/* increments and records each teams wins and losses */

            if(t==winningTeam){
                t.wins++;
            }
            if(t==losingTeam){
                t.losses++;
            }
        }

        competition.event.BracketQueue(winningTeam, losingTeam);/* Determines winner and loser, removes competition from currently running */

         competitions.remove(competition);
            startRandomCompetitions(); /* Begins another competition if olympians available */
    }//..

    protected void startRandomCompetitions(){
        for (Event e : LGO_Main.eventManager.getEvents()){/* iterates through events, starting events randomly */
            boolean canStartComp = true;
            Team[] nextTeams = e.PeakNextTeams();
            if(nextTeams != null) {
                for (Competition c : competitions) {
                    if (c.event.name.equals(e.name)) {/* check if event is in competition, cannot start another if currently competing */
                        canStartComp = false;
                    }
                    if (canStartComp) {
                        String cT1 = c.team1.olympian1.name,
                               cT2 = c.team2.olympian1.name;

                        if (cT1.equals(nextTeams[0].olympian1.name) || cT1.equals(nextTeams[1].olympian1.name)) {
                            canStartComp = false;
                        }

                        if (cT2.equals(nextTeams[0].olympian1.name) || cT2.equals(nextTeams[1].olympian1.name)) {
                            canStartComp = false;
                        }
                    }/* verifies that the teams are not competing in any other events, if true, they can compete in a new competition */
                }
                if (canStartComp) {
                    System.out.println("\n------- Starting New Competition -------\n");
                    System.out.println("Event: " + e.name + "\n" +
                            "\n Team1: " + nextTeams[0].olympian1.name + "\n" +
                            "        " + nextTeams[0].olympian2.name + "\n" +
                            "\n Team2: " + nextTeams[1].olympian1.name + "\n" +
                            "        " + nextTeams[1].olympian2.name + "\n");

                        nextTeams = e.GetNextTeams();
                        startCompetition(e, nextTeams[0], nextTeams[1]);
                }
            }
        }
    }//..

    protected String getChoice(){/* allows user input & reads input as users command */
        String choice = "";

        try{
            choice = in.readLine();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return choice;
    }//..

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }//..

}/* end of CompetitionManager Class */
