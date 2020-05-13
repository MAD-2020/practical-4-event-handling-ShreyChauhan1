package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Whack A Mole";
    int points=MainActivity.points;
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */



    private void readyTimer(){
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

                StringBuilder timer = new StringBuilder(20);
                timer.append("Game starts in: ").append(millisUntilFinished / 1000);
                final Toast toast = Toast.makeText(getApplicationContext(),
                        timer,
                        Toast.LENGTH_SHORT);

                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);

            }

            public void onFinish() {
                Log.v(TAG,"Ready Countdown Complete");
            Toast toast2=Toast.makeText(getApplicationContext(),"GO!",Toast.LENGTH_SHORT);
                setNewMole();
                placeMoleTimer();


            }
        }.start();
    }
    private void placeMoleTimer() {
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */

            new CountDownTimer(1000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    setNewMole();
                    start();
                }
            }.start();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */
        readyTimer();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.v(TAG, "Current User Score: " + String.valueOf(points));



    }
    @Override
    protected void onStart(){
        super.onStart();
        String temp = null;
        temp = Integer.toString(points);
        TextView text = findViewById(R.id.textView);
        text.setText(temp);

    }

    public void checkbtn(final Button checkbtn){
        checkbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkbtn.getText() == "*") {
                    Log.v(TAG, "Hit, score added!");

                    points += 1;
                    String temp = null;
                    temp = Integer.toString(points);
                    TextView text = findViewById(R.id.textView);
                    text.setText(temp);


                    setNewMole();



                } else {
                    Log.v(TAG, "Missed, score deducted!");

                    points -= 1;
                    if(points<0){
                        points=0;
                    }
                    String temp = null;
                    temp = Integer.toString(points);
                    TextView text = findViewById(R.id.textView);
                    text.setText(temp);

                    final Button button1 = findViewById(R.id.button1);
                    final Button button2 = findViewById(R.id.button2);
                    final Button button3 = findViewById(R.id.button3);
                    setNewMole();

                }

            }
        });
    }

    public void setNewMole()
    {
        final Button button1 = findViewById(R.id.button6);
        final Button button2 = findViewById(R.id.button7);
        final Button button3 = findViewById(R.id.button8);
        final Button button4 = findViewById(R.id.button9);
        final Button button5 = findViewById(R.id.button10);
        final Button button6 = findViewById(R.id.button11);
        final Button button7 = findViewById(R.id.button12);
        final Button button8 = findViewById(R.id.button13);
        final Button button9 = findViewById(R.id.button14);

        button1.setText("0");
        button2.setText("0");
        button3.setText("0");
        button4.setText("0");
        button5.setText("0");
        button6.setText("0");
        button7.setText("0");
        button8.setText("0");
        button9.setText("0");

        Random[] ran = {new Random()};
        int randomLocation = ran[0].nextInt(9);
        Button mole;
        if (randomLocation == 1) {
            mole = findViewById(R.id.button6);

        } else if (randomLocation == 2) {
            mole = findViewById(R.id.button7);

        } else if (randomLocation == 3) {
            mole = findViewById(R.id.button8);

        } else if (randomLocation == 4) {
            mole = findViewById(R.id.button9);

        } else if (randomLocation == 5) {
            mole = findViewById(R.id.button10);

        } else if (randomLocation == 6) {
            mole = findViewById(R.id.button11);

        } else if (randomLocation == 7) {
            mole = findViewById(R.id.button12);

        } else if (randomLocation == 8) {
            mole = findViewById(R.id.button13);

        } else {
            mole = findViewById(R.id.button14);

        }
        mole.setText("*");
        Log.v(TAG,"New mole position set");

        checkbtn(button1);
        checkbtn(button2);
        checkbtn(button3);
        checkbtn(button4);
        checkbtn(button5);
        checkbtn(button6);
        checkbtn(button7);
        checkbtn(button8);
        checkbtn(button9);
    }
}

