package com.dashwood.neweducation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dashwood.neweducation.R;
import com.dashwood.neweducation.db.DashwoodAppDatabase;
import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;
import com.dashwood.neweducation.model.DashwoodRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<List<Category>> categoriesLiveData;
    private LiveData<List<Book>> booksLiveData;
    private DashwoodRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new DashwoodRepository(application);
    }

    public LiveData<List<Category>> getCategoriesLiveData() {
        categoriesLiveData = repository.getCategoriesLiveData();
        return categoriesLiveData;
    }

    public LiveData<List<Book>> getBooksLiveData(int id) {
        booksLiveData = repository.getBooksLiveData(id);
        return booksLiveData;
    }

    public void insertNewBook(Book... books) {
        repository.insertBook(books);
    }

    public void updateNewBook(Book... books) {
        repository.updateBook(books);
    }

    public void deleteNewBook(Book... books) {
        repository.deleteBook(books);
    }

    public void insertNewCategory(Category... categories) {
        repository.insertCategory(categories);
    }

    public void updateNewCategory(Category... categories) {
        repository.updateCategory(categories);
    }

    public void deleteNewCategory(Category... categories) {
        repository.deleteCategory(categories);
    }
}
