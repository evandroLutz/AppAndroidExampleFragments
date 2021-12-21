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

import com.example.entrega3.R;
import com.google.android.material.snackbar.Snackbar;

public class EditarFragment extends Fragment {
    private AppCompatEditText txtNome;
    private Button btnEditar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);
        txtNome = root.findViewById(R.id.editTextnomeUsuario);
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
                        Snackbar.make(getView(), "O usuário foi editado!!!", Snackbar.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_nav_editarFragment_to_nav_listarFragment);
                    }
                }).setNegativeButton("Não", null).show();

    }
}