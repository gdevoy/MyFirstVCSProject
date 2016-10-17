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
    TextView opt, optT, diag;
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
        opt = (TextView)findViewById(R.id.textView);  //  set up the inital messages
        opt.setText("Press PLAY to start.");
        optT = (TextView)findViewById(R.id.textView3);
        opt.setText("    ");
        diag = (TextView)findViewById(R.id.textView4);
        diag.setText(Integer.toString(999));
    }

    public void playMusic(View view){
        //set select to rand 0 to 8
        if (pause_on==1)                              // only start the music if paused
            pause_on = 0;
            if (replay == 0) {
                select = (int) (Math.random() * 8);
                startTime = System.currentTimeMillis();
                mySound[select].start();
                replay++;
                opt.setText("Press Pause when you think you know it.");
                optT.setText("You can press PLAY to restart ");
                diag.setText(Integer.toString(replay));
            }
            else if(replay==1){
                mySound[select].start();
                replay++;
                opt.setText("Press Pause when you think you know it.");
                optT.setText("You can press PLAY to restart");
                diag.setText(Integer.toString(replay));
            }
            else if(replay==2){
                mySound[select].start();
                replay++;
                opt.setText("Press Pause when you think you know it.");
                optT.setText("Last go.");
                diag.setText(Integer.toString(replay));
            }
    }



    public void pauseMusic (View v) {
        if (mySound[select].isPlaying()) {               // only pause the music if it is playing
            mySound[select].pause();
            endTime = System.currentTimeMillis();
        }
        pause_on=1;
        opt.setText("Select your guess then press CHOOSE");
        if (replay==3) {
            replay = 0;
            diag.setText(Integer.toString(replay));
        };
    }


    private void delay() {                             // method to calculate and output the delay
        long delay = endTime-startTime;
        double delayf = (double)delay;
        delayf=delayf/1000.0;                                             // convert ms to seconds
        optT.setText("You did that in "+Double.toString(delayf)+"s");
        replay=0;                                                         // reset replay
    }


    public void chooseMusic (View v) {

        RadioButton choice = (RadioButton) findViewById(selector.getCheckedRadioButtonId());
        String ans = choice.getText().toString();
        String OK="Correct, PLAY another?";
        String TD="Wrong!!!";

        if(pause_on==1) {
            if (ans.equals("Axelf") && select == 0) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Under The Boardwalk") && select == 1) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Pianoman") && select == 2) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Alone") && select == 3) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("The Hollywood Waltz") && select == 4) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Let The River Run") && select == 5) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Saturday Night") && select == 6) {
                opt.setText(OK);
                delay();
            } else if (ans.equals("Take It To The Limit") && select == 7) {
                opt.setText(OK);
                delay();
            } else {
                opt.setText(TD);
            }

            diag.setText(Integer.toString(replay));
        }
    }
}