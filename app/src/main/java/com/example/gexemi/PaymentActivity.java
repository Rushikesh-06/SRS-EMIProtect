package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    TextView accountname,accountNumber,ifscCode,branchName,payableAmount;
    Button btn_upipayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        accountname = findViewById(R.id.accountname);
        accountNumber = findViewById(R.id.accountNumber);
        ifscCode = findViewById(R.id.ifscCode);
        branchName = findViewById(R.id.branchName);
        payableAmount = findViewById(R.id.payableAmount);
        btn_upipayment = findViewById(R.id.btn_upipayment);

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

        btn_upipayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("upi://pay?pa=goelectronix@kotak&pn=Miinakshi%20Karande&tn=Payment&tr=GEX%201220350&cu=INR&mode=02&mbbnumber=9004949483&am=1");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        payableAmount.setText("Rs.1000");


    }
}