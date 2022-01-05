package com.example.entrega3.fragments;

import android.app.Activity;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.entrega3.R;
import com.example.entrega3.dao.AppDatabase;
import com.example.entrega3.dao.UsuarioDao;
import com.google.android.material.snackbar.Snackbar;

import com.example.entrega3.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class CadastraFragment extends Fragment {
        private TextInputEditText nome, endereco,dataNasc;
        private TextInputLayout nomeLayout, enderecoLayout, dataNascLayout;
        private Spinner genero;
        private Button btnCadastrar;
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
                        new AsyncTask<Void, Integer, Integer>() {
                            @Override
                            protected Integer doInBackground(Void... voids) {
                                UsuarioDao usuarioDAO = AppDatabase.getInstance(getActivity().getApplicationContext()).createUsuarioDAO();
                                Usuario usuario = new Usuario();
                                usuario.setNome(nome.getText().toString());
                                usuario.setEndereco(endereco.getText().toString());
                                usuario.setDataNasc(dataNasc.getText().toString());
                                usuario.setGenero(genero.getSelectedItem().toString());
                                usuarioDAO.insert(usuario);
                                return usuario.getId();
                            }

                            @Override
                            protected void onPostExecute(Integer id) {
                                if (id == null)
                                    Toast.makeText(getActivity().getApplicationContext(), "O usuário não foi cadastrado!", Toast.LENGTH_LONG).show();
                                else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }.execute();
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