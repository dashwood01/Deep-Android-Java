package com.dashwood.neweducation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DashwoodDAO {
    @Insert
    void addUser(User...users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM dashwood_tb")
    LiveData<List<User>> enteries();


}
