package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();

    String wordFrag="";
    Button button_reset,button_challenge;
    int uScore=0,cScore=0;
    TextView score_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);



        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }

         button_reset= (Button) findViewById(R.id.button_reset);
         button_challenge=(Button)findViewById(R.id.button_challenge);
        score_text=(TextView)findViewById(R.id.score_text);

         onStart(null);

         button_reset.setOnClickListener(new Button.OnClickListener() {
                                             public void onClick(View v) {
                                                 userTurn=false;
                                                 uScore=cScore=0;
                                                 score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                                                 onStart(null);
                                             }

                                         }

         );
         button_challenge.setOnClickListener(new Button.OnClickListener(){
                                                 @Override
                                                 public void onClick(View v) {
                                                     TextView label = (TextView) findViewById(R.id.gameStatus);
                                                     // Do computer turn stuff then make it the user's turn again
                                                     if(wordFrag.length()>=4 && dictionary.isWord(wordFrag)){
                                                         label.setText("A complete word is formed. You win!!");
                                                         uScore++;
                                                         score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                                                         button_challenge.setEnabled(false);
                                                         new CountDownTimer(2000, 1000) {
                                                             public void onFinish() {
                                                                 onStart(null);
                                                             }
                                                             public void onTick(long millisUntilFinished) {
                                                                 // millisUntilFinished    The amount of time until finished.
                                                             }

                                                         }.start();
                                                     }else if(dictionary.getAnyWordStartingWith(wordFrag)!=null){
                                                         String s=dictionary.getAnyWordStartingWith(wordFrag);
                                                         label.setText("Possible word with prefix: " + s + ". Computer wins!!");
                                                         cScore++;
                                                         score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                                                         button_challenge.setEnabled(false);
                                                         new CountDownTimer(2000, 1000) {
                                                             public void onFinish() {
                                                                 onStart(null);
                                                             }
                                                             public void onTick(long millisUntilFinished) {
                                                                 // millisUntilFinished    The amount of time until finished.
                                                             }

                                                         }.start();
                                                     }
                                                     else{
                                                         label.setText("No word possible with prefix. You win!!");

                                                         uScore++;
                                                         score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                                                         button_challenge.setEnabled(false);
                                                         new CountDownTimer(2000, 1000) {
                                                             public void onFinish() {
                                                                 onStart(null);
                                                             }
                                                             public void onTick(long millisUntilFinished) {
                                                                 // millisUntilFinished    The amount of time until finished.
                                                             }

                                                         }.start();
                                                     }

                                                 }
                                             }
         );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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

    private void computerTurn() {
        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                TextView label = (TextView) findViewById(R.id.gameStatus);
                // Do computer turn stuff then make it the user's turn again
                if(wordFrag.length()>=4 && dictionary.isWord(wordFrag)){
                    label.setText("A complete word is formed. Computer wins!!");
                    cScore++;
                    score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                    button_challenge.setEnabled(false);
                    new CountDownTimer(1000, 1000) {
                        public void onFinish() {
                            onStart(null);
                        }
                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }

                    }.start();
                }
                else if(dictionary.getAnyWordStartingWith(wordFrag)==null){
                    label.setText("No word possible with the prefix. Computer wins!!");

                    cScore++;
                    score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
                    button_challenge.setEnabled(false);
                    new CountDownTimer(2000, 1000) {
                        public void onFinish() {
                            onStart(null);
                        }
                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }

                    }.start();
                }
                else {
                    String anyWord=dictionary.getAnyWordStartingWith(wordFrag);
                    wordFrag+=Character.toString(anyWord.charAt(wordFrag.length()));
                    TextView text = (TextView) findViewById(R.id.ghostText);
                    text.setText(wordFrag);
                    userTurn = true;
                    label.setText(USER_TURN);

                }

            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();

    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {

        button_challenge.setEnabled(true);
        wordFrag="";
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //do something here
        char unicodeChar = (char)event.getUnicodeChar();
        unicodeChar=Character.toLowerCase(unicodeChar);
        if(!Character.isLetter(unicodeChar))
        return super.onKeyUp(keyCode, event);

        else {
                wordFrag+=Character.toString(unicodeChar);
                TextView text = (TextView) findViewById(R.id.ghostText);
                text.setText(wordFrag);
                TextView label = (TextView)findViewById(R.id.gameStatus);
                label.setText(COMPUTER_TURN);
                computerTurn();

        }
        return true;
    };
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        super.onSaveInstanceState(savedInstanceState);
        TextView label = (TextView)findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);
        savedInstanceState.putString("wordFrag",text.getText().toString());
        savedInstanceState.putString("gameStatus", label.getText().toString());
        savedInstanceState.putInt("cScore", cScore);
        savedInstanceState.putInt("uScore",uScore);
        // Always call the superclass so it can save the view hierarchy state
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        TextView label = (TextView)findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);
        // Restore state members from saved instance
        text.setText(savedInstanceState.getString("wordFrag"));
        wordFrag=savedInstanceState.getString("wordFrag");
        label.setText(savedInstanceState.getString("gameStatus"));
        uScore=savedInstanceState.getInt("uScore");
        cScore=savedInstanceState.getInt("cScore");
        score_text.setText("Your score:"+uScore+" Computer score:"+cScore);
    }

}
