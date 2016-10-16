package com.example.davd.myfirstvcsproject;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

// Under GitHub control as of 15/10/16

public class MainActivity extends AppCompatActivity {

    RadioGroup selector;
    long startTime, endTime;

    MediaPlayer[] mySound = new MediaPlayer[10];
    int select = 0;
    int pause_on=1;
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
        if (pause_on==1) {
            pause_on = 0;
            select = (int) (Math.random() * 8);
            startTime = System.currentTimeMillis();
            mySound[select].start();

            opt.setText("Press PAUSE, select the tune then press CHOOSE");
        }
    }

    public void pauseMusic (View v) {
        if (mySound[select].isPlaying()) {
            mySound[select].pause();
            endTime = System.currentTimeMillis();
        }
        pause_on=1;

    }






    public void chooseMusic (View v) {

        RadioButton choice = (RadioButton) findViewById(selector.getCheckedRadioButtonId());
        String ans = choice.getText().toString();
        String OK="Correct, PLAY again?";
        String TD="Wrong, PLAY again?";

        if(pause_on==1) {
            if (ans.equals("Axelf") && select == 0) {
                opt.setText(OK);
            } else if (ans.equals("Under The Boardwalk") && select == 1) {
                opt.setText(OK);
            } else if (ans.equals("Pianoman") && select == 2) {
                opt.setText(OK);
            } else if (ans.equals("Alone") && select == 3) {
                opt.setText(OK);
            } else if (ans.equals("The Hollywood Waltz") && select == 4) {
                opt.setText(OK);
            } else if (ans.equals("Let The River Run") && select == 5) {
                opt.setText(OK);
            } else if (ans.equals("Saturday Night") && select == 6) {
                opt.setText(OK);
            } else if (ans.equals("Take It To The Limit") && select == 7) {
                opt.setText(OK);
            } else {
                opt.setText(TD);
            }
            long delay = endTime-startTime;
            double delayf = (double)delay;
            delayf=delayf/1000.0;               // convert ms to seconds
            optT.setText("You did that in "+Double.toString(delayf)+"s");

        }
    }
}