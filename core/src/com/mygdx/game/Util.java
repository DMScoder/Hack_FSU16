package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Util {

    public static boolean checkCollision(Entity one, Entity two)
    {
        return one.getBoundingRectangle().overlaps(two.getBoundingRectangle());
    }

    public static boolean checkCollision2(Entity one, Entity two) {
        Rectangle rectangle1 = new Rectangle(one.getWidth(), one.getHeight(), one.getX(), one.getY());
        Rectangle rectangle2 = new Rectangle(two.getWidth(), two.getHeight(), two.getX(), two.getY());
        return rectangle1.overlaps(rectangle2);
    }
}
