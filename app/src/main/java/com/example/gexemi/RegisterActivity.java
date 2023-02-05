package com.example.gexemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mail, mobileno, Password, confirmpassword, username, adarcardno, pancardno;
    ImageView pass_icon, confirmpass_icon;
    AppCompatButton signup;
    TextView signin;
    private boolean passwordshowing = false;
    private boolean confirm_passwordshowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail = findViewById(R.id.SU_et_mail);
        mobileno = findViewById(R.id.SU_et_mobile);
        Password = findViewById(R.id.SU_et_reg_password);
        confirmpassword = findViewById(R.id.SU_et_confirmpassword);
        pass_icon = findViewById(R.id.password_icon);
        confirmpass_icon = findViewById(R.id.confirmpassword_icon);
        signup = findViewById(R.id.SU_btn_sign_up);
        username = findViewById(R.id.SU_username);
        adarcardno = findViewById(R.id.SU_adarcard);
        pancardno = findViewById(R.id.SU_pancard);

        pass_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordshowing) {
                    passwordshowing = false;
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass_icon.setImageResource(R.drawable.ic_password_show);
                } else {
                    passwordshowing = true;
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_icon.setImageResource(R.drawable.ic_password_hide);
                }
            }
        });

        confirmpass_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirm_passwordshowing) {
                    confirm_passwordshowing = false;
                    confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirmpass_icon.setImageResource(R.drawable.ic_password_show);
                } else {
                    confirm_passwordshowing = true;
                    confirmpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confirmpass_icon.setImageResource(R.drawable.ic_password_hide);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getusername = username.getText().toString();
                String getadarno = adarcardno.getText().toString();
                String getpanno = pancardno.getText().toString();
                String getmobileytext = mobileno.getText().toString();
                String getmailtext = mail.getText().toString();
                String getpassword = Password.getText().toString();
                String getcnfm_pass = confirmpassword.getText().toString();

                if (!getpassword.equals(getcnfm_pass)) {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(getmailtext) && TextUtils.isEmpty(getmobileytext) && TextUtils.isEmpty(getpassword) && TextUtils.isEmpty(getcnfm_pass)
                        && TextUtils.isEmpty(getusername) && TextUtils.isEmpty(getadarno) && TextUtils.isEmpty(getpanno)) {
                    Toast.makeText(RegisterActivity.this, "Please add registration credentials . .", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}