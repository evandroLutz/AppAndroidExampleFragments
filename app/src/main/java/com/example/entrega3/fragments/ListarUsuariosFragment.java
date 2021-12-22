package com.example.entrega3.fragments;

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
import com.example.entrega3.model.Usuario;

public class ListarUsuariosFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listar_usuarios, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        Usuario.getUsuarios();
        MyAdapter myAdapter = new MyAdapter( Usuario.getUsuarios());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        return root;
    }
}
