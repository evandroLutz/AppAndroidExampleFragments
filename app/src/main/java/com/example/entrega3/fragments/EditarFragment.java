package com.example.entrega3.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.entrega3.R;
import com.example.entrega3.activities.MainActivity;
import com.example.entrega3.dao.AppDatabase;
import com.example.entrega3.dao.UsuarioDao;
import com.example.entrega3.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class EditarFragment extends Fragment {
    private TextInputEditText nome, endereco,dataNasc;
    private Spinner genero;
    private Button btnEditar;
    private int idRecebido;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);
        Bundle bundle = getArguments();
        idRecebido = bundle.getInt("id");
        nome = root.findViewById(R.id.editTextnomeUsuario);
        endereco =  root.findViewById((R.id.editTextEnderecoUsuario));
        dataNasc =  root.findViewById(R.id.editTextdataNascUsuario);
        genero = root.findViewById(R.id.spinnerGenero);
        btnEditar = root.findViewById(R.id.btnEditarUsuario);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    public void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("O usuário esta sendo editado")
                .setMessage("Deseja proceder com a edição do usuário?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                AsyncTask<Void, Void, Void> execute = new AsyncTask<Void, Void, Void>() {
                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                        UsuarioDao usuarioDao = AppDatabase.getInstance(getActivity().getApplicationContext()).createUsuarioDAO();
                                        Usuario usuario = usuarioDao.getUsuarioById(idRecebido);
                                        if(!nome.getText().toString().isEmpty()){

                                            usuario.setNome(nome.getText().toString());

                                        }

                                        if(!endereco.getText().toString().isEmpty()){

                                            usuario.setEndereco(endereco.getText().toString());

                                        }

                                        if(!dataNasc.getText().toString().isEmpty()){

                                            usuario.setDataNasc(dataNasc.getText().toString());

                                        }
                                        usuario.setGenero(genero.getSelectedItem().toString());
                                        usuarioDao.update(usuario);
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        String mensagem = "Registro editado com sucesso!";
                                        Toast.makeText(getActivity().getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getActivity().getApplicationContext() , MainActivity.class);
                                        startActivity(intent);
                                    }
                                }.execute();

                            }



                    }
                ).setNegativeButton("Não", null).show();

    }

}