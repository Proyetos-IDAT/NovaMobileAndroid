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

import com.front.novomarket.ClienteEdActivity;
import com.front.novomarket.R;
import com.front.novomarket.model.Cliente;

import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {
    private Context context;
    private List<Cliente> clientes;

    public ClienteAdapter(@NonNull Context context, int resource, @NonNull List<Cliente> objects) {
        super(context, resource,objects);
        this.context=context;
        this.clientes=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_cliente, parent, false);
        TextView txtIdCliente =  rowView.findViewById(R.id.IdCliente);
        TextView txtNombre = rowView.findViewById(R.id.Nombre);
        TextView txtApellidos = rowView.findViewById(R.id.Apellidos);
        TextView txtNumero =  rowView.findViewById(R.id.Numero);
        //asignando datos dentro de las cajas de textos funcionando sin problemas.
        txtIdCliente.setText(String.format("ID: %s", clientes.get(position).getIdcli()));
        txtNombre.setText(String.format("NOMBRE: %s", clientes.get(position).getNomcli()));
        txtApellidos.setText(String.format("APELLIDO: %s", clientes.get(position).getApecli()));
        txtNumero.setText(String.format("NUMERO: %s", clientes.get(position).getNumcli()));

        //aqui se va a poder el usuario realizar las modificaciones
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ClienteEdActivity.class);
                //Aquien en el primer intent lo estamos transformando en cadena(string)
                intent.putExtra("ID",String.valueOf(clientes.get(position).getIdcli()));
                intent.putExtra("NOMBRE", clientes.get(position).getNomcli());
                intent.putExtra("APELLIDOS", clientes.get(position).getApecli());
                intent.putExtra("NUMERO", clientes.get(position).getNumcli());
                //para mostrar todoelformulario
                context.startActivity(intent);
            }
        });

        //Implementando metodos en cada filas va a poder editar
        return rowView;
    }

}
