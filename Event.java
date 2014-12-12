package com.company;

import java.util.ArrayList;

abstract class Event{

    protected ArrayList<Team> queue = new ArrayList<Team>();
    protected ArrayList<Team> stack = new ArrayList<Team>();

    String name;

        int playTo;
        boolean isPlayToExact;
        int playDistance;

    public abstract String getExtraInfo();

    public Team[] GetNextTeams(){

        if(queue.size()>1) {

            Team t1 = queue.get(0), /* Pulls two teams from queue, allowing them to compete in an event */
                 t2 = queue.get(1);

                      queue.remove(t1);
                      queue.remove(t2);
/* deque the competing teams from the queue, creating environment to enqueue or add to stack for ranking */

            Team t[] = new Team[]{t1, t2};

                return t;
        }
                return null;
    }//..

    public void BracketQueue(Team winningTeam, Team losingTeam){

        queue.add(winningTeam); /* enqueue's winning team back to the end of the queue */
        stack.add(0,losingTeam); /* Places losing team on the top of the stack */
    }//..

    public Team[] PeakNextTeams(){

        if(queue.size()>1) {
            Team[] t = new Team[]{queue.get(0), queue.get(1)};
/* If queue has more than one team in it, a competition pulls the next two teams from the queue */
                return t;
        }
                return null;
    }//..

}/* end Event Class */
