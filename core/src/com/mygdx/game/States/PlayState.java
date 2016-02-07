package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by m on 2/6/16.
 */
public class PlayState extends State {

    Random random = new Random();
    ScrollingBackground background;
    ArrayList<Entity> entities = new ArrayList<Entity>();
    int ledgeCount = 20;
    Hero hero;
    TheMaster master;
    TheMaster master2;

    boolean isPinched = false;
    long ticks = 0;
    long lastPinch = 0;
    long currentPinch = 0;
    //Animator animator = new Animator();

    public PlayState(GSM gsm) {
        super(gsm);
        //animator.create();
        makeTextures();
        background = new ScrollingBackground(this);
        hero = new Hero(200, 200, this);
        master = new TheMaster(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        generateInitialLedges();
        //entities.add();
        entities.add(hero);
        entities.add(master);
    }

    public ArrayList<Entity> getEntity() {
        return entities;
    }

    private void generateInitialLedges() {
        Ledge previous = null;
        for (int i = 0; i < ledgeCount; i++) {
            Ledge ledge = generateLedge(previous);
            previous = ledge;
            entities.add(ledge);
        }
    }

    private Ledge generateLedge(Ledge previous)
    {
        if(previous == null)
        {
            return new Ledge(hero.getX(),hero.getY());
        }

        float ledgeX = previous.getX() + 125;
        float ledgeY = 300;

        if (previous.getY() == 300) {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 200;
            else
                ledgeY = 400;
        }

        else if(previous.getY() == 100)
            ledgeY = 200;

        else if(previous.getY() == 400)
        {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 200;
            else
                ledgeY = 800;
        }

        else if(previous.getY() == 500) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 400;
        }
        else if(previous.getY() == 600)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 500;
            else
                ledgeY = 700;
        }

        else if(previous.getY() == 700)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 200;
            else
                ledgeY = 400;
        }

        else if(previous.getY() == 800)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 700;
            else
                ledgeY = 900;
        }

        else if(previous.getY() == 900)
            ledgeY = 800;


        return new Ledge(ledgeX, ledgeY);
    }

    public void makeTextures() {
        Pixmap pixmap = new Pixmap(50, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Ledge.texture = new Texture(pixmap);
        pixmap.dispose();

        pixmap = new Pixmap(10,40, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        Hero.texture = new Texture(pixmap);
        pixmap.dispose();

        TheMaster.texture = new Texture("Untitled.png");
        /*pixmap = new Pixmap(30,30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        TheMaster.texture = new Texture(pixmap);
        pixmap.dispose();*/
    }

    @Override
    public void handleInput() {
    }

    public void removeLedge() {

    }

    public void spawnBomb() {

    }

    public void freeze() {

    }

    public void reverseGravity() {
        hero.reverseGravity();
    }

    public void moveCommand(float x, float y, float z) {
        master.setX(x);
        master.setY(y);
    }

    public void circleCommand(boolean isclockwise, double sweptAngle, float x, float y) {
    }

    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y) {
        if (speed > 400)
            reverseGravity();
    }

    public void duoSwipeDownCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y)
    {
        /*if(direction.getY() >= 0)
            master.setColor(Color.BLUE);
        else
            master.setColor(Color.RED);*/
    }
    public void pinchCommand() {

//        if(lastPinch > ticks)
//            return;
//        lastPinch = ticks + 120;
//
//        master2 = new TheMaster(master.getX(),master.getY());
//        //TheMaster.texture = new Texture("Pinch.png");
//
//        System.out.println(lastPinch);
//        //master2 = new TheMaster(master2.getX(),master2.getY());
//        System.out.println("got here");
//        //if(Math.abs(hero.getX()-master.getX())<50&&Math.abs(hero.getY()-master.getY())<50)
//        if(Util.checkCollision(master2, hero) == true)
//        {
//            System.out.println("PINCHED");
//            currentPinch = ticks + 360;
//            lastPinch = ticks + 720;
//            isPinched = true;
//        }
    }
    public void render(SpriteBatch batch)
    {
//        if(!isPinched&&lastPinch==ticks)
//        {
//            TheMaster master2 = master;
//            TheMaster.texture = new Texture("Untitled.png");
//            master = new TheMaster(master2.getX(),master2.getY());
//        }

        if(isPinched&&currentPinch<ticks)
        {
            hero.setX(master.getX());
            hero.setY(master.getY());
        }
        ticks++;
        //animator.render(hero);
        batch.begin();

        for(Entity entity : entities)
            entity.update();

        for(int i=0;i<entities.size();i++)
            if(entities.get(i).removeFlag)
                entities.remove(i);

        for(Entity entity : entities)
            entity.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
