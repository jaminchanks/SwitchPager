package jamin.github.com.switchpager.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import jamin.github.com.switchpager.R;
import jamin.github.com.switchpager.bean.Line;
import jamin.github.com.switchpager.utils.LineUtil;
import jamin.github.com.switchpager.utils.ScreenUtils;

/**
 *  用于控制在悬浮窗上绘制的内容
 * Created by jaminchanks on 2016/9/25.
 */

public class CurlPage extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = CurlPage.class.getSimpleName();

    private Context mContext;

    private boolean mIsCurling; //是否处于翻页动画状态

    private Bitmap mBitmapBackPage; //页的背面

    private PointF mPointOrigin; //坐标起点，屏幕左下角
    private PointF mOnTouchPointA = new PointF(400, 800); //手指挪动的位置
    private PointF mBezierControl1_B; //第一条贝塞尔曲线的控制点
    private PointF mBezierStar1_E; //第一条贝塞尔曲线的起点
    private PointF mBezierEnd1_R; //第一条贝塞尔曲线的终点

    private PointF mBezierControl2_C; //第二条贝塞尔曲线...
    private PointF mBezierStart2_F;
    private PointF mBezierEnd2_T;

    private Path mBackPath; //页的背部区域
    private Path mForePath; //页的前景区域

    private Bitmap mForeBitmap; // 前景图
    private Bitmap mBackBitmap; //页背图

    private SurfaceHolder mSurfaceHolder;
    private ViewGroup mRootView;

    public CurlPage(Context context) {
        super(context);
    }

    public CurlPage(Context context, AttributeSet attrs, Context mContext) {
        super(context, attrs);
        this.mContext = mContext;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initSomething();
        calculateBezierPoint();
        drawForePageArea(canvas, mForeBitmap, mForePath);
    }

    //// TODO: 2016/9/26 need something to do
    private void initSomething() {
        mForeBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.wallpaper_one_piece);
    }


    /**
     * 绘制页的前景
     */
    private void drawForePageArea(Canvas canvas, Bitmap bitmap, Path path) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        mForePath.reset();
        mForePath.moveTo(mBezierStar1_E.x, mBezierStar1_E.y);
        mForePath.quadTo(mBezierControl1_B.x, mBezierControl1_B.y, mBezierEnd1_R.x, mBezierEnd1_R.y);
        mForePath.lineTo(mOnTouchPointA.x, mOnTouchPointA.y);
        mForePath.lineTo(mBezierEnd2_T.x, mBezierEnd2_T.y);
        mForePath.quadTo(mBezierControl2_C.x, mBezierControl2_C.y, mBezierStart2_F.x, mBezierStart2_F.y);
        mForePath.lineTo(mPointOrigin.x, mPointOrigin.y);
        mForePath.close();

        canvas.save();
        canvas.clipPath(path, Region.Op.XOR);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    /**
     * 绘制页的背部
     */
    private void drawBackPageArea(Canvas canvas, Bitmap bitmap, Path path) {

    }

    /**
     * 计算图形中的各个点坐标
     */
    private void calculateBezierPoint() {
        float mScreenHeight = ScreenUtils.getScreenHeight(mContext);

        //获取到屏幕的左下角坐标，原点O
        mPointOrigin = new PointF(0, mScreenHeight);

        //触摸点与原点所成的直线OA
        Line lineOA = new Line(mPointOrigin, mOnTouchPointA);

        //求与直线OA的垂直平分线BC
        Line lineBC = LineUtil.getVerticalSplitLine(mPointOrigin, mOnTouchPointA);

        //求出直线BC分别和横坐标和纵坐标的交点,B点和C点的坐标
        mBezierControl2_C = LineUtil.getPointOnXCoordinateAxes(mContext, lineBC);
        mBezierControl1_B = LineUtil.getPointOnYCoordinateAxes(mContext, lineBC);

        //触摸点A与坐标原点O之间的中点D
        PointF centerPoint_D =  LineUtil.getCenterPoint(mPointOrigin, mOnTouchPointA);
        //求出AD的垂直平分线EF
        Line lineEF = LineUtil.getVerticalSplitLine(mOnTouchPointA, centerPoint_D);

        //求出直线EF分别和横坐标和纵坐标的交点
        mBezierStart2_F = LineUtil.getPointOnXCoordinateAxes(mContext, lineEF);
        mBezierStar1_E = LineUtil.getPointOnYCoordinateAxes(mContext, lineEF);

        //求出直线EF的中点G
        //// FIXME: 2016/9/26 G点好像没什么用
        PointF centerPoint_G = LineUtil.getCenterPoint(mOnTouchPointA, centerPoint_D);

        mBezierEnd1_R = LineUtil.getCrossPoint(mBezierStar1_E, mBezierStart2_F, mPointOrigin, mOnTouchPointA);
        mBezierEnd2_T = LineUtil.getCrossPoint(mBezierStar1_E, mBezierStart2_F, mOnTouchPointA, mBezierControl2_C);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
