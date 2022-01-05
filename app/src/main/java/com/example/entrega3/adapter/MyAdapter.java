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

import com.example.entrega3.dao.AppDatabase;
import com.example.entrega3.dao.UsuarioDao;
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
        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
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
        bundle.putString("id", String.valueOf(position));
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editar, bundle));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("o usuário será deletado")
                .setMessage("Tem certeza que deseja proceder?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                UsuarioDao usuarioDAO = AppDatabase.getInstance(context.getApplicationContext()).createUsuarioDAO();
                                List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
                                usuarioDAO.delete(usuarios.get(position));

                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                atualizaLista(position);
                            }
                        }.execute();
                    }}).setNegativeButton("Não", null).show();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView nome;
        TextView endereco;
        TextView dataNascimento;
        TextView genero;
        Button btnDelete;
        Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nome = itemView.findViewById(R.id.nome);
            endereco = itemView.findViewById(R.id.endereco);
            dataNascimento = itemView.findViewById(R.id.data);
            genero = itemView.findViewById(R.id.genero);
            btnDelete = itemView.findViewById(R.id.excluir);
            btnEdit= itemView.findViewById(R.id.edita);
        }
    }

    public void atualizaLista(int position){
        String mensagem = "Registro excluído com sucesso!";
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
        listaUsuarios.remove(position);
        this.notifyDataSetChanged();
    }
}
