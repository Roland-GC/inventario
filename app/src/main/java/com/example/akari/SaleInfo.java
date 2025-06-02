package com.example.akari;

public class SaleInfo {
    private int id;
    private String barcode;
    private String paymentMethod;
    private String name;
    private int quantity;
    private String price;

    public SaleInfo( int id,String barcode, String paymentMethod, String name, int quantity, String price) {
        this.id = id;
        this.barcode = barcode;
        this.paymentMethod = paymentMethod;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public String getBarcode() {
        return barcode;
    }
    public int getID() {
        return id;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price +" €";
    }
}
