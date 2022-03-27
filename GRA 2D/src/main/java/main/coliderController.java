package main;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class coliderController {

    static ArrayList<ArrayList<Integer>> ColiderMap = new ArrayList();
    static boolean enter = true;

    //dodanie colidera do mapy
    public static ArrayList addObjectColiderMap(int[][] args){
        for(int x = 0 ; x <= args.length-1; x++)
        {
            ColiderMap.add(new ArrayList<>(Arrays.asList(args[x][0],args[x][1],args[x][2],args[x][3])));
        }
        return ColiderMap;
    }
    //rysowanie coliderow na mapie
    public static void ColiderDrawing(ArrayList<ArrayList<Integer>> arList) {
        if (!enter) {
            PlayerStats.playersize = 0;
        } else {
            PlayerStats.playersize = 160;
        }
        if ((arList.get(0).get(0) + Player.worldMoveX >= Player.x + 80 - Player.playerSpeed && arList.get(0).get(0) + Player.worldMoveX <= Player.x + 80 + Player.playerSpeed) && arList.get(0).get(1) <= Player.y) {

            Player.solid = true;
        }
        if ((arList.get(0).get(0) + Player.worldMoveX <= Player.x + 80 - Player.playerSpeed || arList.get(0).get(0) + Player.worldMoveX >= Player.x + 80 + Player.playerSpeed) && arList.get(0).get(1) < Player.y) {
            Player.solid = false;
        }
        if (enter || arList.get(0).size() == 2) {
            if ((arList.get(0).get(0) + Player.worldMoveX >= Player.x - 80 - Player.playerSpeed && arList.get(0).get(0) + Player.worldMoveX <= Player.x - 80 + PlayerStats.playersize) && arList.get(0).get(1) > Player.y) {
                enter = !enter;
                Player.solid = false;
                Player.ground = arList.get(0).get(1) - 70;
                if (arList.size() >= 2 && arList.get(0).size() <= 2) {
                    arList.remove(0);
                }
            }
        } else if (!enter && arList.get(0).size() > 2) {
            if ((arList.get(0).get(2) + Player.worldMoveX >= Player.x - 80 - Player.playerSpeed && arList.get(0).get(2) + Player.worldMoveX <= Player.x + PlayerStats.playersize) && arList.get(0).get(3) > Player.y) {
                enter = !enter;
                Player.solid = false;
                Player.ground = arList.get(0).get(3) - 70;
                if (arList.size() >= 2) {
                    arList.remove(0);
                }
            }
        }
    }
    //sortowanie coliderow
    public static ArrayList sortColiderMap(){
        for(int x = 0; x < ColiderMap.size();x++){
            for (int y = 0; y < ColiderMap.size();y++){
                if (ColiderMap.get(x).get(0) < ColiderMap.get(y).get(0)){
                    ArrayList<Integer> placeholder = ColiderMap.get(x);
                    ColiderMap.set(x,ColiderMap.get(y));
                    ColiderMap.set(y,placeholder);
                }
            }
        }
        return ColiderMap;
    }
    //dodawanie punktów wyjsca z obiektów
//    public static void bakeColiderMap(Image object,int[][] objectmap){
//        int counter = 0 ;
//        for (int x = 0; x <= objectmap.length-1; x++){
//            for (int y = 0; y < ColiderMap.size();y++){
//                if (objectmap[x][0] != ColiderMap.get(y).get(0)){
//                    counter++;
//                }
//            }
//            if (counter == 1){
//                int width = object.getWidth(null)+objectmap[x][0];
//                int height = PlayerStats.groundStart+70;
//                int index = ColiderMap.indexOf(objectmap[x]);
//                ColiderMap.set(index, new ArrayList(Arrays.asList(objectmap[x][0],objectmap[x][1],width,height)));
//            }
//        }




}
