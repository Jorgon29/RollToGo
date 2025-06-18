package com.terraplanistas.rolltogo.data.repository.settings

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec

object Encrypt {

    private const val SECRET_KEY = "1234567890abcdef" // 16 caracteres = 16 bytes
    private const val INIT_VECTOR = "abcdefghijklmnop" // 16 caracteres = 16 bytes

    private val charset = Charsets.UTF_8

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(charset), "AES")
        val ivSpec = IvParameterSpec(INIT_VECTOR.toByteArray(charset))
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encrypted = cipher.doFinal(input.toByteArray(charset))
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    fun decrypt(encrypted: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(charset), "AES")
        val ivSpec = IvParameterSpec(INIT_VECTOR.toByteArray(charset))
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decrypted = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT))
        return String(decrypted, charset)
    }
}


