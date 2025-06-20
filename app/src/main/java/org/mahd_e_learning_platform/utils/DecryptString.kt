package org.mahd_e_learning_platform.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

fun decryptString(encrypted: String, key: SecretKey): String {
    val combined = Base64.decode(encrypted, Base64.NO_WRAP)
    val iv = combined.copyOfRange(0, 12)
    val ciphertext = combined.copyOfRange(12, combined.size)
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
    val plainBytes = cipher.doFinal(ciphertext)
    return String(plainBytes)
}