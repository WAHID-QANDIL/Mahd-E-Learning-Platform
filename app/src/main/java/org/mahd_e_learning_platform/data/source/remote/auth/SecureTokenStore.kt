package org.mahd_e_learning_platform.data.source.remote.auth

import android.content.Context
import android.util.Base64
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

val Context.dataStore by preferencesDataStore(name = "secure_datastore")

class SecureTokenStore(private val context: Context) {
    companion object {
        private const val MASTER_KEY_ALIAS = "my_app_master_key"
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val IS_LOGGED_IN = stringPreferencesKey("isLoggedIn")
        private val IS_FIRST_LAUNCH = booleanPreferencesKey("isFirstLaunch")
    }

    @Volatile
    private var cachedToken: String? = null

    // Synchronous method for interceptors
    fun getTokenSynchronously(): String? {
        return cachedToken
    }

    private val secretKey: SecretKey by lazy {
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        val entry = keyStore.getEntry(MASTER_KEY_ALIAS, null) as KeyStore.SecretKeyEntry
        entry.secretKey
    }


    val accessTokenFlow = context.dataStore.data
        .map { prefs ->
            prefs[ACCESS_TOKEN_KEY]?.let { encryptedBase64 ->
                decryptString(encryptedBase64, secretKey)
            }
        }

    val accessLoginState = context.dataStore.data.map { prefs ->
        prefs[IS_LOGGED_IN]?.let {
            decryptString(it, secretKey)
        }
    }

    val accessFirstLaunchState = context.dataStore.data.map { prefs ->
        prefs[IS_FIRST_LAUNCH] ?: true
    }

    suspend fun saveAccessToken(token: String) {
        val encrypted = encryptString(token, secretKey)
        cachedToken = token
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = encrypted
        }
    }

    suspend fun saveFirstLaunch(boolean: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH] = boolean
        }
    }

    private fun encryptString(plain: String, key: SecretKey): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv                                   // 12‑byte random nonce
        val ciphertext = cipher.doFinal(plain.toByteArray())
        val combined = iv + ciphertext                        // IV needs to be saved alongside data
        return Base64.encodeToString(combined, Base64.NO_WRAP) // single‑line Base64
    }

    private fun decryptString(encrypted: String, key: SecretKey): String {
        val combined = Base64.decode(encrypted, Base64.NO_WRAP)
        val iv = combined.copyOfRange(0, 12)
        val ciphertext = combined.copyOfRange(12, combined.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
        val plainBytes = cipher.doFinal(ciphertext)
        return String(plainBytes)
    }


}