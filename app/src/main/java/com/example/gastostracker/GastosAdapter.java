package com.example.gastostracker;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GastosAdapter extends ArrayAdapter {
    public GastosAdapter( Context context,  List<Gasto> objetcts) {
        super(context, 0, objetcts);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.lista_gastos, parent, false);
        }
        Gasto current = (Gasto) getItem(position);
        TextView descricao = (TextView) listItemView.findViewById(R.id.txtNome);
        TextView data = (TextView) listItemView.findViewById(R.id.txtData);
        TextView valor = (TextView) listItemView.findViewById(R.id.txtValor);
        descricao.setText(current.getDescricao().toString());
        data.setText(current.getData().toString());
        valor.setText(current.getValor().toString());

        return  listItemView;


    }

}