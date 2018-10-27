package com.example.elizabethzou.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

        findViewById(R.id.add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });

        findViewById(R.id.edit_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("question", ((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("correct_answer", ((TextView) findViewById(R.id.flashcard_answer)).getText().toString());
                intent.putExtra("incorrect_answer_1", ((TextView) findViewById(R.id.incorrect_answer_1)).getText().toString());
                intent.putExtra("incorrect_answer_2", ((TextView) findViewById(R.id.incorrect_answer_2)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String question = data.getExtras().getString("question");
                String correct_answer = data.getExtras().getString("correct_answer");
                String incorrect_answer_1 = data.getExtras().getString("incorrect_answer_1");
                String incorrect_answer_2 = data.getExtras().getString("incorrect_answer_2");
                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer)).setText(correct_answer);
                ((TextView) findViewById(R.id.incorrect_answer_1)).setText(incorrect_answer_1);
                ((TextView) findViewById(R.id.incorrect_answer_2)).setText(incorrect_answer_2);
                ((TextView) findViewById(R.id.correct_answer)).setText(correct_answer);
                Snackbar.make(findViewById(R.id.rootView), "Card successfully created!", Snackbar.LENGTH_LONG).show();
            }
            findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            findViewById(R.id.incorrect_answer_1).setBackground(getResources().getDrawable(R.drawable.choice_background));
            findViewById(R.id.incorrect_answer_2).setBackground(getResources().getDrawable(R.drawable.choice_background));
            findViewById(R.id.correct_answer).setBackground(getResources().getDrawable(R.drawable.choice_background));
        }
    }
}
