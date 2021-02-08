package com.geekbrains.nasalib.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geekbrains.nasalib.model.entity.Element;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ElementDao {
    @Query("SELECT * FROM table_elements")
    Single<List<Element>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertList(List<Element> elements);

    @Query("DELETE FROM table_elements")
    Single<Integer> deleteAll();

}
