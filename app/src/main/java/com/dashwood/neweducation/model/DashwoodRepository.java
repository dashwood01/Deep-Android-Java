package com.dashwood.neweducation.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dashwood.neweducation.db.BookDAO;
import com.dashwood.neweducation.db.CategoryDAO;
import com.dashwood.neweducation.db.DashwoodAppDatabase;
import com.dashwood.neweducation.db.DashwoodDAO;
import com.dashwood.neweducation.db.Dashwood;

import java.util.List;
import java.util.concurrent.Executors;

public class DashwoodRepository {
    private final CategoryDAO categoryDAO;
    private final BookDAO bookDAO;
    private final DashwoodDAO dashwoodDAO;

    public DashwoodRepository(Application application) {
        DashwoodAppDatabase dashwoodAppDatabase = DashwoodAppDatabase.getInstance(application);
        categoryDAO = dashwoodAppDatabase.getCategoryDAO();
        bookDAO = dashwoodAppDatabase.getBookDAO();
        dashwoodDAO = dashwoodAppDatabase.getDashwoodDAO();
    }

    public LiveData<List<Dashwood>> getUsersLiveData() {
        return dashwoodDAO.enteries();
    }

    public LiveData<List<Category>> getCategoriesLiveData() {
        return categoryDAO.getAll();
    }

    public LiveData<List<Book>> getBooksLiveData(int categoryId) {
        return bookDAO.getAll(categoryId);
    }

    public void insertCategory(Category... categories) {
        //new InsertCategoryAsyncTask(categoryDAO).execute(categories);
        Executors.newSingleThreadExecutor().execute(() -> categoryDAO.insert(categories));

    }

    public void insertBook(Book... books) {
        Executors.newSingleThreadExecutor().execute(() -> bookDAO.insert(books));
    }


    public void deleteCategory(Category... categories) {
        Executors.newSingleThreadExecutor().execute(() -> categoryDAO.delete(categories));
    }

    public void deleteBook(Book... books) {
        Executors.newSingleThreadExecutor().execute(() -> bookDAO.delete(books));
    }

    public void updateCategory(Category... categories) {
        Executors.newSingleThreadExecutor().execute(() -> categoryDAO.update(categories));
    }

    public void updateBook(Book... books) {
        Executors.newSingleThreadExecutor().execute(() -> bookDAO.update(books));
    }


}
