package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.playingjoy.fanrabbit.R;

import cn.droidlover.xdroidmvp.kit.Kits;

/**
 * 自定义进度条
 *
 * @author Morphine
 * @date 2018-3-29
 */
public class FilletProgressBarView extends View {
    private int mProgressColor = Color.parseColor("#FFBC07");
    private int mBackgroundColor = Color.parseColor("#F9EBEE");
    private int mProgressTextColor = Color.WHITE;
    private int mBackgroundTextColor = Color.parseColor("#FDCE67");
    private int mProgress = 0;
    private float roundSize = Kits.Dimens.dpToPx(getContext(), 3);
    private float mTextSize = Kits.Dimens.dpToPx(getContext(), 10);


    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextWidth;
    private float mTextHeight;
    CornerPathEffect pathEffect;

    public FilletProgressBarView(Context context) {
        super(context);
        init(null, 0);
    }

    public FilletProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FilletProgressBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FilletProgressBarView, defStyle, 0);

        mProgressColor = a.getColor(
                R.styleable.FilletProgressBarView_progress_color,
                mProgressColor);
        mProgressTextColor = a.getColor(
                R.styleable.FilletProgressBarView_progress_text_color,
                mProgressTextColor);
        mBackgroundColor = a.getColor(
                R.styleable.FilletProgressBarView_background_color,
                mBackgroundColor);
        mBackgroundTextColor = a.getColor(
                R.styleable.FilletProgressBarView_background_text_color,
                mBackgroundTextColor);
        mProgress = a.getInt(R.styleable.FilletProgressBarView_progress_value, mProgress);

        mTextSize = a.getDimension(R.styleable.FilletProgressBarView_text_size, mTextSize);

        a.recycle();
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(mTextSize);
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        pathEffect = new CornerPathEffect(roundSize);
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            widthSize = getResources().getConfiguration().screenWidthDp;
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            heightSize = Kits.Dimens.dpToPxInt(getContext(), 20);
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        String text = "剩余 " + (100 - mProgress) + "%";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTextWidth = mTextPaint.measureText(text);
        mTextHeight = rect.height();
        float dy = (fm.descent - fm.ascent) / 2 - fm.descent;
        float textY = getHeight() / 2 + dy;

        Path path = new Path();
        path.moveTo(10, 0);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - 10, getHeight());
        path.lineTo(0, getHeight());
        path.close();

        mPaint.setColor(mBackgroundColor);
        mPaint.setPathEffect(pathEffect);
        canvas.drawPath(path, mPaint);
        mTextPaint.setColor(mBackgroundTextColor);
        canvas.drawText(text, getWidth() / 2 - mTextWidth / 2, textY, mTextPaint);


        canvas.save();
        canvas.clipRect(0, 0, getWidth() * (mProgress / 100.0f), getWidth());
        mPaint.setColor(mProgressColor);
        canvas.drawPath(path, mPaint);

        mTextPaint.setColor(mProgressTextColor);
        canvas.drawText(text, getWidth() / 2 - mTextWidth / 2, textY, mTextPaint);
        canvas.restore();
    }

}
