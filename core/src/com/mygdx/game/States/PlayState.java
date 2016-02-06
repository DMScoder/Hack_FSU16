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
public class PlayState extends State{

    Random random = new Random();
    ScrollingBackground background;
    ArrayList <Entity> entities = new ArrayList<Entity>();
    int ledgeCount = 20;
    Hero hero;
    TheMaster master;

    public PlayState(GSM gsm)
    {
        super(gsm);
        makeTextures();
        background = new ScrollingBackground(this);
        hero = new Hero(200,200,this);
        master = new TheMaster(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        generateInitialLedges();
        entities.add(hero);
        entities.add(master);
    }

    public ArrayList<Entity> getEntity() {
        return entities;
    }

    private void generateInitialLedges()
    {
        Ledge previous = null;
        for(int i=0;i<ledgeCount;i++)
        {
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

        float ledgeX = previous.getX() + 100;
        float ledgeY = 300;

        if(previous.getY() == 300)
        {
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

            if(determine == 1)
                ledgeY = 200;
            else
                ledgeY = 800;
        }

        else if(previous.getY() == 500)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 400;
            else
                ledgeY = 600;
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

        return new Ledge(ledgeX,ledgeY);
    }

    public void makeTextures()
    {
        Pixmap pixmap = new Pixmap(50,10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Ledge.texture = new Texture(pixmap);
        pixmap.dispose();

        pixmap = new Pixmap(30,30, Pixmap.Format.RGBA8888);
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

    public void removeLedge()
    {

    }

    public void spawnMinion()
    {

    }

    public void freeze()
    {

    }

    public void reverseGravity()
    {
        hero.reverseGravity();
    }

    public void moveCommand(float x, float y, float z)
    {
        master.setX(x);
        master.setY(y);
    }
    public void circleCommand(boolean isclockwise, double sweptAngle, float x, float y)
    {
        if(isclockwise == true)
            master.setColor(Color.RED);
        else
            master.setColor(Color.BLUE);
    }
    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y)
    {
        if(direction.getX() >= 0)
            master.setColor(Color.GREEN);
        else
            master.setColor(Color.MAGENTA);

    }
    public void render(SpriteBatch batch)
    {
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
