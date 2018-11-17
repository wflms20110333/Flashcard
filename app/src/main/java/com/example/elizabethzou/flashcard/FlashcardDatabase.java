package com.example.elizabethzou.flashcard;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class FlashcardDatabase {
    private final com.caren.unobliviate.AppDatabase db;

    FlashcardDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                com.caren.unobliviate.AppDatabase.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public List<com.caren.unobliviate.Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }

    public void insertCard(com.caren.unobliviate.Flashcard flashcard) {
        db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<com.caren.unobliviate.Flashcard> allCards = db.flashcardDao().getAll();
        for (com.caren.unobliviate.Flashcard f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashcardDao().delete(f);
            }
        }
    }

    public void updateCard(com.caren.unobliviate.Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }
}
