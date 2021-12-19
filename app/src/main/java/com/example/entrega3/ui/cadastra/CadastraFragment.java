package com.example.entrega3.ui.cadastra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.entrega3.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class CadastraFragment extends Fragment {
        private AppCompatEditText txtNome;
        private Button btnCadastrar;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_cadastra, container, false);
            txtNome = root.findViewById(R.id.editTextnomeUsuario);
            btnCadastrar = root.findViewById(R.id.btnCadUsuario);
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Nome informado = "+txtNome.getText().toString(), Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.action_nav_cadastrar_to_nav_home);
                }
            });
            return root;
        }
    }