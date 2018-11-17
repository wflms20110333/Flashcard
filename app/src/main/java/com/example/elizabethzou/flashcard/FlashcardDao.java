package com.example.elizabethzou.flashcard;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    List<com.caren.unobliviate.Flashcard> getAll();

    @Insert
    void insertAll(com.caren.unobliviate.Flashcard... flashcards);

    @Delete
    void delete(com.caren.unobliviate.Flashcard flashcard);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(com.caren.unobliviate.Flashcard flashcard);
}
