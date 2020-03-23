package com.mygdx.game.model.utils;

public class Direction {
    public final Point point;
    public final int rotation;

    public Direction(Point point, int rotation) {
        this.point = point;
        this.rotation = rotation;
    }

    public Point getPoint() {
        return point;
    }

    public int getRotation() {
        return rotation;
    }
}
