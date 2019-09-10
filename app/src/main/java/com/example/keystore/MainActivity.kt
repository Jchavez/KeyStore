package com.example.keystore

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.security.KeyPairGenerator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keyPairGenerator()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun keyPairGenerator() {
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA,
            "AndroidKeyStore"
        )

        val parameterSpec: KeyGenParameterSpec =
            KeyGenParameterSpec.Builder(
                "CryptoKeyAlias",
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).run {
                setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                build()
            }

        kpg.initialize(parameterSpec)

        val kp = kpg.generateKeyPair()

        Log.e("PRIVATE KEY", kp.private.toString())
        Log.e("PUBLIC KEY", kp.public.toString())
    }
}
