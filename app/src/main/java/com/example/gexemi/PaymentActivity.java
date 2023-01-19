package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    TextView accountname,accountNumber,ifscCode,branchName,payableAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        accountname = findViewById(R.id.accountname);
        accountNumber = findViewById(R.id.accountNumber);
        ifscCode = findViewById(R.id.ifscCode);
        branchName = findViewById(R.id.branchName);
        payableAmount = findViewById(R.id.payableAmount);

        accountname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", accountname.toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentActivity.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        accountNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", accountNumber.toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentActivity.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        ifscCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", ifscCode.toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentActivity.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        branchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", branchName.toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentActivity.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        payableAmount.setText("Rs.1000");


    }
}