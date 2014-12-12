package com.company;

import java.util.ArrayList;

public class TeamManager {

    private ArrayList<Olympian> OlympiansPossible = new ArrayList<Olympian>();
    private ArrayList<Team> teams = new ArrayList<Team>();

    private ArrayList<Olympian> maleList = new ArrayList<Olympian>();
    private ArrayList<Olympian> femaleList = new ArrayList<Olympian>();

    int MaxNumTeams;

    TeamManager(ArrayList<Olympian> olympians) {

        for (Olympian o:olympians){
            OlympiansPossible.add(o);
        }

        MaxNumTeams = (int)(olympians.size()*.5);

                  splitMaleFemale();

        while (MaxNumTeams > 0) {/* must have enough olympians to make a team */
            makeTeam();
        }

    }//..

    protected void splitMaleFemale(){

        for (Olympian o : OlympiansPossible){
            switch (o.sex){

                case MALE:
                    maleList.add(o);
                        break;

                case FEMALE:
                    femaleList.add(o);
                        break;
            }
        }

       /*System.out.println("males: "+maleList.size());
        System.out.println("females: "+femaleList.size()); */

    }//..

    protected void makeTeam(){
        Olympian o1,o2;

        o1 = pickRandomMale();
        o2 = pickRandomFemale();

        if(o1 != null && o2 != null) { /* !null indicates that olympians are available to be made into a team */
            teams.add(new Team(o1, o2));
                MaxNumTeams--;
/* Indicates the maximum number of teams that can be made from the given list of olympians found in file */
        }
    }//...

    public Olympian pickRandomMale(){

        int rand = (int )(Math.random()*maleList.size());
            Olympian m;

        if(maleList.size() > 0){ /* Chooses available males from olympian file list */
            m = maleList.get(rand);/* picks olympians from OlympiansPossible */

                maleList.remove(maleList.get(rand)); /* remove chosen olympian males from OlympiansPossible */

                return m;

        }else {/* males have been placed in teams, tries placing females */
            return pickRandomFemale();
        }
    }//..

    public Olympian pickRandomFemale(){
        int rand = (int )(Math.random()*femaleList.size());
        Olympian m;

        if(femaleList.size() > 0){/* Chooses available females from olympian file list */
            m = femaleList.get(rand);/* picks olympians from OlympiansPossible */

                femaleList.remove(femaleList.get(rand)); /* remove chosen olympian females from OlympiansPossible */

                return m;

        }else {/* females have been placed in teams, tries males */
            return pickRandomMale();
        }
    }//..

    public void showTeams(){
        int teamNum = 1;
        Olympian o1,o2;
        Olympian oddOneOut = null;

        for (Team t: teams){

            o1 = t.olympian1;
            o2 = t.olympian2;

            System.out.println("\n-----  Team "+(teamNum++)+"  -----");
            System.out.println("-    "+t.wins+" Wins, "+t.losses+" Losses");
            System.out.println(" \n*    "+o1.name+", "+o1.sex+", "+o1.age);
            System.out.println("-    "+o2.name+", "+o2.sex+", "+o2.age);
            System.out.println(" \n-----------------------------------");
        }

        if(maleList.size() > 0 || femaleList.size() > 0){/* If uneven number of olympians, someone is left out */
            for (Olympian o : maleList){
                oddOneOut = o;
            }
            for (Olympian o : femaleList){
                oddOneOut = o;
            }

            System.out.println("\n\n!! "+oddOneOut.name+" is sitting out :(");

        }
    }//..

    public ArrayList<Team> getTeams() {
        return teams;
    }//..

}/* End TeamManager Class */
