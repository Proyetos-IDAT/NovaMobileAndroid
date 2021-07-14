package com.front.novomarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.front.novomarket.R;
import com.front.novomarket.model.Producto;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private List<Producto> productos;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context = context;
        this.productos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_producto, parent, false);

        //Declarando Variables
        TextView txtIdProducto =  rowView.findViewById(R.id.IdProducto);
        TextView txtNomProducto = rowView.findViewById(R.id.NomProducto);
        TextView txtPrecProd = rowView.findViewById(R.id.PrecProd);
        TextView txtFechaVenc = rowView.findViewById(R.id.fechaVencimiento);
        ImageView ivProd = rowView.findViewById(R.id.ivProducto);
        Date fechavenc = productos.get(position).getFechavenc();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        //Agregando datos a las variables :v
        txtIdProducto.setText(String.format("ID PRODUCTO: "+productos.get(position).getIdprod()));
        txtNomProducto.setText(String.format(productos.get(position).getNomprod()));
        txtPrecProd.setText(productos.get(position).getPrecio().toString());
        txtFechaVenc.setText("Fecha de vencimiento: "+dateFormat.format(fechavenc));

        //Imagen
        Glide.with(context).load("http://192.168.1.15:5050/api/productos/foto/"+productos.get(position)
                .getIdprod())
                .into(ivProd);
        return rowView;
    }
}
