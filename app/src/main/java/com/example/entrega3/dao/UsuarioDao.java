package com.example.entrega3.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.entrega3.entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM Usuario")
    public List<Usuario> getAllUsuarios();

    @Query("SELECT * FROM Usuario WHERE nome =:name")
    public  List<Usuario> getUsuarioByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Usuario usuario);
    @Update
    public void update(Usuario usuario);
    @Delete
    public void delete(Usuario usuario);
}
