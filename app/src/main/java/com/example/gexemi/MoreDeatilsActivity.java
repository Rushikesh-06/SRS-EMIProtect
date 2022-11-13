package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MoreDeatilsActivity extends AppCompatActivity {

    TextView MD_username,MD_registerno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_deatils);

        MD_username = findViewById(R.id.MD_username);
        MD_registerno = findViewById(R.id.MD_registerno);

        Bundle bundle = getIntent().getExtras();
        String musername = bundle.getString("Username");
        String mregisterno = bundle.getString("Phoneno");

        MD_username.setText(musername);
        MD_registerno.setText(mregisterno);

    }
}