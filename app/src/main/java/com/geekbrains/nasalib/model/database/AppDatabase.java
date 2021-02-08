package com.geekbrains.nasalib.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekbrains.nasalib.model.entity.Element;

@Database(entities = {Element.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ElementDao elementDao();
}
