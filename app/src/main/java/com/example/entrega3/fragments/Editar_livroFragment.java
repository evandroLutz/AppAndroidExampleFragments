package com.example.entrega3.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.entrega3.R;
import com.example.entrega3.model.Livro;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Editar_livroFragment extends Fragment {
    private TextInputEditText titulo;
    private TextInputEditText autor;
    private TextInputEditText assunto;
    private Button btnEditar;
    private String key;
    private String tituloBundle;
    private String autorBundle;
    private String assuntoBundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar_livro, container, false);
        Bundle bundle = getArguments();
        titulo = root.findViewById(R.id.editTextTitulo);
        autor = root.findViewById(R.id.editTextAutor);
        assunto = root.findViewById(R.id.editTextAssunto);
        btnEditar = root.findViewById(R.id.btnEditarExemplar);
        tituloBundle = bundle.getString("titulo");
        autorBundle = bundle.getString("autor");
        assuntoBundle = bundle.getString("assunto");
        key = bundle.getString("id");
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    private void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Editando livro")
                .setMessage("Tem certeza que deseja editar esse livro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("livro").child(key);
                        String tituloString = titulo.getText().toString();
                        String autorString = autor.getText().toString();
                        String assuntoString = assunto.getText().toString();

                        if(tituloString.isEmpty() && autorString.isEmpty() && assuntoString.isEmpty()){
                            Snackbar.make(getView(), "Nenhum campo preenchido", Snackbar.LENGTH_LONG).show();

                        }else{

                            if(tituloString.isEmpty()){
                                tituloString = tituloBundle;
                            }
                            if(autorString.isEmpty()){
                                autorString = autorBundle;
                            }
                            if(assuntoString.isEmpty()){
                                assuntoString = assuntoBundle;
                            }

                            Livro l = new Livro(tituloString, autorString, assuntoString);
                            reference.setValue(l);
                            Snackbar.make(getView(), "item editado!!!", Snackbar.LENGTH_LONG).show();
                            Navigation.findNavController(getView()).navigate(R.id.action_nav_editar_livro_to_nav_listar_livros);
                        }

                    }
                }).setNegativeButton("Não", null).show();
    }
}