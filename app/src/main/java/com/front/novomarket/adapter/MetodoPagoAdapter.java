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

import com.front.novomarket.MetodoPagoEdActivity;
import com.front.novomarket.R;
import com.front.novomarket.model.MetodoPago;

import java.util.List;

public class MetodoPagoAdapter extends ArrayAdapter<MetodoPago> {
    private Context context;
    private List<MetodoPago> metodopagos;

    //Se crea un contructor para llamar los objetes y contexto
    public MetodoPagoAdapter(@NonNull Context context, int resource, @NonNull List<MetodoPago> objects) {
        super(context, resource,objects);
        this.context=context;
        this.metodopagos=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_metodopago, parent, false);
        TextView txtIdMetodoPago =  rowView.findViewById(R.id.IdMetPago);
        TextView txtTipoPago = rowView.findViewById(R.id.TipoPago);

        txtIdMetodoPago.setText(String.format("ID: %s", metodopagos.get(position).getIdmetpago()));
        txtTipoPago.setText(String.format("TIPO DE PAGO: %s", metodopagos.get(position).getTipopago()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MetodoPagoEdActivity.class);

                intent.putExtra("ID",String.valueOf(metodopagos.get(position).getIdmetpago()));
                intent.putExtra("TIPO DE PAGO", metodopagos.get(position).getTipopago());

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
