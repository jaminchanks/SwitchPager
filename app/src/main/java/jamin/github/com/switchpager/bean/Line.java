package jamin.github.com.switchpager.bean;

import android.graphics.PointF;

/**
 * 直线类
 * Created by jaminchanks on 2016/9/25.
 */

public class Line {
    public float k; //斜率
    public float b; //截距

    /**
     * 已知斜率和截距求直线方程
     * @param k
     * @param b
     */
    public Line(float k, float b) {
        this.k = k;
        this.b = b;
    }

    /**
     * 已知两点求直线方程
     * @param point1
     * @param point2
     */
    public Line(PointF point1, PointF point2) {
        this.k = getK(point1, point2);
        this.b = getB(point1, point2);
    }


    /**
     * 已知两点求斜率: k = (y1 - y2) / (x1 - x2)
     * @return
     */
    private float getK(PointF point1, PointF point2) {
        return (point1.y - point2.y) / (point1.x - point2.x);
    }

    /**
     * 已知两点求截距: b = (x2 * y1 - x1 * y2) / (x2 - x1)

     * @return
     */
    private float getB(PointF point1, PointF point2) {
        return (point2.x * point1.y - point1.x * point2.y) / (point2.x - point1.x);
    }

    /**
     * 已知点直线方程和y，求x: x = (y - b) / k
     * @param y
     * @return
     */
    public float getX(float y) {
        return (y - b) / k;
    }

    /**
     * 已知直线方程和x, 求y: y = k * x + b
     * @param x
     * @return
     */
    public float getY(float x) {
        return k * x + b;
    }
}
