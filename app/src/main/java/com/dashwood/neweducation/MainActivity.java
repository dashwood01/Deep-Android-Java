package com.dashwood.neweducation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dashwood.neweducation.adapter.RecItemBookAdapter;
import com.dashwood.neweducation.databinding.ActivityMainBinding;
import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;
import com.dashwood.neweducation.viewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityClickHandler handler;
    private MainActivityViewModel viewModel;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(getApplicationContext());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCategoriesLiveData().observe(this, categories -> {
            categoryList = categories;
            showItemAdapter(categories);
        });
    }

    private void showItemAdapter(List<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        binding.setSpinnerAdapter(adapter);
        binding.setClick(handler);
    }

    private void loadBooks(int id) {
        viewModel.getBooksLiveData(id).observe(this, books -> {
             viewRecView(books);
        });
    }

    private void viewRecView(List<Book> books) {
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        RecItemBookAdapter adapter = new RecItemBookAdapter();
        binding.recView.setAdapter(adapter);
        adapter.setItems(books);
    }

    public class MainActivityClickHandler {
        private Context context;

        MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onClick(AdapterView<?> parent, View view, int position, long id) {
            loadBooks(categoryList.get(position).getId());
        }
    }
}