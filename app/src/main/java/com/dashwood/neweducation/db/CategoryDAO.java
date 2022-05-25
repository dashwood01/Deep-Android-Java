package com.dashwood.neweducation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dashwood.neweducation.model.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Insert
    void insert(Category... categories);

    @Update
    void update(Category category);

    @Delete
    void delete(Category... categories);

    @Query("SELECT * FROM CATEGORY_TB")
    LiveData<List<Category>> getAll();

}
