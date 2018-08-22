package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Author: Ly
 * Data：2018/4/11-11:40
 * Description:
 */
public class BorderImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaintForBorder;
    private int mW, mH;
    private boolean isShowBorder = false;

    public BorderImageView(Context context) {
        super(context);
        init();
    }

    public BorderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintForBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintForBorder.setColor(Color.parseColor("#999999"));
        mPaintForBorder.setStrokeWidth(2);
        mPaintForBorder.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mW = w;
        mH = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowBorder) {
            doDrawBorder(canvas);
        }
    }

    /**
     * 加多一个边框
     *
     * @param canvas
     */
    private void doDrawBorder(Canvas canvas) {
        RectF rectF = new RectF(0, 0, mH, mW);
        canvas.drawRect(rectF, mPaintForBorder);
    }

    public void isShowBorder(boolean isShow) {
        isShowBorder = isShow;
        invalidate();
    }
}
