package com.example.logbook.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "picture")
public class PictureEntity {
    @PrimaryKey(autoGenerate = true)
    public int pictureId;
    public String url;
    public String name;
    public String description;
    public String create_at;
}
