package com.jmolsmobile.landscapevideocapture.recorder;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by emmanuelguther on 17/09/2015.
 */
public class TimerRecorder {
     int seconds = 0;


    public void timer(final Context context, final TextView tvTimer){

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (VideoRecorder.isRecording()) {
                        Thread.sleep(1000);
                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvTimer.setText(countTime(seconds++));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    private  String countTime(int secondsCount){
        String secondsStr;
        String minutesStr;
        //Calculate the seconds to display:
        int seconds = secondsCount %60;
        secondsCount -= seconds;
        //Calculate the minutes:
        long minutesCount = secondsCount / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;
        //Calculate the hours:
        long hoursCount = minutesCount / 60;
        //Build the String

       if (seconds<10){
           secondsStr= ":0"+seconds;
       }else{
           secondsStr= ":"+seconds;
       }
        if (minutes<10){
            minutesStr= "0"+minutes;
        }else{
            minutesStr= ""+minutes;
        }

        return  minutesStr + secondsStr;

    }
}
