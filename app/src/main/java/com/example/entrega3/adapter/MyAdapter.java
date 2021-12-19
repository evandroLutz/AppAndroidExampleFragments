package com.example.entrega3.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrega3.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import com.example.entrega3.R;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Usuario> listaUsuarios = new ArrayList<>();
    Context context;
    public MyAdapter(List<Usuario> usuarios) {
        this.listaUsuarios = usuarios;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones, viewGroup, false);
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //retorna o itemList que é passado para o construtor da MyViewHolder
        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //exibe os itens no Recycler
        Usuario u = listaUsuarios.get(position);
        myViewHolder.id.setText(String.valueOf(u.getId()));
        myViewHolder.nome.setText(u.getNome());
        myViewHolder.endereco.setText(u.getEndereco());
        myViewHolder.dataNascimento.setText(u.getDataNasc());
        myViewHolder.genero.setText(u.getGenero());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("NOME", listaUsuarios.get(position).getNome());
        bundle.putString("END", listaUsuarios.get(position).getEndereco());
        bundle.putString("DTNASC", listaUsuarios.get(position).getDataNasc());
//        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editarFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando pessoa")
                .setMessage("Tem certeza que deseja deletar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaUsuarios.remove(position);
                        notifyItemRemoved(position);

                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        TextView id;
        TextView nome;
        TextView endereco;
        TextView dataNascimento;
        TextView genero;
        Button btnDelete;
        Button btnEdit;
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            id = itemView.findViewById(R.id.id);
            nome = itemView.findViewById(R.id.nome);
            endereco = itemView.findViewById(R.id.endereco);
            dataNascimento = itemView.findViewById(R.id.data);
            genero = itemView.findViewById(R.id.genero);
            btnDelete = itemView.findViewById(R.id.excluir);
            btnEdit= itemView.findViewById(R.id.edita);
        }
    }
}
