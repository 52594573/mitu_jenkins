package com.playingjoy.fanrabbit.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;

import com.playingjoy.fanrabbit.R;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Author: Ly
 * Data：2018/3/29-16:25
 * Description:
 */
public class ProgressTextView extends android.support.v7.widget.AppCompatTextView {

    private int mBorderWidth = 4;
    private int mBorderWidthColor = Color.parseColor("#FFe12f");
    private int mCornersize = 40; //我们默认以px为单位
    private Paint mCornerPaint, mProgressPaint; //边框画笔   文字我们只用系统的 TextView
    private int mProgress = 0;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    public ProgressTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProgressTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressTextView);
        mProgress = a.getInteger(R.styleable.ProgressTextView_progress, 0);
        a.recycle();

        mCornerPaint = new Paint();
        mProgressPaint = new Paint();
        mCornerPaint.setAntiAlias(true);
        mCornerPaint.setDither(true);
        mCornerPaint.setStrokeWidth(mBorderWidth);
        mCornerPaint.setStyle(Paint.Style.STROKE); //实心 只画边框也画心
        mCornerPaint.setColor(mBorderWidthColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        doDrawBorder(canvas);
        doDrawProgress(canvas);
        doDrawLeft(canvas);
        super.onDraw(canvas);
    }

    /**
     * 画边框
     *
     * @param canvas
     */
    private void doDrawBorder(Canvas canvas) {
        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, getMeasuredWidth() - mBorderWidth, getMeasuredHeight() - mBorderWidth);
        canvas.drawRoundRect(rectF, mCornersize, mCornersize, mCornerPaint);
    }

    /**
     * 画进度条
     *
     * @param canvas
     */
    private void doDrawProgress(Canvas canvas) {
        canvas.save();
        if (mProgress < 100) {
            int[] colors = new int[]{0xffFFe12f, 0x59FFe12f};
            LinearGradient mLinearGradient = new LinearGradient(0, 0, (getMeasuredWidth() - mBorderWidth) * (mProgress / 100f), 0,
                    colors,
                    null, Shader.TileMode.REPEAT);
            mProgressPaint.setShader(mLinearGradient);
        } else {
            mProgressPaint.setColor(Color.parseColor("#FFe12f"));
        }
        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, (getMeasuredWidth() - mBorderWidth), getMeasuredHeight() - mBorderWidth);
        RectF rectFClipRect = new RectF(mBorderWidth / 2, mBorderWidth / 2, (getMeasuredWidth() - mBorderWidth) * (mProgress / 100f), getMeasuredHeight() - mBorderWidth);
        canvas.clipRect(rectFClipRect);
        canvas.drawRoundRect(rectF, 50, 50, mProgressPaint);
        canvas.restore();
    }

    /**
     * 把drawableLeft换到屏幕中间 缺点：gravity要设置为center_vertical
     *
     * @param canvas
     */
    private void doDrawLeft(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawableLeft != null) {
            float textWidth = getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth;
            drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        XLog.e("the progress is" + progress);
        mProgress = progress;
        invalidate();
    }

    public void setProgress(int soFarBytes, int totalBytes) {
        mProgress = (int) ((soFarBytes / (float) totalBytes) * 100);
        invalidate();
    }
}
