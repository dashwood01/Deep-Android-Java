package com.dashwood.neweducation.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DashwoodDAO {
    @Insert
    long addUser(Entery entery);

    @Update
    void updateUser(Entery entery);

    @Delete
    void deleteUser(Entery entery);

    @Query("SELECT * FROM dashwood_tb")
    List<Entery> enteries();

}
