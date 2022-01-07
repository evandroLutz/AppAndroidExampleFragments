package com.example.entrega3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entrega3.R;
import com.example.entrega3.adapter.MyAdapterLivros;
import com.example.entrega3.model.Livro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listar_livrosFragment extends Fragment {
    List<Livro> livros = new ArrayList<>();
    MyAdapterLivros myAdapter;
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_listar_livros, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewLivros);
        carregaLivros();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        return root;
    }
    private void carregaLivros(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("livro");
        livros = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Livro livro = ds.getValue(Livro.class);
                    livro.setId(ds.getKey());
                    livros.add(livro);
                }
                myAdapter = new MyAdapterLivros(root.getContext(), livros);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}