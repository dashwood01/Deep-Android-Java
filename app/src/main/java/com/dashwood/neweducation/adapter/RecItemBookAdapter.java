package com.dashwood.neweducation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dashwood.neweducation.databinding.RecViewBookBinding;
import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.util.BookAdapterDiffUtilsCallback;

import java.util.ArrayList;
import java.util.List;

public class RecItemBookAdapter extends RecyclerView.Adapter<RecItemBookAdapter.ViewHolder> {
    private OnClickItem listener;
    private List<Book> bookList = new ArrayList<>();


    public void setListener(OnClickItem listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecViewBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBook(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setItems(List<Book> list) {
        /*this.bookList = list;
        notifyDataSetChanged();*/
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BookAdapterDiffUtilsCallback(bookList, list));
        bookList = list;
        result.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final RecViewBookBinding binding;

        public ViewHolder(@NonNull RecViewBookBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener == null || position == RecyclerView.NO_POSITION)
                    return;
                listener.onClickItem(bookList.get(position));
            });
        }
    }

    public interface OnClickItem {
        void onClickItem(Book book);
    }
}
