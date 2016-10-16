package com.example.davd.myfirstvcsproject;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

// Under GitHub control as of 16/10/16

public class MainActivity extends AppCompatActivity {

    RadioGroup selector;
    long startTime, endTime;

    MediaPlayer[] mySound = new MediaPlayer[10];
    int select = 0, pause_on = 1, replay = 0;
    TextView opt, optT;
    String ans;

    @Override
    protected void onPause(){
        super.onPause();
        mySound[select].release();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound[0] = MediaPlayer.create(this, R.raw.axelf);
        mySound[1] = MediaPlayer.create(this, R.raw.boardwalk2);
        mySound[2] = MediaPlayer.create(this, R.raw.pianoman);
        mySound[3] = MediaPlayer.create(this, R.raw.alone);
        mySound[4] = MediaPlayer.create(this, R.raw.hollywood);
        mySound[5] = MediaPlayer.create(this, R.raw.lettheriverrun);
        mySound[6] = MediaPlayer.create(this, R.raw.saturdaysight);
        mySound[7] = MediaPlayer.create(this, R.raw.takeit6);

        selector = (RadioGroup)(findViewById(R.id.selector));
        opt = (TextView)findViewById(R.id.textView);
        opt.setText("Press PLAY");
        optT = (TextView)findViewById(R.id.textView3);

    }

    public void playMusic(View view){
        //set select to rand 0 to 8
        if (pause_on==1)                // set a no pause state
            pause_on = 0;
            if (replay == 0) {
                select = (int) (Math.random() * 8);
                startTime = System.currentTimeMillis();
                mySound[select].start();
                replay++;
                optT.setText("You can press play to restart ");

            }
            else if(replay==1||replay==2){
                mySound[select].start();
                replay++;
                optT.setText("You can press play to restart");
            }
             else {
                replay = 0;
                optT.setText("Press PLAY again for another tune ");

            }
                opt.setText("Press PAUSE, select the tune then press CHOOSE");
        }

    public void pauseMusic (View v) {
        if (mySound[select].isPlaying()) {
            mySound[select].pause();
            endTime = System.currentTimeMillis();
        }
        pause_on=1;

    }

    private void delay(long d1, long d2) {  // method to calculate and output the delay
        long startTime = d1;
        long endTime = d2;
        long delay = endTime-startTime;
        double delayf = (double)delay;
        delayf=delayf/1000.0;               // convert ms to seconds
        optT.setText("You did that in "+Double.toString(delayf)+"s");
        replay=0;
    }


    public void chooseMusic (View v) {

        RadioButton choice = (RadioButton) findViewById(selector.getCheckedRadioButtonId());
        String ans = choice.getText().toString();
        String OK="Correct, PLAY another?";
        String TD="Wrong!!!";

        if(pause_on==1) {
            if (ans.equals("Axelf") && select == 0) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Under The Boardwalk") && select == 1) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Pianoman") && select == 2) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Alone") && select == 3) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("The Hollywood Waltz") && select == 4) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Let The River Run") && select == 5) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Saturday Night") && select == 6) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else if (ans.equals("Take It To The Limit") && select == 7) {
                opt.setText(OK);
                delay(startTime,endTime);
            } else {
                opt.setText(TD);
            }


        }
    }
}