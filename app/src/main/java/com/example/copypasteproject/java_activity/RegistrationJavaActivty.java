package com.example.copypasteproject.java_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.copypasteproject.R;
import com.example.copypasteproject.copypastedisable.MyEditText;
import com.example.copypasteproject.copypastedisable.MyEditTextListener;
import com.example.copypasteproject.databinding.ActivityRegistrationJavaActivtyBinding;
import com.example.copypasteproject.kotlin_activity.RegistrationKotlinActivity;
import com.example.copypasteproject.utils.AppConstant;

public class RegistrationJavaActivty extends AppCompatActivity {

    private ActivityRegistrationJavaActivtyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration_java_activty);
        // clicklisteners
        loadclicklistener();
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, MainJavaActivity.class));
        finish();
    }

    private void loadclicklistener() {

        binding.txtsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    AppConstant.hideKeyboard(RegistrationJavaActivty.this);
                    AppConstant.showtoast("Registered Successfully", getApplicationContext());
                    onBackPressed();
                } else {
                    AppConstant.showtoast("Something is Wrong!!", getApplicationContext());
                }
            }
        });
    }






    private boolean validation() {
        boolean status = true;

        if (!AppConstant.NAME_PATTERN.matcher(binding.edtName.getText().toString().trim()).matches()) {
            status = false;
        }

        if (!AppConstant.MOBILE_PATTERN.matcher(binding.edtContactnumber.getText().toString().trim()).matches()) {
            status = false;
        }

        if (!binding.radioFemale.isChecked() && !binding.radioMale.isChecked()) {
            status = false;
        }

        if (binding.edtAddress.getText().toString().trim().length() <= 10) {
            status = false;
        }

        if (!AppConstant.INDIANPINCODE.matcher(binding.edtPincode.getText().toString().trim()).matches()) {
            status = false;
        }

        return status;

    }

}