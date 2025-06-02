package com.example.akari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent; // Import necesario
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Import necesario

import java.util.List;

public class ViewAllStockActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;
    private Button buttonBackHome; // Declarar botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_stock);

        recyclerView = findViewById(R.id.recyclerView);
        buttonBackHome = findViewById(R.id.buttonBackHome); // Buscar botón
        databaseHelper = new DatabaseHelper(this);

        // Query all products from the database
        List<ProductInfo> allProducts = databaseHelper.getAllProducts();

        // Set up RecyclerView and adapter
        productAdapter = new ProductAdapter(allProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(productAdapter);

        // Listener para el botón "Volver a Inicio"
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia MainActivity.class por el nombre de tu pantalla principal
                Intent intent = new Intent(ViewAllStockActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad
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
