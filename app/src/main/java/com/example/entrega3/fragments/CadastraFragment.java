package com.example.entrega3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.entrega3.R;
import com.google.android.material.snackbar.Snackbar;

import com.example.entrega3.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class CadastraFragment extends Fragment {
        private TextInputEditText nome, endereco,dataNasc;
        private TextInputLayout nomeLayout, enderecoLayout, dataNascLayout;
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
            nomeLayout = root.findViewById(R.id.nomeUsuario);
            enderecoLayout =  root.findViewById((R.id.enderecoUsuario));
            dataNascLayout =  root.findViewById(R.id.dataNascUsuario);
            genero = root.findViewById(R.id.spinnerGenero);
            btnCadastrar = root.findViewById(R.id.btnCadUsuario);
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validarCampos()){
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

                }
            });
            return root;
        }

        private boolean validarCampos(){
            if(nome.getText().toString().isEmpty()){
                nomeLayout.setErrorEnabled(true);
                nomeLayout.setError("Informe o seu nome");
                return false;
            }else{
                nomeLayout.setErrorEnabled(false);
            }

            if(endereco.getText().toString().isEmpty()){
                enderecoLayout.setErrorEnabled(true);
                enderecoLayout.setError("Informe o seu endereço");
                return false;
            }else{
                enderecoLayout.setErrorEnabled(false);
            }

            if(dataNasc.getText().toString().isEmpty()){
                dataNascLayout.setErrorEnabled(true);
                dataNascLayout.setError("Informe a sua data de nascimento");
                return false;
            }else{
                dataNascLayout.setErrorEnabled(false);
            }


            return true;
        }
    }