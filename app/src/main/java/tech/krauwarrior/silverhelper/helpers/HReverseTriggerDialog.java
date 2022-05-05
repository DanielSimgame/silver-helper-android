package tech.krauwarrior.silverhelper.helpers;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Button;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.view.Gravity;

import androidx.annotation.NonNull;

import tech.krauwarrior.silverhelper.R;

/**
 * @author Changming Mo
 * @version 1.0
 * @since 2022-05-05
 * An override of the class AlertDialog shows the user dialogue with the text and counts down to cancel.
 * */
public class HReverseTriggerDialog extends Dialog implements HCountDown.onCountDownListener {

    private static long DURATION = 3000;
    private static long INTERVAL = 1000;

    private TextView textCountDown;
    private Button button;
//    private HAlarm alarm;
    private Vibrator vibrator;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation scaleAnimation;

    public HReverseTriggerDialog(@NonNull Context context) {
        super(context, R.style.HReverseTriggerDialog);
    }

    public HReverseTriggerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        // Dialog window settings
        Window window = getWindow();
        // Set the position of the dialog
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        // Set the layout of the dialog
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.dialog_reverse_trigger, null);
        setContentView(view);
        textCountDown = view.findViewById(R.id.text_countdown);

        // Alarm sound
//        alarm = new HAlarm();

        // Set the animation of the dialog
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(DURATION);
        scaleAnimation = new ScaleAnimation(0.5f, 1.5f, 0.5f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        HCountDown timerCountDown = new HCountDown(DURATION, INTERVAL, this);
        timerCountDown.startCountDown();

        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // Stop the alarm
//        alarm.releaseSound();

        if (alphaAnimation != null) {
            alphaAnimation.cancel();
            alphaAnimation = null;
        }
        if (scaleAnimation != null) {
            scaleAnimation.cancel();
            scaleAnimation = null;
        }
        if (vibrator != null) {
            vibrator.cancel();
            vibrator = null;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        // Play the sound and vibrate
//        alarm.playAlarm(getContext(), R.raw.alarm_countdown);
        vibrator.vibrate(100);

        textCountDown.setText(millisUntilFinished / INTERVAL + "");

        // Set AlphaAnimation
        alphaAnimation.setDuration(DURATION / 2);
        textCountDown.startAnimation(alphaAnimation);

        // Set ScaleAnimation
        scaleAnimation.setDuration(DURATION / 2);
        textCountDown.startAnimation(scaleAnimation);
    }

    @Override
    public void onFinish() {
        dismiss();
    }
}
