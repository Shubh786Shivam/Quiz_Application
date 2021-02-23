package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    int locOfCorrectAnswer;
    TextView correct;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scored;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView question;
    TextView timer;
    Button playDobara;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    public void hideElement(View view){
        view.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timer));
        gameLayout.setVisibility(View.VISIBLE);
    }
    public void correctAnswer(View view){
       if(Integer.toString(locOfCorrectAnswer).equals(view.getTag().toString())){
             correct.setText("Correct :)");
             score++;
       }
       else{
           correct.setText("Wrong :(");
       }

        numberOfQuestions++;
        scored.setText(score + "/" + numberOfQuestions);
       newQuestion();
    }
    public void newQuestion(){
        Random rand = new Random();
        int a = 3 + rand.nextInt(50 - 3 + 1);
        int b = 3 + rand.nextInt(50 - 3 + 1);
        question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for(int i = 0; i < 4; i++){
            if(i == locOfCorrectAnswer){
                answers.add(a + b);
            }
            else{
                int wrongAnswer = 6 + rand.nextInt(100 - 6 + 1);
                while(wrongAnswer == a+b){
                    wrongAnswer = 6 + rand.nextInt(100 - 6 + 1);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void playAgain(View view){
         score = 0;
         numberOfQuestions = 0;
         timer.setText("30s");
        scored.setText(score + "/" + numberOfQuestions);
        newQuestion();
        playDobara.setVisibility(View.INVISIBLE);
        correct.setText("");
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                correct.setText("Times Up :(");
                playDobara.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        question = findViewById(R.id.question);
        scored = findViewById(R.id.score);
        correct = findViewById(R.id.correct);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timer = findViewById(R.id.timer);
        playDobara = findViewById(R.id.playDobara);
        gameLayout = findViewById(R.id.gameLayout);
        button.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}