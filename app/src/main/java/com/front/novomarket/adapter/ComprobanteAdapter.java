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

        holder.tvtitulo.setText("Comprobante NovoMarket");
        holder.tvId.setText("ID: "+c.getId());
        holder.tvfecha.setText("Fecha: "+c.getFecha());
        holder.tvcliente.setText("Cliente: "+c.getClienteId());
        holder.tvproducto.setText("Producto: "+c.getProductoId());
        holder.tvmetodopago.setText("Método de pago: "+c.getMetodoPagoId());
        holder.tvprecio.setText("Precio: S/"+c.getPrecio());
        holder.tvigv.setText("IGV : %0.18" );
        holder.tvcantidad.setText("Cantidad: "+c.getCantidad());
        holder.tvtotal.setText("Precio Total: S/"+(((c.getCantidad()*c.getPrecio())+((c.getCantidad()*c.getPrecio())*0.18))));
    }
    
    
    @Override
    public int getItemCount() {
        return comprobantePagos.size(); 
    }

    public class ComprobanteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView tvId,tvfecha,tvcliente,tvproducto,tvmetodopago,tvprecio,tvcantidad,tvtotal,tvtitulo,tvigv;
        OnItemClickListener onItemClickListener;

        public ComprobanteViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            //para agregar al listado
            tvtitulo=itemView.findViewById(R.id.tvtitulo);
            tvigv=itemView.findViewById(R.id.tvIgv);
            tvId =  itemView.findViewById(R.id.tvId);
            tvfecha =  itemView.findViewById(R.id.tvfecha);
            tvcliente=itemView.findViewById(R.id.tvcliente);
            tvproducto=itemView.findViewById(R.id.tvProducto);
            tvmetodopago=itemView.findViewById(R.id.tvmetodopago);
            tvprecio=itemView.findViewById(R.id.tvprecio);
            tvcantidad=itemView.findViewById(R.id.tvcantidad);
            tvtotal=itemView.findViewById(R.id.tvtotal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(comprobantePagos.get(getAdapterPosition()));
        }
    }
}
