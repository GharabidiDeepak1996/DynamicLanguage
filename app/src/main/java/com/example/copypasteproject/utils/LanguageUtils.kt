package com.example.copypasteproject.utils

import android.content.Context
import android.util.Log
import com.example.copypasteproject.BuildConfig
import com.example.copypasteproject.R
import com.google.android.gms.common.util.IOUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets


class LanguageUtils {
    companion object {
        fun setLanguage(context: Context, code: Int) {
//https://androidwave.com/firebase-remote-config/
           // FirebaseRemoteConfig.getInstance().getString("hindi_language")

            val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            val configBuilder = FirebaseRemoteConfigSettings.Builder()
            if (BuildConfig.DEBUG) {
                val cacheInterval: Long = 0
                configBuilder.setMinimumFetchIntervalInSeconds(cacheInterval)
            }
            firebaseRemoteConfig.setConfigSettingsAsync(configBuilder.build())

            firebaseRemoteConfig.fetch().addOnSuccessListener {
                Log.d("TAG", "setLanguage13: ${firebaseRemoteConfig.getString("english_language")}")
            }
            firebaseRemoteConfig.fetch().addOnFailureListener {
                Log.d("TAG", "setLanguage12: ${it}")
            }
            firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener{
                if (it.isSuccessful) {

                }
                Log.d("TAG", "setLanguage18 : ${it.isSuccessful}")
            }
            var content: InputStream = if (code == 1) {
                //context.resources.openRawResource(R.raw.english_language)
                firebaseRemoteConfig.getString("english_language").byteInputStream(Charsets.UTF_8)
            } else {
                firebaseRemoteConfig.getString("hindi_language").byteInputStream(Charsets.UTF_8)
               //context.resources.openRawResource(R.raw.hindi_language)
            }

            var byteArrayOutputStream = ByteArrayOutputStream()
            var n: Int

            try {
                n = content.read()

                while (n != -1) {
                    // converting string into byte array
                    byteArrayOutputStream.write(n)
                    n = content.read()
                }
                content.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val jsonString: String = byteArrayOutputStream.toString()

            //language string
            PreferenceManager.saveStringForKey(context, LanguageConstant.PREFERENCE_CURRENTLANGUAGE, jsonString)
            //language code
            PreferenceManager.saveStringForKey(context, LanguageConstant.PREFERENCE_LANGUAGECODE, code.toString())
        }

        fun getLanguageString(tag: String,context: Context): String {
            if (PreferenceManager.getStringForKey(
                    context,
                    LanguageConstant.PREFERENCE_LANGUAGECODE,
                    null
                ) != null
            ) {
                val result = JSONObject(
                    PreferenceManager.getStringForKey(context,
                        LanguageConstant.PREFERENCE_CURRENTLANGUAGE,
                        ""
                    )
                )

                val data = when(tag) {
                    //result.has(tag)
                     tag -> { result.getString(tag) }
                    else -> "?"
                }

                return data.toString().trim()
            } else {

                return "?"
            }
        }


    }
}