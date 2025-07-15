package com.example.deber_listview;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deber_listview.adapters.AdapterProductos;
import com.example.deber_listview.classes.Productos;
import com.example.deber_listview.services.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AdapterProductos adapterProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Productos");

        listView = findViewById(R.id.listView);

        new GetProductosTask().execute();

        // Set the item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Productos producto = (Productos) parent.getItemAtPosition(position);
                Gson gson = new Gson();
                String productoJson = gson.toJson(producto);

                Intent intent = new Intent(MainActivity.this, VistaProducto.class);
                intent.putExtra("producto", productoJson);
                startActivity(intent);
            }
        });
    }

    private class GetProductosTask extends AsyncTask<String, Void, ArrayList<Productos>> {
        @Override
        protected ArrayList<Productos> doInBackground(String... strings) {
            try {
                String response = HttpRequest.getRequest("https://fakestoreapi.com/products");
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Productos>>() {
                }.getType();
                return gson.fromJson(response, listType);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Productos> productos) {
            if (productos != null) {
                adapterProductos = new AdapterProductos(MainActivity.this, productos);
                listView.setAdapter(adapterProductos);
            } else {
                Toast.makeText(MainActivity.this, "Error al obtener los productos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}