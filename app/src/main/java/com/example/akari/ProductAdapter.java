package com.example.akari;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductInfo> productList;

    public ProductAdapter(List<ProductInfo> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductInfo product = productList.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productBarcodeTextView.setText(product.getBarcode());
        holder.productStockTextView.setText(String.valueOf(product.getStock()));
        holder.productPriceTextView.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView productBarcodeTextView;
        public TextView productStockTextView;
        public TextView productPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productBarcodeTextView = itemView.findViewById(R.id.productBarcodeTextView);
            productStockTextView = itemView.findViewById(R.id.productStockTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}
