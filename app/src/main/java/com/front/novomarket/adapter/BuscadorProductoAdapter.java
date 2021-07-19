package com.front.novomarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.front.novomarket.R;
import com.front.novomarket.model.Producto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuscadorProductoAdapter extends RecyclerView.Adapter<BuscadorProductoAdapter.ProductoViewHolder> {

    Context context;
    List<Producto> listaProductos;

    public BuscadorProductoAdapter(Context context, List<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_busca_producto, viewGroup, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder productoViewHolder, int i) {


    productoViewHolder.NomBuscProducto.setText("Producto: "+listaProductos.get(i).getNomprod());
    productoViewHolder.IdBuscProducto.setText(String.valueOf("Id Producto: "+listaProductos.get(i).getIdprod()));
    productoViewHolder.PrecBuscProd.setText(String.valueOf("Precio: S/."+listaProductos.get(i).getPrecio()));
    productoViewHolder.stockBuscActu.setText(String.valueOf("Stock Actual: "+listaProductos.get(i).getStock_act()));
    productoViewHolder.stockBuscMin.setText(String.valueOf("Stock Mínimo: "+listaProductos.get(i).getStock_min()));
    Date fechavenc=listaProductos.get(i).getFechavenc();
    DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
    productoViewHolder.fechaBuscVencimiento.setText("Fecha: "+dateFormat.format(fechavenc));
    Glide.with(context).load(listaProductos.get(i).getFoto()).into(productoViewHolder.ivBuscProducto);

    /*Date fechavenc = productos.get(position).getFechavenc();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);*/
        /*txtFechaVenc.setText("Fecha de vencimiento: "+dateFormat.format(fechavenc));*/
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBuscProducto;
        TextView NomBuscProducto,PrecBuscProd,fechaBuscVencimiento,stockBuscActu,stockBuscMin,IdBuscProducto;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBuscProducto=itemView.findViewById(R.id.ivBuscProducto);
            NomBuscProducto=itemView.findViewById(R.id.NomBuscProducto);
            PrecBuscProd=itemView.findViewById(R.id.PrecBuscProd);
            fechaBuscVencimiento=itemView.findViewById(R.id.fechaBuscVencimiento);
            stockBuscActu=itemView.findViewById(R.id.stockBuscActu);
            stockBuscMin=itemView.findViewById(R.id.stockBuscMin);
            IdBuscProducto=itemView.findViewById(R.id.IdBuscProducto);


        }
    }
    public void filtrar(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
    }
}
