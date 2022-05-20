package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Particles {
    static ArrayList<Particle> arrayList = new ArrayList<>();
    static ArrayList<Particle> runList = new ArrayList<>();
    static ArrayList<Particle> runArrayList = new ArrayList<>();

    public static void DrawParticle(Graphics2D g2){
        g2.setColor(new Color(120,120,120,190));
        for (int loop=0; loop < arrayList.size()-1;loop++){
            Particle particle = arrayList.get(loop);
            if (particle.getWidth() < 50) {
                arrayList.set(loop,(new Particle(particle.getX()-5, particle.getY()-1, particle.getWidth() + 2, particle.getHeight() + 2)));
            }else if(particle.getWidth() < 100){
                g2.setColor(new Color(120,120,120,190+(100-particle.getWidth()-50)));
                arrayList.set(loop,(new Particle(particle.getX()-5, particle.getY()-1, particle.getWidth()+1, particle.getHeight()+1)));
            }else{
                removeParticle(loop);
            }
            g2.fillOval(particle.getX(), particle.getY(), particle.getWidth(), particle.getHeight());
        }

    }
    public static void addNewParticle(int x,int y){

        arrayList.add(new Particle(x+25,y,1,1));
        arrayList.add(new Particle(x+15,y-15,1,1));
        arrayList.add(new Particle(x,y,1,1));
        arrayList.add(new Particle(x-25,y,1,1));
        arrayList.add(new Particle(x-15,y-15,1,1));

    }
    public static void removeParticle(int index){
        arrayList.remove(index);
    }

    public static void addNewRunParticle(int x,int y){

    }
    public static void drawRunParticle(Graphics2D g2){
        g2.setColor(new Color(120,120,120,190));
        for (int loop=0; loop < runArrayList.size()-1;loop++){
            Particle particle = runArrayList.get(loop);
            if (particle.getWidth() < 10) {
                runArrayList.set(loop,(new Particle(particle.getX()-(int)(Math.random()*13), particle.getY()-2, particle.getWidth() + 1, particle.getHeight() + 1)));
            }else if(particle.getWidth() <= 15){
                g2.setColor(new Color(120,120,120,190+(100-particle.getWidth()-50)));
                runArrayList.set(loop,(new Particle(particle.getX()-(int)(Math.random()*13), particle.getY()+1, particle.getWidth()+1, particle.getHeight()+1)));
            }else{
                removeRunParticle(loop);
            }
            g2.fillOval(particle.getX(), particle.getY(), particle.getWidth(), particle.getHeight());
        }
    }
    public static void removeRunParticle(int index){runArrayList.remove(index);}
}
