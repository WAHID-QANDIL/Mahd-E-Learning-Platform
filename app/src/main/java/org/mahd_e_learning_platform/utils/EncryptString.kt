package org.mahd_e_learning_platform.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey

fun encryptString(plain: String, key: SecretKey): String {
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val iv = cipher.iv                                   // 12‑byte random nonce
    val ciphertext = cipher.doFinal(plain.toByteArray())
    val combined = iv + ciphertext                        // IV needs to be saved alongside data
    return Base64.encodeToString(combined, Base64.NO_WRAP) // single‑line Base64
}