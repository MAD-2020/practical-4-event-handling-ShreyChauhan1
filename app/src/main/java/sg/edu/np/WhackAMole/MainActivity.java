package sg.edu.np.WhackAMole;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Whack A Mole";
    public static int points = 0;


    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "Starting GUI!");


        setNewMole();


    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
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
                    TextView text = findViewById(R.id.textView3);
                    text.setText(temp);


                    setNewMole();


                    if (points%10==0){
                        nextLevelQuery();

                    }
                } else {
                    Log.v(TAG, "Missed, score deducted!");

                    points -= 1;
                    if(points<0){
                        points=0;
                    }
                    String temp = null;
                    temp = Integer.toString(points);
                    TextView text = findViewById(R.id.textView3);
                    text.setText(temp);

                    final Button button1 = findViewById(R.id.button1);
                    final Button button2 = findViewById(R.id.button2);
                    final Button button3 = findViewById(R.id.button3);
                    setNewMole();

                }

            }
        });
    }


    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Add the buttons
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.v(TAG,"User chose to advance");
                // User clicked OK button
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Log.v(TAG,"User chose to stay");
            }
        });

// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
        Log.v(TAG,"Advanced option given to user");
    }



    private void setNewMole() {
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);

        button1.setText("0");
        button2.setText("0");
        button3.setText("0");

        Random[] ran = {new Random()};
        int randomLocation = ran[0].nextInt(3);
        Button mole;
        if (randomLocation == 1) {
            mole = findViewById(R.id.button1);

        } else if (randomLocation == 2) {
            mole = findViewById(R.id.button2);

        } else {
            mole = findViewById(R.id.button3);

        }
        mole.setText("*");
        Log.v(TAG,"New mole position set");
        checkbtn(button1);
        checkbtn(button2);
        checkbtn(button3);
    }

}