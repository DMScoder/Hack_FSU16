package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {

    Random random = new Random();
    ArrayList<Entity> entities = new ArrayList<Entity>();
    int ledgeCount = 20;
    Hero hero;
    TheMaster master;
    TheMaster master2;
    int pressCount = 0;
    boolean isFrozen = false;
    //boolean brokenFree = true;

    int bombCount = 10;

    boolean isPinched = false;
    long lastPinch = 0;
    long ticks = 0;

    public PlayState(GSM gsm) {
        super(gsm);
        //animator.create();
        makeTextures();
        hero = new Hero(200, 200, this);
        master = new TheMaster(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        master.setColor(Color.WHITE);
        generateInitialLedges();
        //entities.add();
        entities.add(hero);
        entities.add(master);
        generateBomb();
    }

    public ArrayList<Entity> getEntity() {
        return entities;
    }

    private void generateBomb() {

        Random random = new Random(500);

        for (int i = 0; i < 10; i++) {
            int d = random.nextInt(1500);
            int f = random.nextInt(500);
            entities.add(new Bomb((750 + d), Gdx.graphics.getHeight() + f, this));
        }

    }

    private void generateInitialLedges() {
        Ledge previous = null;
        for (int i = 0; i < ledgeCount; i++) {
            Ledge ledge = generateLedge(previous);
            previous = ledge;
            entities.add(ledge);
        }
    }

    private Ledge generateLedge(Ledge previous) {
        if (previous == null) {
            return new Ledge(hero.getX(), hero.getY());
        }

        float ledgeX = previous.getX() + 125;
        float ledgeY = 300;

        if (previous.getY() == 300) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 200;
            else
                ledgeY = 400;
        } else if (previous.getY() == 100)
            ledgeY = 200;

        else if (previous.getY() == 400) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 200;
            else
                ledgeY = 800;
        } else if (previous.getY() == 500) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 400;
        } else if (previous.getY() == 600) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 500;
            else
                ledgeY = 700;
        } else if (previous.getY() == 700) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 200;
            else
                ledgeY = 400;
        } else if (previous.getY() == 800) {
            int determine = random.nextInt(2);

            if (determine == 1)
                ledgeY = 700;
            else
                ledgeY = 900;
        } else if (previous.getY() == 900)
            ledgeY = 800;

        return new Ledge(ledgeX, ledgeY);
    }


    public void powerUp() {
        isFrozen = true;
        master.setColor(Color.CYAN);
    }

    public void makeTextures() {
        Pixmap pixmap = new Pixmap(100, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Ledge.texture = new Texture(pixmap);
        pixmap.dispose();

        pixmap = new Pixmap(15, 15, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.PINK);
        pixmap.fill();
        Bomb.texture = new Texture(pixmap);
        pixmap.dispose();

        pixmap = new Pixmap(20, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        Hero.texture = new Texture(pixmap);
        pixmap.dispose();

        pixmap = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.PURPLE);
        pixmap.fill();
        Powerup.texture = new Texture(pixmap);
        pixmap.dispose();

        TheMaster.texture = new Texture("Untitled.png");

        pixmap = new Pixmap(30,30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        Powerup.texture = new Texture(pixmap);
        pixmap.dispose();

        TheMaster.texture = new Texture("Untitled.png");
    }

    @Override
    public void handleInput() {
    }

    public void reverseGravity() {
        hero.reverseGravity();
    }

    public void moveCommand(float x, float y, float z) {
        if(isFrozen = false)
        {
            master.setX(x);
            master.setY(y);
        }
    }

    public void circleCommand(boolean isclockwise, double sweptAngle, float x, float y) {
            reverseGravity();
    }

    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y) {
    }

    public void keyPress(com.leapmotion.leap.Vector direction) {

        if (direction.equals(0))
            ;
        else {
            if (pressCount <= 20)
                System.out.println("Press Count is: " + pressCount++);
            else {
                master.setColor(Color.WHITE);
                pressCount = 0;
                isFrozen = false;
            }
        }
    }

    public void pinchCommand() {

        if(lastPinch > ticks)
            return;

        lastPinch = ticks + 360;

    }

    public void render(SpriteBatch batch)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ticks++;
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2,Color.TEAL,Color.TEAL,Color.NAVY,Color.NAVY);
        renderer.rect(0,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Color.ORANGE,Color.ORANGE,Color.NAVY,Color.NAVY);
        renderer.end();
        batch.begin();

        for(Entity entity : entities)
            entity.update();

        for(int i=0;i<entities.size();i++)
            if(entities.get(i).removeFlag)
                entities.remove(i);

        for(Entity entity : entities)
            entity.render(batch);

        if(random.nextInt(5)==0)
        {
            System.out.println("Made");
            Powerup powerup = new Powerup(1500,random.nextInt(700));
            entities.add(powerup);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
