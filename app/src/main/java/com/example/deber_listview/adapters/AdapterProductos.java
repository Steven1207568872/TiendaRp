package com.example.deber_listview.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deber_listview.R;
import com.example.deber_listview.classes.Productos;

import java.util.ArrayList;

public class AdapterProductos extends BaseAdapter {

    private Context context;
    private ArrayList<Productos> productos;

    public AdapterProductos(Context context, ArrayList<Productos> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        }

        Productos producto = productos.get(position);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView category = convertView.findViewById(R.id.category);
        TextView title = convertView.findViewById(R.id.title);
        TextView price = convertView.findViewById(R.id.price);
        TextView description = convertView.findViewById(R.id.description);

        Glide.with(context).load(producto.getImage()).into(imageView);
        title.setText(producto.getTitle());
        price.setText(String.format("$%.2f", producto.getPrice()));
        description.setText(producto.getDescription());
        category.setText(producto.getCategory());

        return convertView;
    }
}
