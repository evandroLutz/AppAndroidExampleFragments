package com.example.entrega3.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.entrega3.model.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class EditarFragment extends Fragment {
    private TextInputEditText nome, endereco,dataNasc;
    private TextInputLayout nomeLayout, enderecoLayout, dataNascLayout;
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
        nomeLayout = root.findViewById(R.id.nomeUsuario);
        enderecoLayout =  root.findViewById((R.id.enderecoUsuario));
        dataNascLayout =  root.findViewById(R.id.dataNascUsuario);
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
                        List<Usuario> usuarios = Usuario.getUsuarios();
                        for (Usuario usuario : usuarios) {
                            if (usuario.getId() == idRecebido) {

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
                                Snackbar.make(getView(), "O usuário foi editado!!!", Snackbar.LENGTH_LONG).show();
                                Navigation.findNavController(getView()).navigate(R.id.action_nav_editarFragment_to_nav_listarFragment);

                            }

                        }

                    }
                }).setNegativeButton("Não", null).show();

    }
}