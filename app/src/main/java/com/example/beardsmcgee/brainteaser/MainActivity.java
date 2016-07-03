/*
 * App is a game, it picks random integers for a simple
 * arithmetic problem (ie: 2 + 5) and displays four choices to choose from.
 * The goal of the game is to answer as many correct questions as possible with in
 * 30 seconds.
 */

package com.example.beardsmcgee.brainteaser;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // TextView for displaying count down
    // GAME_TIME is total time for the game (ie: 30000 ms is 30 seconds)
    // TIMER_UPDATE is when to update clock (ie: 1000ms is 1 second)
    private TextView timerTextView;
    private final int GAME_TIME = 30000;
    private final int TIMER_UPDATE = 1000;

    private GridLayout gridLayout;

    // displays current arithmetic problem
    private TextView problemTextView;

    // displays total number of correct answers / total questions
    private TextView scoreTextView;

    // possible option 1
    private Button firstButton;

    // possible option 2
    private Button secondButton;

    // possible option 3
    private Button thirdButton;

    // possible option 4
    private Button fourthButton;

    //button for new game
    private Button newGameButton;

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
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        gridLayout.setVisibility(View.GONE);

        newGameButton = (Button) findViewById(R.id.startButton);
        newGameButton.setVisibility(View.VISIBLE);
        newGameButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                newGameButton.setVisibility(View.GONE);
                gridLayout.setVisibility(View.VISIBLE);
                newGame();
            }
        });

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText("");

        problemTextView = (TextView) findViewById(R.id.problemTextView);
        problemTextView.setText("");

        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText("0/0");

        firstButton = (Button) findViewById(R.id.firstButton);
        firstButton.setText("");

        secondButton = (Button) findViewById(R.id.secondButton);
        secondButton.setText("");

        thirdButton = (Button) findViewById(R.id.thirdButtonTextView);
        thirdButton.setText("");

        fourthButton = (Button) findViewById(R.id.fourthButton);
        fourthButton.setText("");

         // Button onClick listeners
        firstButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                answer((Button) view);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((Button) view);
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((Button) view);}
        });

        fourthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                answer((Button) view);
            }
        });

    }

    /*
     * Method creates a new game by
     * creating a countdowntimer and the first problem.
     */

    private void newGame(){
        createProblem();
        timerTextView.setText(String.valueOf(GAME_TIME / 1000));

        new CountDownTimer(GAME_TIME+100, TIMER_UPDATE){

            @Override
            public void onTick(long l) {
                int secondsLeft = (int) l / 1000;
                timerTextView.setText(String.valueOf(secondsLeft));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0");
                gridLayout.setVisibility(View.GONE);
                problemTextView.setText("");
                newGameButton.setVisibility(View.VISIBLE);

            }
        }.start();

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
                setAnswers(firstButton, secondButton, thirdButton,
                        fourthButton, x, y, z);
                break;

            case(SECOND_BUTTON):
                setAnswers(secondButton, firstButton, thirdButton,
                        fourthButton, x, y, z);
                break;

            case(THIRD_BUTTON):
                setAnswers(thirdButton, firstButton, secondButton,
                        fourthButton, x, y, z);
                break;

            case(FOURTH_BUTTON):
                setAnswers(fourthButton, firstButton, secondButton,
                        thirdButton, x, y, z);
                break;
        }
        setProblem();


    }


    // Sets text of other text views to integers
    // First argument will contain correct answer, rest will be incorrect.
    // X, Y, Z are incorrect answers
    private void setAnswers(Button correctButton, Button button1, Button button2, Button button3, int x, int y, int z){
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
    private void answer(Button answerButton){
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
