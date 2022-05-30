package com.dashwood.neweducation.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;

@Database(entities = {Dashwood.class, Category.class, Book.class}, version = 3, exportSchema = false)
public abstract class DashwoodAppDatabase extends RoomDatabase {

    public abstract DashwoodDAO getDashwoodDAO();

    public abstract CategoryDAO getCategoryDAO();

    public abstract BookDAO getBookDAO();

    private static DashwoodAppDatabase instance;

    public static synchronized DashwoodAppDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, DashwoodAppDatabase.class, "dashwood_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback).build();
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialAsynDatabase(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
        }
    };

    private static class InitialAsynDatabase extends AsyncTask<Void, Void, Void> {
        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;
        private DashwoodDAO dashwoodDAO;

        InitialAsynDatabase(DashwoodAppDatabase dashwoodAppDatabase) {
            this.categoryDAO = dashwoodAppDatabase.getCategoryDAO();
            this.bookDAO = dashwoodAppDatabase.getBookDAO();
            this.dashwoodDAO = dashwoodAppDatabase.getDashwoodDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
