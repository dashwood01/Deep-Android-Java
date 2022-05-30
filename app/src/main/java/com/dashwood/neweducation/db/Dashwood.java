package com.dashwood.neweducation.db;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "dashwood_tb")
public class Dashwood extends BaseObservable {
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    public Dashwood() {
    }

    public Dashwood(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
        /*notifyPropertyChanged(BR.id);
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.email);*/
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public int getId() {
        return id;
    }
}
