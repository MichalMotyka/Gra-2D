package main;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Particles {
    static ArrayList<Particle> arrayList = new ArrayList<>();
    static ArrayList<Particle> runList = new ArrayList<>();
    static ArrayList<Particle> runArrayList = new ArrayList<>();
    static ArrayList<ArrayList> rainParticles = new ArrayList<ArrayList>();
    static ArrayList<Particle> layer = new ArrayList<Particle>();

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
    public static void createLayer(){
        for (int x= 0;x <=1500; x++){
            int v = (int) Math.round(Math.random() * 250);
            Particle rainParticle = new Particle(x+v,-10,1,2);
            rainParticle.setSpeed((int)Math.round(Math.random()*(10-5))+5);
            layer.add(rainParticle);
            x=x+v;
        }
        rainParticles.add((ArrayList) layer.clone());
        layer.clear();
    }
    public static void drawRainParticle(Graphics2D g2){
        createLayer();
        g2.setColor(Color.CYAN);
        for (int y = 0;y<=rainParticles.size()-1;y++)
          for (int x = 0; x <= rainParticles.get(y).size()-1;x++){
            Particle particle = (Particle)rainParticles.get(y).get(x);
            if (particle.getY()>=1200){
                rainParticles.get(y).remove(x);
                if(rainParticles.get(y).size() == 0){
                    rainParticles.remove(y);
                }
            }else{
                rainParticles.get(y).set(x,new Particle(particle.getX()-2, particle.getY()+particle.getSpeed(),1,(int) Math.round(Math.random() * 4)));
                ((Particle) rainParticles.get(y).get(x)).setSpeed(particle.getSpeed());
                g2.fillRect(particle.getX(),particle.getY(),1,3);
            }

        }
    }
}
