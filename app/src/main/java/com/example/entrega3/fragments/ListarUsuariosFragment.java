package com.example.entrega3.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.entrega3.R;
import com.example.entrega3.adapter.MyAdapter;
import com.example.entrega3.dao.AppDatabase;
import com.example.entrega3.dao.UsuarioDao;
import com.example.entrega3.model.Usuario;

import java.util.List;

public class ListarUsuariosFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listar_usuarios, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        new AsyncTask<Void,Void, List<Usuario>>() {

            @Override
            protected List<Usuario> doInBackground(Void... voids) {
                UsuarioDao usuarioDao = AppDatabase.getInstance(getActivity().getApplicationContext()).createUsuarioDAO();
                return usuarioDao.getAllUsuarios();
            }

            @Override
            protected void onPostExecute(List<Usuario> usuarios) {
                super.onPostExecute(usuarios);
                MyAdapter myAdapter = new MyAdapter( usuarios);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
            }

        }.execute();


        return root;
    }
}
