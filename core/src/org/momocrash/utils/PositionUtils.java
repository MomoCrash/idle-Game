package org.momocrash.utils;

public class PositionUtils {

    /**
     * Check if object A (x, y, xMax, yMax)
     * as pixel into object B (x1, y1, xMax1, yMax2)
     *
     * @param x x coordinate A
     * @param y y coordinate A
     * @param xMax x coordinate A
     * @param yMax y coordinate A
     * @param x1 x1 coordinate B
     * @param y1 y1 coordinate B
     * @param xMax1 xMax1 coordinate B
     * @param yMax1 yMax1 coordinate B
     * @return if two object had pixel inside each other
     */
    public static boolean objectIsInsideOther(float x, float y, float xMax, float yMax, float x1, float y1, float xMax1, float yMax1) {

        return ((x >= x1 && x <= xMax1 && y >= y1 && y <= yMax1)
                || (xMax >= x1 && xMax <= xMax1 && y >= y1 && y <= yMax1)
                || (x >= x1 && x <= xMax1 && yMax >= y1 && yMax <= yMax1)
                || (xMax >= x1 && xMax <= xMax1 && yMax >= y1 && yMax <= yMax1));

    }

    public static boolean pointIsInsideObject(float x, float y, float x1, float y1, float xMax1, float yMax1) {

        return (x >= x1 && x <= xMax1 && y >= y1 && y <= yMax1);

    }

}
