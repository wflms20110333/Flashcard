package com.example.elizabethzou.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        final boolean edit = getIntent().getBooleanExtra("edit", false);

        if (edit) {
            String question = getIntent().getStringExtra("question");
            String correct_answer = getIntent().getStringExtra("correct_answer");
            String incorrect_answer_1 = getIntent().getStringExtra("incorrect_answer_1");
            String incorrect_answer_2 = getIntent().getStringExtra("incorrect_answer_2");
            ((EditText) findViewById(R.id.question)).setText(question);
            ((EditText) findViewById(R.id.correct_answer)).setText(correct_answer);
            ((EditText) findViewById(R.id.incorrect_answer_1)).setText(incorrect_answer_1);
            ((EditText) findViewById(R.id.incorrect_answer_2)).setText(incorrect_answer_2);
        }

        findViewById(R.id.cancel_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
                if (edit)
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                else
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        findViewById(R.id.save_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.question)).getText().toString();
                String correct_answer = ((EditText) findViewById(R.id.correct_answer)).getText().toString();
                String incorrect_answer_1 = ((EditText) findViewById(R.id.incorrect_answer_1)).getText().toString();
                String incorrect_answer_2 = ((EditText) findViewById(R.id.incorrect_answer_2)).getText().toString();
                if (question.length() == 0 || correct_answer.length() == 0 || incorrect_answer_1.length() == 0 || incorrect_answer_2.length() == 0)
                    Toast.makeText(getApplicationContext(), "Must enter all question and answer choices!", Toast.LENGTH_SHORT).show();
                else {
                    Intent data = new Intent();
                    data.putExtra("question", question);
                    data.putExtra("correct_answer", correct_answer);
                    data.putExtra("incorrect_answer_1", incorrect_answer_1);
                    data.putExtra("incorrect_answer_2", incorrect_answer_2);
                    setResult(RESULT_OK, data);
                    finish();
                    if (edit)
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    else
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                }
            }
        });
    }

}
