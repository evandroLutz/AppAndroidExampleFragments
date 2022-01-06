package com.example.entrega3.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.entrega3.R;
import com.example.entrega3.model.Livro;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastrar_livroFragment extends Fragment {
    private Button btnCadastrar;
    View root;
    TextInputEditText titulo;
    TextInputEditText autor;
    TextInputEditText assunto;
    TextInputLayout tituloExemplar;
    TextInputLayout assuntoExemplar;
    TextInputLayout autorExemplar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cadastrar_livro, container, false);
        titulo = root.findViewById(R.id.editTextTitulo);
        autor = root.findViewById(R.id.editTextAutor);
        assunto = root.findViewById(R.id.editTextAssunto);
        tituloExemplar = root.findViewById(R.id.layoutTitulo);
        assuntoExemplar = root.findViewById(R.id.layoutAssunto);
        autorExemplar = root.findViewById(R.id.layoutAutor);
        btnCadastrar = root.findViewById(R.id.btnCadastraExemplar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clickado com sucesso", Snackbar.LENGTH_LONG).show();
                cadLivro(view);
            }
        });
        return root;
    }
    public void cadLivro(View view){
        if(validarCampos()){
        Snackbar.make(view, "Entrou cad livro", Snackbar.LENGTH_LONG).show();

            Livro livro = new Livro();
            livro.setTitulo(titulo.getText().toString());
            livro.setAutor(autor.getText().toString());
            livro.setAssunto(assunto.getText().toString());
            DatabaseReference livros = FirebaseDatabase.getInstance().getReference().child("livro");
            livros.push().setValue(livro).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Snackbar.make(view, "Cadastrado com sucesso", Snackbar.LENGTH_LONG).show();
                    //Navigation.findNavController(view).navigate(R.id.ac);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(view, "Erro ao cadastrar usuario!", Snackbar.LENGTH_LONG)
                                    .setTextColor(Color.RED).show();
                        }
                    });
        }

    }

    private boolean validarCampos(){
        if(titulo.getText().toString().isEmpty()){
            tituloExemplar.setErrorEnabled(true);
            tituloExemplar.setError("Informe o Titulo");
            return false;
        }else{
            tituloExemplar.setErrorEnabled(false);
        }

        if(autor.getText().toString().isEmpty()){
            autorExemplar.setErrorEnabled(true);
            autorExemplar.setError("Informe o Autor");
            return false;
        }else{
            autorExemplar.setErrorEnabled(false);
        }

        if(assunto.getText().toString().isEmpty()){
            assuntoExemplar.setErrorEnabled(true);
            assuntoExemplar.setError("Informe o Assunto");
            return false;
        }else{
            assuntoExemplar.setErrorEnabled(false);
        }

        return true;
    }
}