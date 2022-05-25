package com.dashwood.neweducation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void insert(Book... books);

    @Update
    void update(Book book);

    @Delete
    void delete(Book... books);

    @Query("SELECT * FROM BOOK_TB WHERE category_id ==:categoryId")
    LiveData<List<Book>> getAll(int categoryId);

}
