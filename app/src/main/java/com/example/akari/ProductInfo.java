package com.example.akari;

public class ProductInfo {
    private String name;
    private String barcode;
    private int stock;
    private String price;

    public ProductInfo(String name, String barcode, int stock, String price) {
        this.name = name;
        this.barcode = barcode;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getStock() {
        return stock;
    }

    public String getPrice() {
        return price +" €";
    }
}
