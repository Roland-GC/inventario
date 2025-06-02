package com.example.akari;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your database tables here
        db.execSQL("CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "barcode TEXT," +
                "stock INTEGER," +
                "price TEXT)");

        // Create the sales table
        db.execSQL("CREATE TABLE IF NOT EXISTS sales (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "barcode TEXT," +
                "payment_method TEXT," +
                "name TEXT," +
                "quantity INTEGER," +
                "price TEXT," +
                "FOREIGN KEY (barcode) REFERENCES products(barcode))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean exportDatabase(String outputPath) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            File exportDir = new File(outputPath);

            // Check if the directory was created successfully
            if (!exportDir.exists() && !exportDir.mkdirs()) {
                Log.e("Database", "Error creating export directory");
                return false; // Directory creation failed
            }

            File file = new File(exportDir, "exported_database.csv");

            // Check if the file creation was successful
            if (!file.createNewFile()) {
                Log.e("Database", "Error creating export file");
                return false; // File creation failed
            }

            try (CSVWriter csvWrite = new CSVWriter(new FileWriter(file))) {
                Cursor curCSV = db.rawQuery("SELECT * FROM products", null);
                csvWrite.writeNext(curCSV.getColumnNames());

                while (curCSV.moveToNext()) {
                    String[] arrStr = new String[curCSV.getColumnCount()];
                    for (int i = 0; i < curCSV.getColumnCount(); i++) {
                        arrStr[i] = curCSV.getString(i);
                    }
                    csvWrite.writeNext(arrStr);
                }

                csvWrite.close();
                curCSV.close();
            }
            return true; // Export successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Export failed
        }
    }





    public boolean exportSales(String outputPath) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            File exportDir = new File(outputPath);

            // Check if the directory was created successfully
            if (!exportDir.exists() && !exportDir.mkdirs()) {
                Log.e("Database", "Error creating export directory");
                return false; // Directory creation failed
            }

            File file = new File(exportDir, "exported_sales.csv");

            // Check if the file creation was successful
            if (!file.createNewFile()) {
                Log.e("Database", "Error creating export file");
                return false; // File creation failed
            }

            try (CSVWriter csvWrite = new CSVWriter(new FileWriter(file))) {
                Cursor curCSV = db.rawQuery("SELECT * FROM sales", null);
                csvWrite.writeNext(curCSV.getColumnNames());

                while (curCSV.moveToNext()) {
                    String[] arrStr = new String[curCSV.getColumnCount()];
                    for (int i = 0; i < curCSV.getColumnCount(); i++) {
                        arrStr[i] = curCSV.getString(i);
                    }
                    csvWrite.writeNext(arrStr);
                }

                csvWrite.close();
                curCSV.close();
            }
            return true; // Export successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Export failed
        }
    }





    public void queryProductInfo (String barcode, MainActivity.ProductInfoCallback callback) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT name, stock, price FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                // Check if the column exists before trying to retrieve its index
                int nameIndex = cursor.getColumnIndex("name");
                int stockIndex = cursor.getColumnIndex("stock");
                int priceIndex = cursor.getColumnIndex("price");

                if (nameIndex >= 0 && stockIndex >= 0 && priceIndex >= 0) {
                    // Get product information from the cursor
                    String productName = cursor.getString(nameIndex);
                    int stock = cursor.getInt(stockIndex);
                    String price = cursor.getString(priceIndex);

                    // Create a ProductInfo object with the obtained information
                    ProductInfo productInfo = new ProductInfo(productName, barcode, stock, price);

                    // Notify the callback about the received product information
                    callback.onProductInfoReceived(productInfo);
                } else {
                    // Notify the callback about the error (column not found)
                    callback.onProductInfoError("One or more columns not found");
                }
            } else {
                // Notify the callback about the error (product not found)
                callback.onProductInfoError("Product not found");
            }
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


// Inside the DatabaseHelper class

    public void addSale(String barcode, String paymentMethod, int quantity,String newPrice) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // Query the database to get the product name
            String productName = getProductNameFromBarcode(barcode);
            ContentValues values = new ContentValues();
            values.put("barcode", barcode);
            values.put("payment_method", paymentMethod);
            values.put("name", productName);
            values.put("quantity", quantity);
            values.put("price", newPrice);

            long newRowId = db.insert("sales", null, values);

            if (newRowId == -1) {
                // Insertion failed
                Log.e("DatabaseHelper", "Error inserting new sale");
            } else {
                // Insertion successful
                Log.d("DatabaseHelper", "New sale inserted with ID: " + newRowId);
            }
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    private String getPriceFromBarcode(String barcode) {
        SQLiteDatabase db = getReadableDatabase();
        String price = "";

        try (Cursor cursor = db.rawQuery("SELECT price FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("price");
                if (nameIndex >= 0) {
                    price = cursor.getString(nameIndex);
                }
            }
        }

        return price;
    }

    private String getProductNameFromBarcode(String barcode) {
        SQLiteDatabase db = getReadableDatabase();
        String productName = "";

        try (Cursor cursor = db.rawQuery("SELECT name FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("name");
                if (nameIndex >= 0) {
                    productName = cursor.getString(nameIndex);
                }
            }
        }

        return productName;
    }


    public void updateProductStock(String barcode, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // Update the stock value in the products table
            db.execSQL("UPDATE products SET stock = stock - ? WHERE barcode = ?",
                    new Object[]{quantity, barcode});
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public void addProduct(String name, String barcode, int stock, String price) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("barcode", barcode);
            values.put("stock", stock);
            values.put("price", price);

            long newRowId = db.insert("products", null, values);

            if (newRowId == -1) {
                // Insertion failed
                Log.e("DatabaseHelper", "Error inserting new product");
            } else {
                // Insertion successful
                Log.d("DatabaseHelper", "New product inserted with ID: " + newRowId);
            }
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public int getCurrentStock(String barcode) {
        SQLiteDatabase db = getReadableDatabase();
        int stock = 0;

        try (Cursor cursor = db.rawQuery("SELECT stock FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                int stockIndex = cursor.getColumnIndex("stock");
                if (stockIndex >= 0) {
                    stock = cursor.getInt(stockIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return stock;
    }

    // Inside the DatabaseHelper class

    public List<ProductInfo> getAllProducts() {
        List<ProductInfo> productList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT * FROM products", null)) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex("name");
                int barcodeIndex = cursor.getColumnIndex("barcode");
                int stockIndex = cursor.getColumnIndex("stock");
                int priceIndex = cursor.getColumnIndex("price");

                String name = cursor.getString(nameIndex);
                String barcode = cursor.getString(barcodeIndex);
                int stock = cursor.getInt(stockIndex);
                String price = cursor.getString(priceIndex);

                // Create a ProductInfo object with the obtained information
                ProductInfo productInfo = new ProductInfo(name, barcode, stock, price);

                // Add the product to the list
                productList.add(productInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return productList;
    }


    public List<SaleInfo> getAllSales() {
        List<SaleInfo> salesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT * FROM sales", null)) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex("id");
                int barcodeIndex = cursor.getColumnIndex("barcode");
                int paymentMethodIndex = cursor.getColumnIndex("payment_method");
                int nameIndex = cursor.getColumnIndex("name");
                int quantityIndex = cursor.getColumnIndex("quantity");
                int priceIndex = cursor.getColumnIndex("price");

                int id = cursor.getInt(idIndex);
                String barcode = cursor.getString(barcodeIndex);
                String paymentMethod = cursor.getString(paymentMethodIndex);
                String name = cursor.getString(nameIndex);
                int quantity = cursor.getInt(quantityIndex);
                String price = cursor.getString(priceIndex);

                // Create a SaleInfo object with the obtained information
                SaleInfo saleInfo = new SaleInfo(id,barcode, paymentMethod, name, quantity, price);

                // Add the sale to the list
                salesList.add(saleInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return salesList;
    }


    public void updateProductPrice(String barcode, String newPrice) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("price", newPrice);

            // Update the price value in the products table
            int rowsAffected = db.update("products", values, "barcode = ?", new String[]{barcode});

            if (rowsAffected > 0) {
                // Update successful
                Log.d("DatabaseHelper", "Product price updated for barcode: " + barcode);
            } else {
                // Update failed
                Log.e("DatabaseHelper", "Failed to update product price for barcode: " + barcode);
            }
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public String getProductPrice(String barcode) {
        SQLiteDatabase db = getReadableDatabase();
        String price = "0";  // Initialize as a String with a default value

        try (Cursor cursor = db.rawQuery("SELECT price FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                int priceIndex = cursor.getColumnIndex("price");
                if (priceIndex >= 0) {
                    price = cursor.getString(priceIndex);  // Retrieve as a String
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return price;
    }




    public void updateProductPriceForAll(String barcode, String newPrice, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // Calculate the updated price for the specified quantity
            double updatedPrice = Double.parseDouble(newPrice) / quantity;

            ContentValues values = new ContentValues();
            values.put("price", String.valueOf(updatedPrice));

            // Update the price value in the products table for the specified quantity
            int rowsAffected = db.update("products", values, "barcode = ? AND stock >= ?", new String[]{barcode, String.valueOf(quantity)});

            if (rowsAffected > 0) {
                // Update successful for the specified quantity
                Log.d("DatabaseHelper", "Product price updated for barcode: " + barcode + " for quantity: " + quantity);
            } else {
                // Update failed, handle the case where the stock is not sufficient for the specified quantity
                Log.e("DatabaseHelper", "Failed to update product price for barcode: " + barcode);
            }
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }



}