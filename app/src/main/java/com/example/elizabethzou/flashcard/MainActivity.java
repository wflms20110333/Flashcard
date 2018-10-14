package com.example.elizabethzou.flashcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                v.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.correct_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.choice_background_correct));
            }
        });

        findViewById(R.id.incorrect_answer_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.choice_background_incorrect));
                findViewById(R.id.correct_answer).setBackground(getResources().getDrawable(R.drawable.choice_background_correct));
            }
        });

        findViewById(R.id.incorrect_answer_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.choice_background_incorrect));
                findViewById(R.id.correct_answer).setBackground(getResources().getDrawable(R.drawable.choice_background_correct));
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.incorrect_answer_1).setBackground(getResources().getDrawable(R.drawable.choice_background));
                findViewById(R.id.incorrect_answer_2).setBackground(getResources().getDrawable(R.drawable.choice_background));
                findViewById(R.id.correct_answer).setBackground(getResources().getDrawable(R.drawable.choice_background));
            }
        });
    }
}
