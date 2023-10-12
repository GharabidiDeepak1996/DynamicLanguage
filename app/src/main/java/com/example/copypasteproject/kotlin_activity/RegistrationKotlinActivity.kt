package com.example.copypasteproject.kotlin_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.copypasteproject.R
import com.example.copypasteproject.databinding.ActivityRegistrationJavaActivtyBinding
import com.example.copypasteproject.utils.AppConstant
import com.example.copypasteproject.utils.LanguageConstant
import com.example.copypasteproject.utils.LanguageUtils


class RegistrationKotlinActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityRegistrationJavaActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_registration_java_activty)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration_java_activty)

        binding.edtPincode.addListener { binding.edtPincode.setText("") }
        loadclicklistener()
        init_language()
    }

    private fun init_language() {
        binding.apply {
            txtName.text = LanguageUtils.getLanguageString(LanguageConstant.enterName,this@RegistrationKotlinActivity)
            edtName.hint = LanguageUtils.getLanguageString(LanguageConstant.enterName,this@RegistrationKotlinActivity)
            txtcontactnumber.text = LanguageUtils.getLanguageString(LanguageConstant.enterMobileNumber,this@RegistrationKotlinActivity)
            txtgender.text = LanguageUtils.getLanguageString(LanguageConstant.gender,this@RegistrationKotlinActivity)
            radioMale.text = LanguageUtils.getLanguageString(LanguageConstant.male,this@RegistrationKotlinActivity)
            radioFemale.text = LanguageUtils.getLanguageString(LanguageConstant.female,this@RegistrationKotlinActivity)
            txtaddress.text = LanguageUtils.getLanguageString(LanguageConstant.address,this@RegistrationKotlinActivity)
            txtpincode.text = LanguageUtils.getLanguageString(LanguageConstant.pincode,this@RegistrationKotlinActivity)
            edtPincode.hint = LanguageUtils.getLanguageString(LanguageConstant.pincode,this@RegistrationKotlinActivity)
            txtsubmit.text = LanguageUtils.getLanguageString(LanguageConstant.submit,this@RegistrationKotlinActivity)
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainKotlinActivity::class.java))
    }

    private fun loadclicklistener() {
        binding.txtsubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.txtsubmit -> {
                if (validation()) {
                    AppConstant.hideKeyboard(this@RegistrationKotlinActivity)
                    AppConstant.showtoast(
                        LanguageUtils.getLanguageString(LanguageConstant.newCustomerAddedSuccessfully,this@RegistrationKotlinActivity),
                        this
                    )
                    onBackPressed()
                } else {
                    AppConstant.showtoast(
                        LanguageUtils.getLanguageString(LanguageConstant.somethingWentWrong,this@RegistrationKotlinActivity),
                        this
                    )

                }
            }
        }
    }

    private fun validation(): Boolean {
        var status = true

        binding.apply {
            if (!AppConstant.NAME_PATTERN.matcher(edtName.text.toString().trim()).matches()) {
                status = false
            }

            if (!AppConstant.MOBILE_PATTERN.matcher(edtContactnumber.text.toString().trim())
                    .matches()
            ) {
                status = false
            }

            if (!radioMale.isChecked && !radioFemale.isChecked) {
                status = false
            }

            if (edtAddress.text.toString().trim().length <= 10) {
                status = false
            }

            if (!AppConstant.INDIANPINCODE.matcher(edtPincode.text.toString().trim()).matches()) {
                status = false
            }

            return status
        }

    }


}