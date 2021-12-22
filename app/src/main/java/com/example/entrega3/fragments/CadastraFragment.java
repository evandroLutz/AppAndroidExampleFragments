package com.example.entrega3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.entrega3.R;
import com.google.android.material.snackbar.Snackbar;

import com.example.entrega3.model.Usuario;


public class CadastraFragment extends Fragment {
        private EditText nome, endereco,dataNasc;
        private Spinner genero;
        private Button btnCadastrar;
        static int id = 0;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_cadastra, container, false);
            nome = root.findViewById(R.id.editTextnomeUsuario);
            endereco =  root.findViewById((R.id.editTextEnderecoUsuario));
            dataNasc =  root.findViewById(R.id.editTextdataNascUsuario);
            genero = root.findViewById(R.id.spinnerGenero);
            btnCadastrar = root.findViewById(R.id.btnCadUsuario);
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Usuario cadastrado = "+nome.getText().toString(), Snackbar.LENGTH_LONG).show();
                    Usuario usuarioCadastro = new Usuario();
                    usuarioCadastro.setId(id);
                    usuarioCadastro.setNome(nome.getText().toString());
                    usuarioCadastro.setDataNasc(dataNasc.getText().toString());
                    usuarioCadastro.setEndereco(endereco.getText().toString());
                    usuarioCadastro.setGenero(genero.getSelectedItem().toString());
                    Usuario.setUsuario(usuarioCadastro);
                    id +=1;
                    Navigation.findNavController(view).navigate(R.id.action_nav_cadastrar_to_nav_home);
                }
            });
            return root;
        }
    }