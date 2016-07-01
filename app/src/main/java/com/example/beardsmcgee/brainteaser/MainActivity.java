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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // count down timer
    private TextView timerTextView;

    // displays current arithmetic problem
    private TextView problemTextView;

    // displays total number of correct answers / total questions
    private TextView scoreTextView;

    // possible option 1
    private TextView firstButtonTextView;

    // possible option 2
    private TextView secondButtonTextView;

    // possible option 3
    private TextView thirdButtonTextView;

    // possible option 4
    private TextView fourthButtonTextView;

    // Upper bound for random ints
    private final int UPPER_BOUND = 9;

    //Upper bound for wrong answers
    private final int UPPER_BOUND_ANSWERS = 18;

    /* Using these for readability when assigning values
     * after generating each problem.
     */
    private final int FIRST_BUTTON = 0;
    private final int SECOND_BUTTON = 1;
    private final int THIRD_BUTTON = 2;
    private final int FOURTH_BUTTON = 3;


    // Problem is as follows: a + b = answer
    private int a;
    private int b;
    private int answer;

    // Counts number of total number of questions answered and how many
    // answered correctly
    private int totalQuestions;
    private int totalCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //********************testing************************
        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                createProblem();
            }
        });

        timerTextView       = (TextView) findViewById(R.id.timerTextView);
        problemTextView     = (TextView) findViewById(R.id.problemTextView);
        scoreTextView       = (TextView) findViewById(R.id.scoreTextView);
        firstButtonTextView = (TextView) findViewById(R.id.firstButtonTextView);
        secondButtonTextView = (TextView) findViewById(R.id.secondButtonTextView);
        thirdButtonTextView = (TextView) findViewById(R.id.thirdButtonTextView);
        fourthButtonTextView = (TextView) findViewById(R.id.fourthButtonTextView);

        firstButtonTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                answer((TextView) view);
            }
        });

        secondButtonTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((TextView) view);
            }
        });

        thirdButtonTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((TextView) view);}
        });

        fourthButtonTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((TextView) view);
            }
        });

    }

    /*
     * Creates arithmetic problem.
     * Picks two random integers, a and b.
     * Updates a, b and answer.
     * Generates answers
     */
    private void createProblem(){
        totalQuestions++;
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
        switch(correctButton){
            case(FIRST_BUTTON):
                setAnswers(firstButtonTextView, secondButtonTextView, thirdButtonTextView,
                        fourthButtonTextView, x, y, z);
                break;

            case(SECOND_BUTTON):
                setAnswers(secondButtonTextView, firstButtonTextView, thirdButtonTextView,
                        fourthButtonTextView, x, y, z);
                break;

            case(THIRD_BUTTON):
                setAnswers(thirdButtonTextView, firstButtonTextView, secondButtonTextView,
                        fourthButtonTextView, x, y, z);
                break;

            case(FOURTH_BUTTON):
                setAnswers(fourthButtonTextView, firstButtonTextView, secondButtonTextView,
                        thirdButtonTextView, x, y, z);
                break;
        }
        setProblem();


    }


    // Sets text of other text views to integers
    // First argument will contain correct answer, rest will be incorrect.
    // X, Y, Z are incorrect answers
    private void setAnswers(TextView correctButton, TextView button1, TextView button2, TextView button3, int x, int y, int z){
        correctButton.setText(String.valueOf(answer));
        button1.setText(String.valueOf(x));
        button2.setText(String.valueOf(y));
        button3.setText(String.valueOf(z));
    }

    //Sets text of problem
    private void setProblem(){
        problemTextView.setText(String.valueOf(a) + " + " + String.valueOf(b));
    }

    //sets text of scoreTextView
    private void setScore(){
        scoreTextView.setText(String.valueOf(totalCorrect) + "/" + String.valueOf(totalQuestions));
    }

    // Checks if selected answer is correct
    // calls setScore() and createProblem()
    private void answer(TextView answerButton){
        int toastId;
        if(Integer.parseInt(answerButton.getText().toString()) == answer){
            totalCorrect++;
            toastId = R.string.toast_correct;
        } else {
            toastId = R.string.toast_incorrect;
        }
        //displays a toast notifying user answer is correct or incorrect
        Toast.makeText(getApplicationContext(), toastId, Toast.LENGTH_SHORT).show();

        //updates score text view and creates next problem
        setScore();
        createProblem();
    }
}
