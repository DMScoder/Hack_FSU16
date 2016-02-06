package com.mygdx.game;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Util {

    public static boolean checkCollision(Entity one, Entity two)
    {
        return one.getBoundingRectangle().overlaps(two.getBoundingRectangle());
    }
}
