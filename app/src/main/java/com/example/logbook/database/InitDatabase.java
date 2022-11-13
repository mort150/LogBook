package com.example.logbook.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PictureEntity.class}, version = 1)
public abstract class InitDatabase extends RoomDatabase {
    public abstract PictureDAO pictureDao();
}
