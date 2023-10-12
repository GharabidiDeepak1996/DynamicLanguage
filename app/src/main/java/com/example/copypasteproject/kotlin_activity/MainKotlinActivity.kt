package com.example.copypasteproject.kotlin_activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.copypasteproject.R
import com.example.copypasteproject.databinding.ActivityMainJavaBinding
import com.example.copypasteproject.utils.AppConstant
import com.example.copypasteproject.utils.LanguageConstant
import com.example.copypasteproject.utils.LanguageUtils
import com.example.copypasteproject.utils.PreferenceManager


class MainKotlinActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainJavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_java)

        if (PreferenceManager.getStringForKey(
                this,
                LanguageConstant.PREFERENCE_LANGUAGECODE,
                null
            ) == null
        ) {
            //initial
            LanguageUtils.setLanguage(this@MainKotlinActivity, 1)
        }

        init_language()
        loadclicklistener()
    }


    private fun init_language() {
        binding.apply {
            try{
                textViewSignin.text = LanguageUtils.getLanguageString(LanguageConstant.signIn,this@MainKotlinActivity)
                tvLabelEmailId.text = LanguageUtils.getLanguageString(LanguageConstant.enterEmailID,this@MainKotlinActivity)
                editEmailID.hint = LanguageUtils.getLanguageString(LanguageConstant.enterEmailID,this@MainKotlinActivity)
                tvLabelpin.text = LanguageUtils.getLanguageString(LanguageConstant.enterPIN,this@MainKotlinActivity)
                textViewSigninBtn.text = LanguageUtils.getLanguageString(LanguageConstant.signIn,this@MainKotlinActivity)
                textForgotPassword.text = LanguageUtils.getLanguageString(LanguageConstant.forgotPin,this@MainKotlinActivity)
                textViewRegisterBtn.text = LanguageUtils.getLanguageString(LanguageConstant.signUpRegister,this@MainKotlinActivity)
            }catch (e: Exception){
                Log.d("TAG", "init_language: "+e)
            }
        }
    }

    private fun loadclicklistener() {
        binding.textViewSigninBtn.setOnClickListener(this)
        binding.textViewRegisterBtn.setOnClickListener(this)
        binding.rdgLanguage.setOnCheckedChangeListener { _, checkedId -> // find which radio button is selected
            when (checkedId) {
                R.id.chk_hindi -> {
                    LanguageUtils.setLanguage(this@MainKotlinActivity, 2)
                    init_language()
                }
                R.id.chk_english -> {
                    LanguageUtils.setLanguage(this@MainKotlinActivity, 1)
                    init_language()
                }

            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.textViewSignin -> {
                if ("admin@gmail.com" == binding.editEmailID.text.toString().trim()
                    && "admin" == binding.edtUseridpassword.text.toString()
                ) {
                    AppConstant.showtoast(
                        LanguageUtils.getLanguageString(LanguageConstant.loginSuccess,this),
                        applicationContext
                    )
                } else {
                    AppConstant.showtoast(
                        LanguageUtils.getLanguageString(LanguageConstant.somethingWentWrong,this),
                        applicationContext
                    )
                }
                AppConstant.hideKeyboard(this@MainKotlinActivity)

            }

            binding.textViewRegisterBtn -> {
                var intent = Intent(this, RegistrationKotlinActivity::class.java)
                startActivity(intent)
            }
        }
    }
}