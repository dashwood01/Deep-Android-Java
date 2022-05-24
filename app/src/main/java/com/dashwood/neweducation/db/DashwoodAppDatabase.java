package com.dashwood.neweducation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entery.class}, version = 1, exportSchema = false)
public abstract class DashwoodAppDatabase extends RoomDatabase {
    public abstract DashwoodDAO getDashwoodDAO();
}
