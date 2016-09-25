package jamin.github.com.switchpager.utils;

import android.content.Context;
import android.graphics.PointF;

import jamin.github.com.switchpager.bean.Line;

/**
 * 直线的计算相关的工具类
 * Created by jaminchanks on 2016/9/26.
 */

public class LineUtil {
    /**
     * 计算两条直线的交点
     *  其中，x = (b2 - b1) / (k1 - k2)
     *  y 则根据y = kx + b 代入
     * @return
     */
    public static PointF getCrossPoint(PointF point1, PointF point2, PointF point3, PointF point4) {
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point3, point4);

        float crossX = (line2.b - line1.b) / (line1.k - line2.k);
        float crossY = line1.k * crossX + line1.b;
        return new PointF(crossX, crossY);
    }

    /**
     * 求出线段的垂直平分线
     * @param point1
     * @param point2
     * @return
     */
    public static Line getVerticalSplitLine(PointF point1, PointF point2) {
        Line line = new Line(point1, point2);
        PointF centerPoint = new PointF( (point1.x + point2.x) / 2, (point1.y + point2.y) / 2);
        float k = -1 / line.k;
        float b = centerPoint.y - k * centerPoint.x;
        return new Line(k, b);
    }

    /**
     * 求出线段的中点
     * @param point1
     * @param point2
     * @return
     */
    public static PointF getCenterPoint(PointF point1, PointF point2) {
        return new PointF((point1.x + point2.x) / 2, (point1.y + point2.y) / 2);
    }

    /**
     * 获取直线与横坐标的交点
     * @param context
     * @param line
     * @return
     */
    public static PointF getPointOnXCoordinateAxes(Context context, Line line) {
        float screenHeight = ScreenUtils.getScreenHeight(context);
        return new PointF(line.getX(screenHeight), screenHeight);
    }

    /**
     * 获取直线与纵坐标的交点
     * @param context
     * @param line
     * @return
     */
    public static PointF getPointOnYCoordinateAxes(Context context, Line line) {
        return new PointF(0, line.getY(0));
    }

    /**
     * 已知点直线方程和y，求x: x = (y - b) / k
     * @param line
     * @param y
     * @return
     */
    public float getX(Line line, float y) {
        return (y - line.b) / line.k;
    }

    /**
     * 已知直线方程和x, 求y: y = k * x + b
     * @param line
     * @param x
     * @return
     */
    public float getY(Line line, float x) {
        return line.k * x + line.b;
    }
}
