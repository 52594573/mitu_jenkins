package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.playingjoy.fanrabbit.R;

import cn.droidlover.xdroidmvp.kit.Kits;

/**
 * TODO: document your custom view class.
 */
public class MyTaskTimeProgressBar extends View {
    private int mTopTextColor = Color.parseColor("#FFBC07");
    private int mTopTextHintColor = Color.parseColor("#b3b3b3");
    private int mProgressBgColor = Color.parseColor("#D9D9D9");
    private int mProgressColor = Color.parseColor("#FFE12F");

    private float mTopTextSize = Kits.Dimens.dpToPxInt(getContext(), 13);
    private int mMaxValue = 4;
    private int mCurValue = -1;

    private String[] topText = new String[]{"经验+10", "经验+10", "经验+30", "经验+60"};

    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextWidth;
    private float mTextHeight;
    float textY;
    int top;
    float progressBegin;
    float progressEnd;
    float progressLength;
    RectF rectf;
    Bitmap whiteCircle;
    Bitmap yellowCircle;
    Bitmap location;

    private static final String TAG = "MyTaskTimeProgressBar";

    public MyTaskTimeProgressBar(Context context) {
        super(context);
        init(null, 0);
    }

    public MyTaskTimeProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyTaskTimeProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyTaskTimeProgressBar, defStyle, 0);


        mTopTextColor = a.getColor(
                R.styleable.MyTaskTimeProgressBar_topTextColor,
                mTopTextColor);
        mTopTextHintColor = a.getColor(
                R.styleable.MyTaskTimeProgressBar_topTextHintColor,
                mTopTextHintColor);
        mProgressBgColor = a.getColor(
                R.styleable.MyTaskTimeProgressBar_mProgressBgColor,
                mProgressBgColor);
        mProgressColor = a.getColor(
                R.styleable.MyTaskTimeProgressBar_mProgressColor,
                mProgressColor);
        mTopTextSize = a.getDimension(R.styleable.MyTaskTimeProgressBar_topTextSize, mTopTextSize);

        mMaxValue = a.getInt(R.styleable.MyTaskTimeProgressBar_maxValue, mMaxValue);
        mCurValue = a.getInt(R.styleable.MyTaskTimeProgressBar_curValue, mCurValue);

        a.recycle();
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

        whiteCircle = BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_task_pb_white);
        yellowCircle = BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_task_pb_yellow);
        location = BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_task_pb_location);

        mPaint = new Paint();
        mPaint.setColor(mProgressBgColor);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setTextSize(mTopTextSize);
        mTextPaint.setColor(mTopTextHintColor);
        String text = "经验+10";
        mTextWidth = mTextPaint.measureText(text);
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        top = rect.top;
        rectf = new RectF();

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();

        progressBegin = mTextWidth / 2;
        progressEnd = getWidth() - mTextWidth / 2;
        progressLength = progressEnd - progressBegin;

        drawTopText(canvas);

        mPaint.setColor(mProgressBgColor);
        rectf.left = progressBegin;
        rectf.top = -top + location.getHeight() / 2 + 30;
        rectf.right = progressEnd;
        rectf.bottom = -top + location.getHeight() / 2 + 38;
        canvas.drawRoundRect(rectf, 20, 20, mPaint);

        if (mCurValue > 1) {
//            Log.e(TAG, "onDraw: " + progressLength);
//            float progress = ((float) mCurValue / (mMaxValue - 1)) * progressLength;
//            Log.e(TAG, "onDraw: " + progress);
            rectf.left = progressBegin;
            rectf.top = -top + location.getHeight() / 2 + 30;
            rectf.right = (mCurValue - 1) * (progressLength / 3) + mTextWidth / 2;
            rectf.bottom = -top + location.getHeight() / 2 + 38;
            mPaint.setColor(mProgressColor);
            canvas.drawRoundRect(rectf, 20, 20, mPaint);
        }

        for (int i = 0; i < mMaxValue; i++) {
            if (i < mCurValue - 1) {
                canvas.drawBitmap(yellowCircle, i * (progressLength / 3) + mTextWidth / 2 - yellowCircle.getWidth() / 2, -top + 34 + location.getHeight() / 2 - yellowCircle.getHeight() / 2, mPaint);
            } else if (i == mCurValue - 1) {
                canvas.drawBitmap(location, i * (progressLength / 3) + mTextWidth / 2 - location.getWidth() / 2, -top + 34, mPaint);
            } else {
                canvas.drawBitmap(whiteCircle, i * (progressLength / 3) + mTextWidth / 2 - whiteCircle.getWidth() / 2, -top + 34 + location.getHeight() / 2 - whiteCircle.getHeight() / 2, mPaint);
            }
        }


    }

    private void drawTopText(Canvas canvas) {
        for (int i = 0; i < mMaxValue; i++) {
            if (i <= mCurValue - 1) {
                mTextPaint.setColor(mTopTextColor);
            } else {
                mTextPaint.setColor(mTopTextHintColor);
            }
            canvas.drawText(topText[i], i * (progressLength / 3), -top, mTextPaint);
        }
    }

    public void setCurValue(int curValue) {
        this.mCurValue = curValue;
        invalidate();
    }

}
