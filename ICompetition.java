package com.company;

/**
 * Created by Ryan on 11/30/2014.
 */
public interface ICompetition {

    void start(Event event, Team team1, Team team2);

        Event getEvent();

        Team getTeam1();

        Team getTeam2();

}/* End ICompetition interface */
