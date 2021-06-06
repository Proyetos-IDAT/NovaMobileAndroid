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

import com.front.novomarket.ProveedorEdActivity;
import com.front.novomarket.R;
;
import com.front.novomarket.model.Proveedor;

import java.util.List;
public class ProveedorAdapter extends ArrayAdapter<Proveedor>{
        private Context context;
        private List<Proveedor> proveedores;

        //Se crea un contructor para llamar los objetes y contexto
        public ProveedorAdapter(@NonNull Context context, int resource , @NonNull List<Proveedor> objects) {
            super(context, resource,objects);
            this.context=context;
            this.proveedores =objects;
        }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_proveedor, parent, false);
        TextView txtIdProveedor =  rowView.findViewById(R.id.IdProveedor);
        TextView txtNomProve = rowView.findViewById(R.id.NomProveedor);
        TextView txtRuc = rowView.findViewById(R.id.Ruc);
        TextView txtNomContacto =  rowView.findViewById(R.id.NomContacto);
        TextView txtDireccion =  rowView.findViewById(R.id.Direccion);
        TextView txtTelefono =  rowView.findViewById(R.id.Telefono);

        txtIdProveedor.setText(String.format("ID:%s", proveedores.get(position).getIdprove()));
        txtNomProve.setText(String.format("NOMBRE PROVEEDOR:%s", proveedores.get(position).getNomprove()));
        txtRuc.setText(String.format("RUC:%s", proveedores.get(position).getRuc()));
        txtNomContacto.setText(String.format("NOMBRE CONTACTO:%s", proveedores.get(position).getNomcontacto()));
        txtDireccion.setText(String.format("DIRECCION:%s", proveedores.get(position).getDireccion()));
        txtTelefono.setText(String.format("TELEFONO:%s", proveedores.get(position).getTelefono()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProveedorEdActivity.class);

                intent.putExtra("ID",String.valueOf(proveedores.get(position).getIdprove()));
                intent.putExtra("NOMBRE PROVEEDOR", proveedores.get(position).getNomprove());
                intent.putExtra("RUC", proveedores.get(position).getRuc());
                intent.putExtra("NOMBRE CONTACTO", proveedores.get(position).getNomcontacto());
                intent.putExtra("DIRECCION", proveedores.get(position).getDireccion());
                intent.putExtra("TELEFONO", proveedores.get(position).getTelefono());

                context.startActivity(intent);
            }
        });

        return rowView;
    }

}
