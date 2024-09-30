package com.example.educai.data.contexts

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

private fun getOrCreateMasterKey(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        TokenManager.PREF_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    return sharedPreferences
}

object TokenManager {
    const val PREF_NAME = "encrypted_prefs"
    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"

    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
        val sharedPreferences = getOrCreateMasterKey(context)

        // Save token
        with(sharedPreferences.edit()) {
            putString(ACCESS_TOKEN_KEY, accessToken)
            putString(REFRESH_TOKEN_KEY, refreshToken)
            apply()
        }
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = getOrCreateMasterKey(context)

        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun getRefreshToken(context: Context): String? {
        val sharedPreferences = getOrCreateMasterKey(context)

        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }

    fun clearTokens(context: Context) {
        val sharedPreferences = getOrCreateMasterKey(context)

        with(sharedPreferences.edit()) {
            remove(ACCESS_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            apply()
        }
    }
}
