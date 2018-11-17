package com.example.elizabethzou.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int ADD_CARD_REQUEST_CODE = 0;
    static int EDIT_CARD_REQUEST_CODE = 1;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        currentCardDisplayedIndex = getRandomCardIndex();
        displayCard();

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
                MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
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
                MainActivity.this.startActivityForResult(intent, EDIT_CARD_REQUEST_CODE);
            }
        });

        findViewById(R.id.next_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex = getRandomCardIndex();
                displayCard();
            }
        });

        findViewById(R.id.delete_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex = getRandomCardIndex();
                displayCard();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CARD_REQUEST_CODE || requestCode == EDIT_CARD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String question = data.getExtras().getString("question");
                String correct_answer = data.getExtras().getString("correct_answer");
                String incorrect_answer_1 = data.getExtras().getString("incorrect_answer_1");
                String incorrect_answer_2 = data.getExtras().getString("incorrect_answer_2");

                if (requestCode == ADD_CARD_REQUEST_CODE) {
                    flashcardDatabase.insertCard(new Flashcard(question, correct_answer, incorrect_answer_1, incorrect_answer_2));
                    allFlashcards = flashcardDatabase.getAllCards();
                    currentCardDisplayedIndex = allFlashcards.size() - 1;
                    Snackbar.make(findViewById(R.id.rootView), "Card successfully created!", Snackbar.LENGTH_LONG).show();
                }
                else if (requestCode == EDIT_CARD_REQUEST_CODE) {
                    Flashcard cardToEdit = allFlashcards.get(currentCardDisplayedIndex);
                    cardToEdit.setQuestion(question);
                    cardToEdit.setAnswer(correct_answer);
                    cardToEdit.setWrongAnswer1(incorrect_answer_1);
                    cardToEdit.setWrongAnswer2(incorrect_answer_2);
                    flashcardDatabase.updateCard(cardToEdit);
                    Snackbar.make(findViewById(R.id.rootView), "Card successfully edited!", Snackbar.LENGTH_LONG).show();
                }
            }
            displayCard();
        }
    }

    private int getRandomCardIndex() {
        if (allFlashcards == null || allFlashcards.isEmpty())
            return -1;
        return (int) (Math.random() * allFlashcards.size());
    }

    private void displayCard() {
        if (currentCardDisplayedIndex == -1) {
            findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            findViewById(R.id.incorrect_answer_1).setVisibility(View.INVISIBLE);
            findViewById(R.id.incorrect_answer_2).setVisibility(View.INVISIBLE);
            findViewById(R.id.correct_answer).setVisibility(View.INVISIBLE);
            findViewById(R.id.empty_state).setVisibility(View.VISIBLE);
            findViewById(R.id.next_card).setVisibility(View.INVISIBLE);
            findViewById(R.id.edit_card).setVisibility(View.INVISIBLE);
            findViewById(R.id.delete_card).setVisibility(View.INVISIBLE);
        }
        else {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            ((TextView) findViewById(R.id.incorrect_answer_1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
            ((TextView) findViewById(R.id.incorrect_answer_2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
            ((TextView) findViewById(R.id.correct_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

            findViewById(R.id.incorrect_answer_1).setBackground(getResources().getDrawable(R.drawable.choice_background));
            findViewById(R.id.incorrect_answer_2).setBackground(getResources().getDrawable(R.drawable.choice_background));
            findViewById(R.id.correct_answer).setBackground(getResources().getDrawable(R.drawable.choice_background));

            findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            findViewById(R.id.incorrect_answer_1).setVisibility(View.VISIBLE);
            findViewById(R.id.incorrect_answer_2).setVisibility(View.VISIBLE);
            findViewById(R.id.correct_answer).setVisibility(View.VISIBLE);
            findViewById(R.id.empty_state).setVisibility(View.INVISIBLE);
            findViewById(R.id.next_card).setVisibility(View.VISIBLE);
            findViewById(R.id.edit_card).setVisibility(View.VISIBLE);
            findViewById(R.id.delete_card).setVisibility(View.VISIBLE);
        }
    }
}
