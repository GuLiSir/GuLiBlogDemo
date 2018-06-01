package demo.guli.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import demo.guli.absInterface.EffectListener;
import demo.guli.absInterface.ItemEffect;
import demo.guli.utils.LogLazy;

/**
 * 线条动画View
 */
public class RunningAnimationView extends View implements ItemEffect {


    private EffectListener listener;

    public RunningAnimationView(Context context) {
        super(context);
    }

    public RunningAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RunningAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RunningAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //存储各个效果的list
    private final List<DrawItem> drawItemList = new ArrayList<>();

    //开始时间
    private long timeMillis;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (state != SelfEffectState.ANIMATION) {
            return;//没有在执行动画的时候,就直接不绘制
        }
        long millisCur = System.currentTimeMillis();
        //当前动画执行的时间
        long detailTime = millisCur - timeMillis;

        LogLazy.i("当前动画执行的时间 " + detailTime);

        //第一个item到当前item的总时间
        long curItemTotalTime = 0;
        for (DrawItem drawItem : drawItemList) {
            int duration = drawItem.getDuration();
            curItemTotalTime = curItemTotalTime + duration;
            int indexOf = drawItemList.indexOf(drawItem);
            if (curItemTotalTime <= detailTime) {
                LogLazy.i("绘制全部:" + indexOf);
                drawItem.drawSelfModule(canvas, 1.0f);//绘制全部
            } else {
                long huanyuan = detailTime - (curItemTotalTime - duration);//当前过去的时间,减去执行的总时间,得出当前item播放动画的时间
                float v = huanyuan * 1.0f / duration * 1.0f;
                LogLazy.i("部分:" + indexOf + "  百分比:" + v + "  过了时间" + huanyuan);
                drawItem.drawSelfModule(canvas, v);//绘制全部
                break;
            }
        }
        if (detailTime < curItemTotalTime) {
            invalidate();//绘制的时间还没到需要执行的总时间,继续绘制
        } else {
            LogLazy.i("全部绘制结束");
            if (listener != null) {
                listener.onEffectEnd();
            }
        }
    }

    public boolean add(DrawItem drawItem) {
        return drawItemList.add(drawItem);
    }

    public void test() {
        Point line2Start = new Point(596, 200);
        Point line2End = new Point(820, 200);
        add(new LineItem(new Point(520, 250), line2Start, 200));
        add(new LineItem(line2Start, line2End, 200));

        add(new DotItem(new Point(line2End.X + 8, 200), 8));
    }

    private SelfEffectState state = SelfEffectState.NOTHINK;

    @Override
    public void prepare() {
        state = SelfEffectState.PREPARE;
        invalidate();
    }


    @Override
    public void startEffect(EffectListener listener) {
        state = SelfEffectState.ANIMATION;
        this.listener = listener;
        timeMillis = System.currentTimeMillis();
        invalidate();
    }

    @Override
    public void endEffect() {

    }

    //绘制类型
    private interface DrawItem {
        /**
         * 绘制动画效果
         * @param canvas
         * @param percent 绘制的进度,百分比0.0 - 1.0
         */
        void drawSelfModule(Canvas canvas, float percent);

        /**
         * 返回需要绘制的时长
         * @return
         */
        int getDuration();
    }

    //绘制线条item
    public static class LineItem implements DrawItem {

        private final Point startPoint;
        private final Point endPoint;
        private final int duration;
        private final Paint paint = new Paint();

        /**
         *
         * @param startPoint 起始坐标点
         * @param endPoint 终止坐标点
         * @param duration 时间,单位ms
         */
        public LineItem(Point startPoint, Point endPoint, int duration) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.duration = duration;
            paint.setColor(Color.BLUE);
            paint.setAlpha(mAlphaPaint);
            paint.setStrokeWidth(3);//线粗
            paint.setAntiAlias(true);//抗锯齿
        }

        @Override
        public void drawSelfModule(Canvas canvas, float percent) {
            canvas.drawLine(startPoint.X,
                    startPoint.Y,
                    startPoint.X + (endPoint.X - startPoint.X) * percent,
                    startPoint.Y + (endPoint.Y - startPoint.Y) * percent
                    , paint);
        }

        @Override
        public int getDuration() {
            return duration;
        }

    }

    private static int mAlphaPaint = (int) (0.7 * 255);

    public static class DotItem implements DrawItem {

        private final Point point;
        //圆半径
        private int radius;
        private final Paint paint = new Paint();

        public DotItem(Point point, int radius) {
            this.point = point;
            this.radius = radius;
            paint.setColor(Color.BLUE);
            paint.setAlpha(mAlphaPaint);
//            paint.setStrokeWidth(3);//线粗
            paint.setAntiAlias(true);//抗锯齿
        }

        @Override
        public void drawSelfModule(Canvas canvas, float percent) {
            canvas.drawCircle(point.X, point.Y, radius, paint);
        }

        @Override
        public int getDuration() {
            return 32;
        }
    }

    public static class Point {
        public final int X;
        public final int Y;

        public Point(int x, int y) {
            X = x;
            Y = y;
        }
    }
}
