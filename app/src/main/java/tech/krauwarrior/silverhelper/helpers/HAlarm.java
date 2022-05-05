package tech.krauwarrior.silverhelper.helpers;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author Changming Mo
 * @version 1.0
 * @since 2022-05-05
 * Sound alarm helper
 * */
public class HAlarm {
    private MediaPlayer mediaPlayer;

    public void playAlarm(Context context, int sound) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, sound);
        mediaPlayer.start();
        mediaPlayer.setVolume(1f, 1f);
    }

    public void releaseSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
