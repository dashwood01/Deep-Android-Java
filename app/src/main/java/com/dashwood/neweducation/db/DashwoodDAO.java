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
    void addUser(Dashwood...users);

    @Update
    void updateUser(Dashwood user);

    @Delete
    void deleteUser(Dashwood user);

    @Query("SELECT * FROM dashwood_tb")
    LiveData<List<Dashwood>> enteries();


}
