package com.example.akari;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder> {
    private List<SaleInfo> salesList;

    public SalesAdapter(List<SaleInfo> salesList) {
        this.salesList = salesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sale, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaleInfo sale = salesList.get(position);
        holder.saleIDTextView.setText(String.valueOf(sale.getID()));
        holder.saleBarcodeTextView.setText(sale.getBarcode());
        holder.salePaymentMethodTextView.setText(sale.getPaymentMethod());
        holder.saleNameTextView.setText(sale.getName());
        holder.saleQuantityTextView.setText(String.valueOf(sale.getQuantity()));
        holder.salePriceTextView.setText(sale.getPrice());
    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView saleIDTextView;
        public TextView saleBarcodeTextView;
        public TextView salePaymentMethodTextView;
        public TextView saleNameTextView;
        public TextView saleQuantityTextView;
        public TextView salePriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            saleIDTextView = itemView.findViewById(R.id.saleIDTextView);
            saleBarcodeTextView = itemView.findViewById(R.id.saleBarcodeTextView);
            salePaymentMethodTextView = itemView.findViewById(R.id.salePaymentMethodTextView);
            saleNameTextView = itemView.findViewById(R.id.saleNameTextView);
            saleQuantityTextView = itemView.findViewById(R.id.saleQuantityTextView);
            salePriceTextView = itemView.findViewById(R.id.salePriceTextView);
        }
    }
}
