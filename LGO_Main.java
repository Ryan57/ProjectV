package com.company;

import java.io.*;
import java.util.ArrayList;

public class LGO_Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedReader file;
    static String fileLocation;
    static String command;

        static EventManager eventManager;
        static OlympianManager olympianManager;
        static TeamManager teamManager;
        static CompetitionManager competitionManager;

    public static void showHelp(){

        System.out.println("-------- LGO HELP -------\n*");
        System.out.println("* ARGUMENTS            -- DETAILS\n*");
        System.out.println("* events, e            -- Displays events.");
        System.out.println("* olympians, o         -- Displays olympians.");
        System.out.println("* queue, t             -- Display each teams queue and record");
        System.out.println("* competitions, c      -- Display competitions currently running");
        System.out.println("* endcompetition, ec   -- Ends currently running competition");
        System.out.println("* help, h              -- Display this help message");
        System.out.println("* quit, q              -- Exit program");
        System.out.println("*\n-----------------------------");

    }/* list of help functions, giving users available options when they don't know what to do. */

    public static void processCommand() throws IOException{

        command = in.readLine();
        command.toLowerCase();


        if(command.equals("help") || command.equals("h")){
            showHelp();
        }else if(command.equals("quit") || command.equals("q")){
            System.exit(0);
        }else if(command.equals("olympians") || command.equals("o")){
            showOlympians();
        }else if(command.equals("events") || command.equals("e")){
            showEvents();
        }else if(command.equals("queue") || command.equals("t")){
            showTeams();
        }else if(command.equals("competitions") || command.equals("c")){
            showCompetitions();
        }else if(command.equals("endcompetition") || command.equals("ec")){
            endCompetition();
        }else{
            showHelp();
        }
    }//..

    public static void showTeams(){
        teamManager.showTeams();
    }//..

    public static void showEvents() {
        eventManager.showEvents();
    }//..

    public static void showOlympians(){
        olympianManager.showOlympians();
    }//...

    public static void showCompetitions(){
        competitionManager.CurrentCompetitions();
    }//..

    public static void endCompetition(){
        competitionManager.initiateEndCompetition();
    }//..

    public static void main(String[] args) {

        if (args.length < 1) {/* handles invalid argument */
            System.out.println("File location .lgoo required");
                System.exit(1);
        }else {
            try{
                System.out.println("-------- LGO --------\n");

                       fileLocation = args[0];

                            file = new BufferedReader(new FileReader(new File(fileLocation)));

                            olympianManager = new OlympianManager(file);

                            teamManager = new TeamManager(olympianManager.getOlympians());

                            eventManager = new EventManager(teamManager.getTeams());

                            competitionManager = new CompetitionManager(teamManager.getTeams());

                while(true) {
                    System.out.print("\nEnter Command: ");
                    processCommand();
                }

            }catch (FileNotFoundException fnfe){/* handle invalid argument */
                fnfe.printStackTrace();
                System.out.println("Cannot locate file "+fileLocation);

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Internal error found!");
            }
        }
    }//..


}/* end Class LGO_Main */
