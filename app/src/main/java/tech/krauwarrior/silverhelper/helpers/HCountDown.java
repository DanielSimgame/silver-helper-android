package tech.krauwarrior.silverhelper.helpers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * @author Changming Mo
 * @version 1.0
 * Count down timer helper
 * */
public class HCountDown {
    private static final int MSG = 1;
    /**
     * Count down duration (ms)
     */
    private final long mMillisInFuture;
    /**
     * Count down interval (ms, default 1000ms)
     */
    private final long mCountdownInterval;
    /**
     * Is the timer being cancelled
     */
    private boolean mCancelled = false;
    /**
     * Count down duration (ms)
     * */
    private long mDuration;

    public interface onCountDownListener {
        /**
         * Function on per tick of count down
         */
        void onTick(long millisUntilFinished);

        /**
         * Function on count down event finish.
         */
        void onFinish();

    }

    private onCountDownListener mOnCountDownListener;

    public HCountDown(
            long mMillisInFuture,
            long mCountdownInterval,
            onCountDownListener mOnCountDownListener
    ) {
        this.mMillisInFuture = mMillisInFuture;
        this.mCountdownInterval = mCountdownInterval;
        this.mOnCountDownListener = mOnCountDownListener;
    }

    /**
     * Stop and clear the timer.
     * */
    public synchronized final void stopCountDown() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /**
     * Start the timer
     * */
    public synchronized final HCountDown startCountDown() {
        mCancelled = false;
        mDuration = mMillisInFuture;

        if (mMillisInFuture <= 0) {
            if (mOnCountDownListener != null) {
                mOnCountDownListener.onFinish();
            }
            return this;
        }
        if (mOnCountDownListener != null) {
            mOnCountDownListener.onTick(mDuration);
        }
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG), mCountdownInterval);
        return this;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mCancelled) {
                return;
            }
            mDuration -= mCountdownInterval;
            if (mDuration <= 0) {
                if (mOnCountDownListener != null) {
                    mOnCountDownListener.onFinish();
                }
            } else {
                if (mOnCountDownListener != null) {
                    mOnCountDownListener.onTick(mDuration);
                }
                sendMessageDelayed(obtainMessage(MSG), mCountdownInterval);
            }
        }
    };

    public void setCountDownListener(onCountDownListener mOnCountDownListener) {
        this.mOnCountDownListener = mOnCountDownListener;
    }
}
