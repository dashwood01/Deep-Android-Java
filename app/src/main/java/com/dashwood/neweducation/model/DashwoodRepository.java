package com.dashwood.neweducation.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dashwood.neweducation.db.BookDAO;
import com.dashwood.neweducation.db.CategoryDAO;
import com.dashwood.neweducation.db.DashwoodAppDatabase;
import com.dashwood.neweducation.db.DashwoodDAO;
import com.dashwood.neweducation.db.User;

import java.util.List;

public class DashwoodRepository {
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private DashwoodDAO dashwoodDAO;

    private LiveData<List<User>> usersLiveData;
    private LiveData<List<Category>> categoriesLiveData;
    private LiveData<List<Book>> booksLiveData;

    public DashwoodRepository(Application application) {
        DashwoodAppDatabase dashwoodAppDatabase = DashwoodAppDatabase.getInstance(application);
        categoryDAO = dashwoodAppDatabase.getCategoryDAO();
        bookDAO = dashwoodAppDatabase.getBookDAO();
        dashwoodDAO = dashwoodAppDatabase.getDashwoodDAO();
    }

    public LiveData<List<User>> getUsersLiveData() {
        return dashwoodDAO.enteries();
    }

    public LiveData<List<Category>> getCategoriesLiveData() {
        return categoryDAO.getAll();
    }

    public LiveData<List<Book>> getBooksLiveData(int categoryId) {
        return bookDAO.getAll(categoryId);
    }

    public void insertCategory(Category... categories) {
        new InsertCategoryAsyncTask(categoryDAO).execute(categories);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories);
            return null;
        }
    }

    public void insertBook(Book... books) {
        new InsertBookAsyncTask(bookDAO).execute(books);
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books);
            return null;
        }
    }

    public void deleteCategory(Category... categories) {
        new InsertCategoryAsyncTask(categoryDAO).execute(categories);
    }

    private static class deleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public deleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories);
            return null;
        }
    }

    public void deleteBook(Book... books) {
        new InsertBookAsyncTask(bookDAO).execute(books);
    }

    private static class deleteBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public deleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books);
            return null;
        }
    }

    public void updateCategory(Category... categories) {
        new InsertCategoryAsyncTask(categoryDAO).execute(categories);
    }

    private static class updateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        public updateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories);
            return null;
        }
    }

    public void updateBook(Book... books) {
        new InsertBookAsyncTask(bookDAO).execute(books);
    }

    private static class updateBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        public updateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books);
            return null;
        }
    }

}
