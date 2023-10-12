package com.example.copypasteproject.java_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.copypasteproject.R;
import com.example.copypasteproject.databinding.ActivityMainJavaBinding;
import com.example.copypasteproject.utils.AppConstant;


public class MainJavaActivity extends AppCompatActivity {
    private ActivityMainJavaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_java);

        // clicklisteners
        loadclicklistener();
    }

    private void loadclicklistener() {

        binding.textViewSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("admin@gmail.com".equals(binding.editEmailID.getText().toString().trim()) && "admin".equals(binding.edtUseridpassword.getText().toString())) {

                    AppConstant.showtoast("Login Successful", getApplicationContext());
                    AppConstant.hideKeyboard(MainJavaActivity.this);
                }
            }
        });


        binding.textViewRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainJavaActivity.this, RegistrationJavaActivty.class);
                startActivity(intent);
                finish();
            }
        });
    }


}