package sinet.startup.indrive.util

import android.util.Base64
import io.michaelrocks.paranoid.Obfuscate
import sinet.startup.indrive.BuildConfig
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Obfuscate
object CryptUtil {
    private fun base64Decoder(base64: String): ByteArray =
        Base64.decode(base64, Base64.URL_SAFE)

    private fun encryptPackageName(packageName: String): String {
        val digest: ByteArray
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.reset()
        messageDigest.update(packageName.toByteArray())
        digest = messageDigest.digest()
        val bigInt = BigInteger(1, digest)
        var md5Hex = bigInt.toString(16)
        while (md5Hex.length < 32) {
            md5Hex = "0$md5Hex"
        }
        return md5Hex
    }

    private fun cipherDecode(md5Key: String, base64Link: ByteArray): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val key = SecretKeySpec(md5Key.toByteArray(), "AES")
        val ivSpec = BuildConfig.APPLICATION_ID.take(16).toByteArray(StandardCharsets.UTF_8)
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(ivSpec))
        return String(cipher.doFinal(base64Link))
    }

    fun decrypt(packageName: String, base64Link: String): String {
        val md5Key = encryptPackageName(packageName)
        return cipherDecode(md5Key, base64Decoder(base64Link))
    }
}