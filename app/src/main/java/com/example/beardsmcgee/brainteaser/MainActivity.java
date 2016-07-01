/*
 * App is a game, it picks random integers for a simple
 * arithmetic problem (ie: 2 + 5) and displays four choices to choose from.
 * The goal of the game is to answer as many correct questions as possible with in
 * 30 seconds.
 */

package com.example.beardsmcgee.brainteaser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // count down timer
    private TextView timerTextView;

    // displays current arithmetic problem
    private TextView problemTextView;

    // displays total number of correct answers / total questions
    private TextView scoreTextView;

    // possible option 1
    private TextView topLeftTextView;

    // possible option 2
    private TextView topRightTextView;

    // possible option 3
    private TextView bottomLeftTextView;

    // possible option 4
    private TextView bottomRightTextView;

    // Upper bound for random ints
    private final int UPPER_BOUND = 9;

    //Upper bound for wrong answers
    private final int UPPER_BOUND_ANSWERS = 18;

    /*
     * Problem is as follows: a + b = answer
     */
    private int a;
    private int b;
    private int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView       = (TextView) findViewById(R.id.timerTextView);
        problemTextView     = (TextView) findViewById(R.id.problemTextView);
        scoreTextView       = (TextView) findViewById(R.id.scoreTextView);
        topLeftTextView     = (TextView) findViewById(R.id.topLeftTextView);
        topRightTextView    = (TextView) findViewById(R.id.topRightTextView);
        bottomLeftTextView  = (TextView) findViewById(R.id.bottomLeftTextView);
        bottomRightTextView = (TextView) findViewById(R.id.bottomRightTextView);

        topLeftTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i("button", "topLeft clicked");
            }
        });

        topRightTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("button", "topRight clicked");
            }
        });

        bottomLeftTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("button", "bottomLeft clicked");
            }
        });

        bottomRightTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("button", "bottomRight clicked");
            }
        });

    }

    /*
     * Creates arithmetic problem.
     * Picks two random integers, a and b.
     * Updates a, b and answer.
     * Generates answers
     */
    private void generateProblem(){
        // create problem
        Random generator = new Random();
        a = generator.nextInt(UPPER_BOUND);
        b = generator.nextInt(UPPER_BOUND);
        answer = a + b;

        // wrong answers
        int x = generator.nextInt(UPPER_BOUND_ANSWERS);
        int y = generator.nextInt(UPPER_BOUND_ANSWERS);
        int z = generator.nextInt(UPPER_BOUND_ANSWERS);

        //randomly pick which button will have correct answer
        //upper bound of 3 since generator is from 0 to upperbound
        int correctButton = generator.nextInt(3);

        //todo: correctly formatted strings
        //assign textviews

    }

}
