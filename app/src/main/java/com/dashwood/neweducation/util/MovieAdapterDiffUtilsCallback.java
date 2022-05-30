package com.dashwood.neweducation.util;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.inf.User;
import com.dashwood.neweducation.model.Book;

import java.util.List;
import java.util.Objects;

public class MovieAdapterDiffUtilsCallback extends DiffUtil.Callback {
    final List<Result> newList;
    final List<Result> oldList;

    public MovieAdapterDiffUtilsCallback(List<Result> newList, List<Result> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition).getId(), newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
