package com.example.akari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ViewAllSalesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SalesAdapter salesAdapter;
    private DatabaseHelper databaseHelper;
    private Button buttonBackHome; // Declaración del botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_sales);

        recyclerView = findViewById(R.id.recyclerView);
        buttonBackHome = findViewById(R.id.buttonBackHome); // Conexión con el botón en el XML
        databaseHelper = new DatabaseHelper(this);

        // Obtener todas las ventas de la base de datos
        List<SaleInfo> allSales = databaseHelper.getAllSales();

        // Configurar RecyclerView y su adaptador
        salesAdapter = new SalesAdapter(allSales);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(salesAdapter);

        // Listener para el botón "Volver a Inicio"
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllSalesActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra esta actividad si ya no es necesaria
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
