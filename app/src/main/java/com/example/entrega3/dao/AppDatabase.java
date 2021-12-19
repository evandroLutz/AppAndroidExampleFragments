package com.example.entrega3.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.entrega3.model.Usuario;


@Database(entities = {Usuario.class}, version = 11)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract UsuarioDao createUsuarioDAO();

    public static AppDatabase getInstance(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "driver_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}