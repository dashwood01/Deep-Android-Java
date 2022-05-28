package com.dashwood.neweducation.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.dashwood.neweducation.BR;

import java.util.Objects;

@Entity(tableName = "book_tb", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = CASCADE))
public class Book extends BaseObservable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "price")
    private String price;

    public Book(int id, int categoryId, String name, String description, String price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && categoryId == book.categoryId && name.equals(book.name) && description.equals(book.description) && price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, description, price);
    }
}
