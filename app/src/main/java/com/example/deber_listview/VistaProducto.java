package com.example.deber_listview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.deber_listview.classes.Productos;
import com.google.gson.Gson;

public class VistaProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_producto);

        String productoJson = getIntent().getStringExtra("producto");

        setTitle("Producto Seleccionado");

        Gson gson = new Gson();
        Productos producto = gson.fromJson(productoJson, Productos.class);

        ImageView imageView = findViewById(R.id.image);
        TextView title = findViewById(R.id.title);
        TextView price = findViewById(R.id.price);
        TextView category = findViewById(R.id.category);

        Glide.with(this).load(producto.getImage()).into(imageView);
        title.setText(producto.getTitle());
        price.setText(String.format("$%.2f", producto.getPrice()));
        category.setText(producto.getCategory());
    }
}