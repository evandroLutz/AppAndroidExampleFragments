package com.example.entrega3.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrega3.R;
import com.example.entrega3.dao.AppDatabase;
import com.example.entrega3.dao.UsuarioDao;
import com.example.entrega3.model.Livro;
import com.example.entrega3.model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterLivros extends RecyclerView.Adapter<MyAdapterLivros.MyViewHolder> {
    List<Livro> listaLivros = new ArrayList<>();
    Context context;
    public MyAdapterLivros(Context context, List<Livro> livros) {
        this.listaLivros = livros;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones_livros, viewGroup, false);
        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Livro l = listaLivros.get(position);
        myViewHolder.titulo.setText(l.getTitulo());
        myViewHolder.autor.setText(l.getAutor());
        myViewHolder.assunto.setText(l.getAssunto());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("id", l.getId());
        System.out.println("position view"+ position);
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editar, bundle));
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando livro")
                .setMessage("Tem certeza que deseja deletar esse livro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("livro");
                        System.out.println("idString"+ listaLivros.get(position).getId());
                        reference.child(listaLivros.get(position).getId()).removeValue();
                        listaLivros.remove(position);
                        notifyItemRemoved(position);

                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView titulo;
        TextView autor;
        TextView assunto;
        Button btnDelete;
        Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            autor = itemView.findViewById(R.id.autor);
            assunto = itemView.findViewById(R.id.assunto);
            btnDelete = itemView.findViewById(R.id.excluir);
            btnEdit= itemView.findViewById(R.id.edita);
        }
    }

}
