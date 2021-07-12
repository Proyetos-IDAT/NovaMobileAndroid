package com.front.novomarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.front.novomarket.R;
import com.front.novomarket.model.Cliente;
import com.front.novomarket.model.ComprobantePago;
import com.front.novomarket.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteAdapter extends RecyclerView.Adapter<ComprobanteAdapter.ComprobanteViewHolder> {

    private Context context;
    private List<ComprobantePago> comprobantePagos;
   
    private OnItemClickListener clickListener;

    public ComprobanteAdapter(Context context, List<ComprobantePago> comprobantePagos, OnItemClickListener clickListener){
        this.context=context;
        this.comprobantePagos=comprobantePagos;
        this.clickListener=clickListener;
    }


    @NonNull
    @Override
    public ComprobanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.comprobante_item,parent,false);
        return new ComprobanteViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ComprobanteViewHolder holder, int position) {
        ComprobantePago c=comprobantePagos.get(position);

        holder.tvId.setText("ID:"+c.getId());
        holder.tvfecha.setText("fecha:"+c.getFecha());
        holder.tvcliente.setText("cliente:"+c.getClienteId());
        holder.tvproducto.setText("producto: "+c.getProductoId());
        holder.tvmetodopago.setText("metodopago: "+c.getMetodoPagoId());
    }
    
    
    @Override
    public int getItemCount() {
        return comprobantePagos.size(); 
    }

    public class ComprobanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView tvId,tvfecha,tvcliente,tvproducto,tvmetodopago;
        OnItemClickListener onItemClickListener;

        public ComprobanteViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            //para agregar al listado
            tvId =  itemView.findViewById(R.id.tvId);
            tvfecha =  itemView.findViewById(R.id.tvfecha);
            tvcliente=itemView.findViewById(R.id.tvcliente);
            tvproducto=itemView.findViewById(R.id.tvProducto);
            tvmetodopago=itemView.findViewById(R.id.tvmetodopago);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(comprobantePagos.get(getAdapterPosition()));
        }
    }
}
