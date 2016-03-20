package com.example.manojy.scarnedice;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class DiceActivity extends AppCompatActivity {


    int uScore=0,uTurnScore=0,cScore=0,cTurnScore=0,result;
    TextView result_text;
    ImageView image_dice;
    Button button_roll;
    Button button_hold;
    Button button_reset;
    boolean f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        result_text= (TextView) findViewById(R.id.result_text);
        image_dice= (ImageView) findViewById(R.id.image_dice);
        button_roll= (Button) findViewById(R.id.button_roll);
        button_hold= (Button) findViewById(R.id.button_hold);
        button_reset= (Button) findViewById(R.id.button_reset);


        button_roll.setOnClickListener(new Button.OnClickListener() {
                                           public void onClick(View v) {

                                            Random r=new Random();
                                            result=Math.abs(r.nextInt()%7);
                                            rollDice();
                                               f=true;

                                               if(result==1){
                                                   uTurnScore=0;

                                                   result_text.setText("Your score: " + uScore + " Computer score: " + cScore + " your turn score: " + uTurnScore);


                                                      new CountDownTimer(1000, 1000) {
                                                          public void onFinish() {
                                                              // When timer is finished
                                                              // Execute your code here
                                                              f = computerTurn();
                                                              if(f==true)
                                                                  this.start();
                                                          }

                                                          public void onTick(long millisUntilFinished) {
                                                              // millisUntilFinished    The amount of time until finished.
                                                          }
                                                      }.start();


                                               }
                                               else{
                                                   uTurnScore+=result;
                                                   if(uTurnScore+uScore>=100){
                                                       result_text.setText("You are the winner");
                                                       uScore=uTurnScore=cTurnScore=cScore=0;
                                                   }

                                                   else
                                                   result_text.setText("Your score: "+uScore+" Computer score: "+cScore+" your turn score: "+uTurnScore);
                                               }
                                           }
                                       }

        );

        button_reset.setOnClickListener(new Button.OnClickListener(){
                                            @Override
                                            public void onClick(View v) {
                                                uTurnScore=uScore=cScore=cTurnScore=0;
                                                result_text.setText("Your score: " + uScore + " Computer score: " + cScore);
                                                button_hold.setEnabled(true);
                                                button_roll.setEnabled(true);
                                            }
                                        }

        );

        button_hold.setOnClickListener(new Button.OnClickListener(){
                                            @Override
                                            public void onClick(View v) {

                                                uScore+=uTurnScore;
                                                uTurnScore=0;
                                                result_text.setText("Your score: " + uScore + " Computer score: " + cScore);
                                                f=true;

                                                      new CountDownTimer(1000, 1000) {
                                                          public void onFinish() {
                                                              // When timer is finished
                                                              // Execute your code here
                                                              f = computerTurn();
                                                              if(f==true)
                                                                  this.start();

                                                          }

                                                          public void onTick(long millisUntilFinished) {
                                                              // millisUntilFinished    The amount of time until finished.
                                                          }
                                                      }.start();


                                            }
                                        }

        );




    }
    public void rollDice(){
        switch(result){

            case 1: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice1);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


                    break;
            case 2: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice2);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


                break;
            case 3: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice3);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


                break;
            case 4: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice4);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


                break;
            case 5: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice5);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();
                break;
            case 6: image_dice.setImageResource(R.drawable.dice3droll);
                new CountDownTimer(200, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        image_dice.setImageResource(R.drawable.dice6);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


                break;
        }
    }

    public boolean computerTurn(){

        button_hold.setEnabled(false);
        button_roll.setEnabled(false);

        Random r=new Random();
        result=Math.abs(r.nextInt()%7);

                rollDice();
                if(result==1){
                    cTurnScore=0;
                    result_text.setText("Your score: "+uScore+" Computer score: "+cScore+" Computer rolled a one");
                    button_hold.setEnabled(true);
                    button_roll.setEnabled(true);
                    return false;
                }
                else{
                    cTurnScore+=result;
                    if(cScore+cTurnScore>=100) {
                        result_text.setText("Computer is the winner");
                        uTurnScore=uScore=cScore=cTurnScore=0;
                        button_hold.setEnabled(true);
                        button_roll.setEnabled(true);
                        return false;
                    }
                    else {
                        result_text.setText("Your score: " + uScore + " Computer score: " + cScore + " Computer turn score: " + cTurnScore);
                        if (cTurnScore >= 20) {
                            cScore += cTurnScore;
                            cTurnScore = 0;

                            result_text.setText("Your score: " + uScore + " Computer score: " + cScore + " Computer holds");
                            button_hold.setEnabled(true);
                            button_roll.setEnabled(true);
                            return false;
                        }
                    }

                }

        button_hold.setEnabled(true);
        button_roll.setEnabled(true);
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
