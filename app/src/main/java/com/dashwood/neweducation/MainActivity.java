package com.dashwood.neweducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dashwood.neweducation.adapter.RecItemBookAdapter;
import com.dashwood.neweducation.databinding.ActivityMainBinding;
import com.dashwood.neweducation.extra.A;
import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;
import com.dashwood.neweducation.viewModel.MainActivityViewModel;
import com.dashwood.neweducation.viewModel.MainActivityViewModelFactory;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityClickHandler handler;
    private MainActivityViewModel viewModel;
    private List<Category> categoryList;

    /*@Inject
    private MainActivityViewModelFactory viewModelFactory;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //A.getApplicationClass().getBookComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(getApplicationContext());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
       // viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete = books.get(viewHolder.getAdapterPosition());
                viewModel.deleteNewBook(bookToDelete);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(binding.recView);
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