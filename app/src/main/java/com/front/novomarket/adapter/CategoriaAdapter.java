package com.front.novomarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.front.novomarket.CategoriaEdActivity;
import com.front.novomarket.R;
import com.front.novomarket.model.Categoria;

import java.util.List;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {
    private Context context;
    private List<Categoria> categorias;

    public CategoriaAdapter(@NonNull Context context, int resource, @NonNull List<Categoria> objects) {
        super(context, resource, objects);
        this.context=context;
        this.categorias=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_categoria, parent, false);
        TextView txtIdCategoria =  rowView.findViewById(R.id.IdCategoria);
        TextView txtNomCategoria = rowView.findViewById(R.id.NomCategoria);
        //asignando datos dentro de las cajas de textos funcionando sin problemas.
        txtIdCategoria.setText(String.format("ID CATEGORIA: %s", categorias.get(position).getIdcat()));
        txtNomCategoria.setText(String.format("NOMBRE DE LA CATEGORIA: %s", categorias.get(position).getNomcat()));

        //aqui se va a poner cuando el usuario presiona en una de las filas va a po
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CategoriaEdActivity.class);
                //Aquien en el primer intent lo estamos transformando en cadena(string)
                intent.putExtra("ID CATEGORIA",String.valueOf(categorias.get(position).getIdcat()));
                intent.putExtra("NOMBRE DE LA CATEGORIA", categorias.get(position).getNomcat());
                //para mostrar todoelformulario
                context.startActivity(intent);
            }
        });

        //Implementando metodos en cada filas va a poder editar
        return rowView;
    }
}
