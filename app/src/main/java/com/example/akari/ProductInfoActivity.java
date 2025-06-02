package com.example.akari;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.text.style.ForegroundColorSpan;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ProductInfoActivity extends AppCompatActivity {

    private View dialogView;
    private String productName;
    private int stock;
    private String price;
    private AlertDialog priceDialog;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        // Get product information from the intent
        productName = getIntent().getStringExtra("productName");
        String barcode = getIntent().getStringExtra("barcode");
        stock = getIntent().getIntExtra("stock", 0);
        price = getIntent().getStringExtra("price");
        double change = 0;

        // Display product information in TextViews
        TextView productNameTextView = findViewById(R.id.productNameTextView);
        TextView barcodeTextView = findViewById(R.id.barcodeTextView);
        TextView stockTextView = findViewById(R.id.stockTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);


        productNameTextView.setText(getString(R.string.product_name_template, productName));
        barcodeTextView.setText(getString(R.string.barcode_template, barcode));
        stockTextView.setText(getString(R.string.stock_template, stock));
        priceTextView.setText(getString(R.string.price_template, price));


        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);

        // Set click listener for the sale button
        Button saleButton = findViewById(R.id.saleButton);
        saleButton.setOnClickListener(view -> makeSale(barcode));
    }



    private void makeSale(String barcode) {
        // Create a dialog to get the quantity
        AlertDialog.Builder quantityDialogBuilder = new AlertDialog.Builder(this);
        quantityDialogBuilder.setTitle("Cantidad del producto a vender");

        EditText quantityEditText = new EditText(this);
        quantityEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        quantityDialogBuilder.setView(quantityEditText);

        quantityDialogBuilder.setPositiveButton("OK", (quantityDialog, quantityDialogWhich) -> {
            // Set sale information
            int quantity = Integer.parseInt(quantityEditText.getText().toString());

            // Now, proceed to ask for confirmation before changing the price
            askForConfirmation(barcode, quantity);
        });

        quantityDialogBuilder.setNegativeButton("Cancelar", (quantityDialog, quantityDialogWhich) -> {
            // Handle cancellation or dismiss the dialog
        });

        // Show the quantity dialog
        quantityDialogBuilder.show();
    }


    private void askForConfirmation(String barcode, int quantity) {
        // Create a dialog to get confirmation before changing the price
        AlertDialog.Builder confirmationDialogBuilder = new AlertDialog.Builder(this);
        confirmationDialogBuilder.setTitle("Cambiar el precio del producto");
        confirmationDialogBuilder.setMessage("¿Desea cambiar el precio del producto?");

        confirmationDialogBuilder.setPositiveButton("Sí", (confirmationDialog, confirmationDialogWhich) -> {
            // Create a new dialog to get the new price
            AlertDialog.Builder priceDialogBuilder = new AlertDialog.Builder(this);
            priceDialogBuilder.setTitle("Cambiar precio (Precio total de :"+ quantity + " unidades)");

            EditText priceEditText = new EditText(this);
            priceEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            priceDialogBuilder.setView(priceEditText);

            priceDialogBuilder.setPositiveButton("OK", (priceDialog, priceDialogWhich) -> {
                // Get the new price from the EditText
                String newPriceString = priceEditText.getText().toString();

                if (TextUtils.isEmpty(newPriceString)) {
                    // Handle the case where the user didn't enter a new price
                    Toast.makeText(this, "Por favor introduce un nuevo precio", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Now, proceed to ask for confirmation before payment
                askForPayment(barcode, quantity, newPriceString);
            });

            priceDialogBuilder.setNegativeButton("Cancelar", (priceDialog, priceDialogWhich) -> {
                // Dismiss the price dialog
                priceDialog.dismiss();

                // Finish the activity or perform any other necessary action
                finish();
            });

            // Show the price dialog
            priceDialogBuilder.show();
        });

        confirmationDialogBuilder.setNegativeButton("No", (confirmationDialog, confirmationDialogWhich) -> {
            // Proceed directly to payment without changing the price
            askForPayment(barcode, quantity, null);
        });

        // Show the confirmation dialog
        confirmationDialogBuilder.show();
    }


    private void askForPayment(String barcode, int quantity, String newPriceString) {
        // Create a dialog to get the payment method
        AlertDialog.Builder paymentMethodDialogBuilder = new AlertDialog.Builder(this);
        paymentMethodDialogBuilder.setTitle("Seleccionar método de pago");

        // Use a custom layout with radio buttons for payment method selection
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_sale, null);
        paymentMethodDialogBuilder.setView(dialogView);

        RadioGroup paymentMethodRadioGroup = dialogView.findViewById(R.id.paymentMethodRadioGroup);

        paymentMethodDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            int selectedPaymentMethodId = paymentMethodRadioGroup.getCheckedRadioButtonId();
            String precio = databaseHelper.getProductPrice(barcode);
            double preciooriginal = Double.parseDouble(precio);
            if (selectedPaymentMethodId == R.id.cashRadioButton) {
                if (newPriceString == null) {
                    askForCashPayment2(barcode, quantity, preciooriginal);
                } else {
                    askForCashPayment(barcode, quantity, newPriceString,preciooriginal);
                }
            } else if (selectedPaymentMethodId == R.id.cardRadioButton) {
                if (newPriceString == null) {
                    askForCardPayment2(barcode, quantity, preciooriginal);
                } else {
                    askForCardPayment(barcode, quantity, newPriceString);
                }
            }

        });

        paymentMethodDialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> {
            // Handle cancellation or dismiss the dialog
        });

        // Show the payment method dialog
        paymentMethodDialogBuilder.show();
    }

    private void askForCashPayment2(String barcode, int quantity, Double newPriceString) {
        double preciooriginal = Double.parseDouble(databaseHelper.getProductPrice(barcode));

        // Check if there is sufficient stock before proceeding with the sale
        if (isStockAvailable(barcode, quantity)) {
            AlertDialog.Builder cashDialogBuilder = new AlertDialog.Builder(this);

            int moradoColor = getResources().getColor(R.color.colorMorado);

            String titleText = "Dinero que proporciona el cliente";
            SpannableString spannableTitle = new SpannableString(titleText);
            spannableTitle.setSpan(new ForegroundColorSpan(moradoColor), 0, titleText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            cashDialogBuilder.setTitle(spannableTitle);

            EditText cashEditText = new EditText(this);
            cashEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            cashDialogBuilder.setView(cashEditText);

            cashDialogBuilder.setPositiveButton("OK", (cashDialog, cashDialogWhich) -> {
                double cashAmount = Double.parseDouble(cashEditText.getText().toString());

                double totalPrice = calculateTotalPrice(barcode, quantity);
                if (cashAmount < totalPrice) {
                    Toast.makeText(this, "Dinero insuficiente paga moroso", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate the change amount
                double changeAmount = cashAmount - totalPrice;

                // Show a dialog with the change amount and an option to finish the sale
                showChangeDialog2(barcode, quantity, newPriceString.toString(), totalPrice, changeAmount, preciooriginal);
            });

            cashDialogBuilder.setNegativeButton("Cancelar", (cashDialog, cashDialogWhich) -> {
                databaseHelper.updateProductPrice(barcode, String.valueOf(preciooriginal));
            });

            // Show the dialog to ask for the amount of cash
            cashDialogBuilder.show();
        } else {
            // Show a message indicating insufficient stock
            Toast.makeText(this, "Insuficiente stock", Toast.LENGTH_SHORT).show();
        }
    }

    private void askForCashPayment(String barcode, int quantity, String newPriceString, Double preciooriginal) {
        // Check if there is sufficient stock before proceeding with the sale
        if (isStockAvailable(barcode, quantity)) {
            AlertDialog.Builder cashDialogBuilder = new AlertDialog.Builder(this);

            int moradoColor = getResources().getColor(R.color.colorMorado);

            String titleText = "Dinero que proporciona el cliente";
            SpannableString spannableTitle = new SpannableString(titleText);
            spannableTitle.setSpan(new ForegroundColorSpan(moradoColor), 0, titleText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            cashDialogBuilder.setTitle(spannableTitle);

            databaseHelper.updateProductPriceForAll(barcode, String.valueOf(newPriceString), quantity);

            EditText cashEditText = new EditText(this);
            cashEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            cashDialogBuilder.setView(cashEditText);

            cashDialogBuilder.setPositiveButton("OK", (cashDialog, cashDialogWhich) -> {
                double cashAmount = Double.parseDouble(cashEditText.getText().toString());

                double totalPrice = calculateTotalPrice(barcode, quantity);
                if (cashAmount < totalPrice) {
                    Toast.makeText(this, "Dinero insuficiente paga moroso", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate the change amount
                double changeAmount = cashAmount - totalPrice;

                // Show a dialog with the change amount and an option to finish the sale
                showChangeDialog(barcode, quantity, newPriceString, totalPrice, changeAmount, preciooriginal);
            });

            cashDialogBuilder.setNegativeButton("Cancelar", (cashDialog, cashDialogWhich) -> {
                updateProductPrice(barcode, String.valueOf(preciooriginal));
            });

            // Show the dialog to ask for the amount of cash
            cashDialogBuilder.show();
        } else {
            // Show a message indicating insufficient stock
            Toast.makeText(this, "Insuficiente stock", Toast.LENGTH_SHORT).show();
        }
    }

    private void showChangeDialog(String barcode, int quantity, String newPriceString, double totalPrice, double changeAmount, double preciooriginal) {
        AlertDialog.Builder changeDialogBuilder = new AlertDialog.Builder(this);

        // Customize the dialog content to show the change amount
        String changeText = "Cambio: " + String.format(Locale.getDefault(), "%.2f", changeAmount);
        changeDialogBuilder.setMessage(changeText);
        changeDialogBuilder.setTitle("Confirmar venta");

        changeDialogBuilder.setPositiveButton("Sí", (changeDialog, changeDialogWhich) -> {
            // Save the sale information in the database
            saveSale(barcode, "Efectivo", quantity, newPriceString);

            // Update the stock value in the products table
            updateProductStock(barcode, quantity);

            // Restore the original price in the database
            updateProductPrice(barcode, String.valueOf(preciooriginal));


            finish();
        });

        changeDialogBuilder.setNegativeButton("No", (changeDialog, changeDialogWhich) -> {
            // Restore the original price in the database
            updateProductPrice(barcode, String.valueOf(preciooriginal));

            // Finish the activity or perform any other necessary action
            finish();
        });

        // Show the change dialog
        changeDialogBuilder.show();
    }


    private void showChangeDialog2(String barcode, int quantity, String newPriceString, double totalPrice, double changeAmount, double preciooriginal) {
        AlertDialog.Builder changeDialogBuilder = new AlertDialog.Builder(this);

        // Customize the dialog content to show the change amount
        String changeText = "Cambio: " + String.format(Locale.getDefault(), "%.2f", changeAmount);
        changeDialogBuilder.setMessage(changeText);
        changeDialogBuilder.setTitle("Confirmar venta");

        changeDialogBuilder.setPositiveButton("Sí", (changeDialog, changeDialogWhich) -> {
            // Save the sale information in the database
            saveSale2(barcode, "Efectivo", quantity, newPriceString);

            // Update the stock value in the products table
            updateProductStock(barcode, quantity);

            // Restore the original price in the database
            updateProductPrice(barcode, String.valueOf(preciooriginal));

            // Finish the activity or perform any other necessary action
            finish();
        });

        changeDialogBuilder.setNegativeButton("No", (changeDialog, changeDialogWhich) -> {
            // Restore the original price in the database
            updateProductPrice(barcode, String.valueOf(preciooriginal));

            // Finish the activity or perform any other necessary action
            finish();
        });

        // Show the change dialog
        changeDialogBuilder.show();
    }


   private void askForCardPayment2(String barcode, int quantity, Double newPriceString) {
    double preciooriginal = Double.parseDouble(databaseHelper.getProductPrice(barcode));
    String newPriceString2 = String.valueOf(newPriceString);

    // Check if there is sufficient stock before proceeding with the sale
    if (isStockAvailable(barcode, quantity)) {
        // For example, you can show another dialog to confirm card payment
        AlertDialog.Builder cardPaymentConfirmationDialogBuilder = new AlertDialog.Builder(this);
        cardPaymentConfirmationDialogBuilder.setTitle("Confirmar venta con tarjeta");
        cardPaymentConfirmationDialogBuilder.setMessage("¿Desea pagar con tarjeta?");

        cardPaymentConfirmationDialogBuilder.setPositiveButton("Sí", (confirmationDialog, confirmationDialogWhich) -> {
            // Dismiss the price dialog

            // Show an additional confirmation dialog before saving the sale
            AlertDialog.Builder saveSaleConfirmationDialogBuilder = new AlertDialog.Builder(this);
            saveSaleConfirmationDialogBuilder.setTitle("Confirmar venta");
            saveSaleConfirmationDialogBuilder.setMessage("¿Desea confirmar la venta?");

            saveSaleConfirmationDialogBuilder.setPositiveButton("Sí", (saveSaleConfirmationDialog, saveSaleConfirmationDialogWhich) -> {
                // Update the product price for this sale

                // Save the sale
                saveSale2(barcode, "Tarjeta", quantity, newPriceString2);

                // Update the stock value in the products table
                updateProductStock(barcode, quantity);

                updateProductPrice(barcode, String.valueOf(preciooriginal));

                // Finish the activity or perform any other necessary action
                finish();
            });

            saveSaleConfirmationDialogBuilder.setNegativeButton("No", (saveSaleConfirmationDialog, saveSaleConfirmationDialogWhich) -> {
                // Finish the activity or perform any other necessary action
                updateProductPrice(barcode, String.valueOf(preciooriginal));
                finish();
            });

            // Show the save sale confirmation dialog
            saveSaleConfirmationDialogBuilder.show();
        });

        cardPaymentConfirmationDialogBuilder.setNegativeButton("No", (confirmationDialog, confirmationDialogWhich) -> {
            // Finish the activity or perform any other necessary action
            updateProductPrice(barcode, String.valueOf(preciooriginal));
            finish();
        });

        // Show the card payment confirmation dialog
        cardPaymentConfirmationDialogBuilder.show();
    } else {
        // Show a message indicating insufficient stock
        Toast.makeText(this, "Insuficiente stock", Toast.LENGTH_SHORT).show();
    }
}




    private void askForCardPayment(String barcode, int quantity, String newPriceString) {
        double preciooriginal = Double.parseDouble(databaseHelper.getProductPrice(barcode));

        // Check if there is sufficient stock before proceeding with the sale
        if (isStockAvailable(barcode, quantity)) {
            // For example, you can show another dialog to confirm card payment
            AlertDialog.Builder cardPaymentConfirmationDialogBuilder = new AlertDialog.Builder(this);
            cardPaymentConfirmationDialogBuilder.setTitle("Confirmar venta con tarjeta");
            cardPaymentConfirmationDialogBuilder.setMessage("¿Desea pagar con tarjeta?");

            cardPaymentConfirmationDialogBuilder.setPositiveButton("Sí", (confirmationDialog, confirmationDialogWhich) -> {
                // Dismiss the price dialog

                // Show an additional confirmation dialog before saving the sale
                AlertDialog.Builder saveSaleConfirmationDialogBuilder = new AlertDialog.Builder(this);
                saveSaleConfirmationDialogBuilder.setTitle("Confirmar venta");
                saveSaleConfirmationDialogBuilder.setMessage("¿Desea confirmar la venta?");

                saveSaleConfirmationDialogBuilder.setPositiveButton("Sí", (saveSaleConfirmationDialog, saveSaleConfirmationDialogWhich) -> {
                    // Update the product price for this sale
                    databaseHelper.updateProductPriceForAll(barcode, newPriceString, quantity);

                    // Save the sale
                    saveSale(barcode, "Tarjeta", quantity, newPriceString);

                    // Update the stock value in the products table
                    updateProductStock(barcode, quantity);

                    updateProductPrice(barcode, String.valueOf(preciooriginal));

                    // Finish the activity or perform any other necessary action
                    finish();
                });

                saveSaleConfirmationDialogBuilder.setNegativeButton("No", (saveSaleConfirmationDialog, saveSaleConfirmationDialogWhich) -> {
                    // Finish the activity or perform any other necessary action
                    databaseHelper.updateProductPrice(barcode, String.valueOf(preciooriginal));
                    finish();
                });

                // Show the save sale confirmation dialog
                saveSaleConfirmationDialogBuilder.show();
            });

            cardPaymentConfirmationDialogBuilder.setNegativeButton("No", (confirmationDialog, confirmationDialogWhich) -> {
                // Finish the activity or perform any other necessary action
                databaseHelper.updateProductPrice(barcode, String.valueOf(preciooriginal));
                finish();
            });

            // Show the card payment confirmation dialog
            cardPaymentConfirmationDialogBuilder.show();
        } else {
            // Show a message indicating insufficient stock
            Toast.makeText(this, "Insuficiente stock", Toast.LENGTH_SHORT).show();
        }
    }

    // Check if there is sufficient stock for the given quantity
    private boolean isStockAvailable(String barcode, int quantity) {
        // Retrieve the current stock from your database
        int currentStock = databaseHelper.getCurrentStock(barcode);

        // Check if there is sufficient stock for the sale
        return currentStock >= quantity;
    }



    private double calculateTotalPrice(String barcode, int quantity) {
        // Query the database to get the price based on the barcode
        double price = getPriceFromBarcode(barcode);

        // Calculate the total price
        return price * quantity;
    }


    private double getPriceFromBarcode(String barcode) {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        double price = 0.0;

        try (Cursor cursor = db.rawQuery("SELECT price FROM products WHERE barcode = ? COLLATE NOCASE", new String[]{barcode})) {
            if (cursor.moveToFirst()) {
                int priceIndex = cursor.getColumnIndex("price");
                if (priceIndex >= 0) {
                    price = cursor.getDouble(priceIndex);
                }
            }
        } finally {

            db.close();
        }

        return price;
    }



    private void saveSale(String barcode, String paymentMethod, int quantity,String price) {
        // Save the sale information in the sales table

        databaseHelper.addSale(barcode, paymentMethod, quantity,price);
        Toast.makeText(this, "Se añade la venta", Toast.LENGTH_SHORT).show();

    }

    private void saveSale2(String barcode, String paymentMethod, int quantity,String price) {
        // Save the sale information in the sales table
        double newprice = Double.parseDouble(price) * quantity;
        String pricen= String.valueOf(newprice);
        databaseHelper.addSale(barcode, paymentMethod, quantity,pricen);
        Toast.makeText(this, "Se añade la venta", Toast.LENGTH_SHORT).show();

    }

    private void updateProductStock(String barcode, int quantity) {
        // Update the stock value in the products table
        databaseHelper.updateProductStock(barcode, quantity);
        Toast.makeText(this, "Se actualiza la venta", Toast.LENGTH_SHORT).show();
    }

    private void updateProductPrice(String barcode, String preciooriginal) {
        // Update the stock value in the products table
        databaseHelper.updateProductPrice(barcode, preciooriginal);
        Toast.makeText(this, "Se actualiza el precio", Toast.LENGTH_SHORT).show();
    }
}
