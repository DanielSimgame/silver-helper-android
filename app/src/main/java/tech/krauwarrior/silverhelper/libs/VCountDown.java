package tech.krauwarrior.silverhelper.libs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import tech.krauwarrior.silverhelper.R;

/**
 * @author Changming Mo
 * @version 1.0
 * @since 2022-05-05
 */
public class VCountDown extends View {

    // Paints
    private Paint mCirclePaint;
    private Paint mRingPaint;
    private Paint mRingBgPaint;
    private Paint mTextPaint;

    // Colors
    private int mCircleColor;
    private int mRingColor;
    private int mRingBgColor;

    // Circles
    private float mRadius;
    private float mRingRadius;
    private float mProgressRadius;
    private float mBorderWidth;
    private float mProgressWidth;
    private int mXCenter;
    private int mYCenter;

    // Text
    private float mTextWidth;
    private float mTextHeight;
    private float mTextSize;
    private int mTextColor;

    // Progress, it's countdown, so it's always decreasing
    private long mMaxProgress = 100;
    private long mCurrentProgress = 100;

    public VCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Customized attributes

    }

    /**
     * Initialize the view attributes
     *
     * @param context The context
     * @param attrs   The attributes
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        // Get customized attributes
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CountDownView, 0, 0);
        mRadius = typedArray.getDimension(R.styleable.CountDownView_radius, 80);
        mBorderWidth = typedArray.getDimension(R.styleable.CountDownView_borderWidth, 10);
        mProgressWidth = typedArray.getDimension(R.styleable.CountDownView_progressWidth, 10);
        mCircleColor = typedArray.getColor(R.styleable.CountDownView_circleColor, 0xFFFFFFFF);
        mRingColor = typedArray.getColor(R.styleable.CountDownView_ringColor, 0xFFFFFFFF);
        mRingBgColor = typedArray.getColor(R.styleable.CountDownView_ringBgColor, 0xFFFFFFFF);
        mTextColor = typedArray.getColor(R.styleable.CountDownView_textColor, 0xFFFFFFFF);
        mTextSize = typedArray.getDimension(R.styleable.CountDownView_textSize, 12);
        mMaxProgress = typedArray.getInt(R.styleable.CountDownView_maxProgress, 10);
        mCurrentProgress = typedArray.getInt(R.styleable.CountDownView_currentProgress, 10);

        mRingRadius = mRadius + mBorderWidth / 2;
        mProgressRadius = mRadius - mBorderWidth / 2;
        // typedArray.recycle();
    }

    /**
     * Initialize the paints
     */
    private void initPaints() {
        // Inner circle paint
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        // Outer ring background paint
        mRingBgPaint = new Paint();
        mRingBgPaint.setAntiAlias(true);
        mRingBgPaint.setColor(mRingBgColor);
        mRingBgPaint.setStyle(Paint.Style.STROKE);
        mRingBgPaint.setStrokeWidth(mBorderWidth);

        // Outer ring paint
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mBorderWidth);
        mRingPaint.setStrokeCap(Paint.Cap.SQUARE);

        // Text paint
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        // Draw the inner circle
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        // Draw the outer ring background
        RectF ovalOuter = new RectF();
        ovalOuter.left = (mXCenter - mRingRadius);
        ovalOuter.top = (mYCenter - mRingRadius);
        ovalOuter.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        ovalOuter.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
        // The arc of the outer ring, start angle, sweep angle, whether to show the radius line
        canvas.drawArc(ovalOuter, 0, 360, false, mRingBgPaint);

        // Draw the outer ring
        if (mCurrentProgress > 0) {
            RectF ovalInner = new RectF();
            ovalInner.left = (mXCenter - mProgressRadius - (mBorderWidth - mProgressWidth) / 2);
            ovalInner.top = (mYCenter - mProgressRadius - (mBorderWidth - mProgressWidth) / 2);
            ovalInner.right = mProgressRadius * 2 + (mXCenter - mProgressRadius) + (mBorderWidth - mProgressWidth) / 2;
            ovalInner.bottom = mProgressRadius * 2 + (mYCenter - mProgressRadius) + (mBorderWidth - mProgressWidth) / 2;
            canvas.drawArc(
                    ovalInner,
                    -90,
                    ((float) (mMaxProgress - mCurrentProgress) / mMaxProgress) * 360,
                    false, mRingPaint
            );

            // Draw the progress text
            long count = mCurrentProgress;
            String text = String.valueOf(count);
            mTextWidth = mTextPaint.measureText(text, 0, text.length());
            canvas.drawText(
                    text,
                    mXCenter - mTextWidth / 2,
                    mYCenter + mTextHeight / 4,
                    mTextPaint
            );
        }
    }

    /**
     * Set the progress value
     * @param maxProgress The max progress value
     * @param currentProgress The current progress value
     * */
    public void setProgress(long maxProgress, long currentProgress) {
        mMaxProgress = maxProgress;
        mCurrentProgress = currentProgress;
        // Invalidate the view (refresh)
        postInvalidate();
    }
}
