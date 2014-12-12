package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class OlympianManager {

    private BufferedReader file;
    private ArrayList<Olympian> olympians = new ArrayList<Olympian>();
    private ArrayNode OlympianList = new ArrayNode();
/* Creates an array of Olympians based on the input from reading the file */

    OlympianManager(BufferedReader file){
       this.file = file;
        loadOlympians();
    }/* Pulls potential olympians information from a file */

    public void loadOlympians(){
        String line;

        try {
            while ( (line = file.readLine()) != null){
                if(!line.equals("LGOO")) {

                    String[] parse = line.split(",");
                    String name = parse[0];
                    Sex sex;
                    int age = Integer.parseInt(parse[2]);

                    if(parse[1].equals("M")) {
                        sex = Sex.MALE;

                    }else{
                        sex = Sex.FEMALE;
                    }
                    //System.out.println("Adding Olympian "+name+", "+sex+", "+age);

                        olympians.add(new Olympian(name, sex, age));
                        OlympianList.add(new Olympian(name, sex, age));
                }
            }
            //System.out.println("ArrayNode olympians.size() = "+OlympianList.size());

        }catch (IOException e) {

            e.printStackTrace();
        }

    }//..

    public void showOlympians(){

        System.out.println("\n------- Olympians -------");

        for(Olympian o : olympians){

           System.out.println(o.name+", "+o.sex+", "+o.age);
        }

/*       for (int i=0;i< OlympianList.size();i++){
           Olympian o = (Olympian)OlympianList.get(i);
           System.out.println(o.name+", "+o.sex+", "+o.age);
*/       }

    public ArrayList<Olympian> getOlympians(){
        return olympians;
    }

    public ArrayNode getOlympianList(){
        return  OlympianList;
    }

}/* end OlympianManager Class */
