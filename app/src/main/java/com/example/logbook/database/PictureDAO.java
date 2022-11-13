package com.example.logbook.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.logbook.database.PictureEntity;

import java.util.List;

@Dao
public interface PictureDAO {
    @Insert
    void addPicture(PictureEntity picture);

    @Update
    void updatePicture(PictureEntity picture);

    @Query("SELECT * FROM picture")
    LiveData<List<PictureEntity>> getAllData();

    @Query("SELECT * FROM picture WHERE pictureId = :pictureId")
    LiveData<PictureEntity> findPictureById(int pictureId);

    @Query("DELETE FROM picture")
    void deleteAllPicture();


}
