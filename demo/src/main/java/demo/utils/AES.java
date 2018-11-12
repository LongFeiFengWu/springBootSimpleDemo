package demo.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AES {

	public static String encrypt(String src, String psw) throws Exception {

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(psw.getBytes()));
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] keyBytes = secretKey.getEncoded();

		// Key转换
		Key key = new SecretKeySpec(keyBytes, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encodeResult = cipher.doFinal(src.getBytes("utf-8"));
		return Common.parseByte2HexStr(encodeResult);

	}

	public static String decryp(String src, String psw) throws Exception {

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(psw.getBytes()));

		SecretKey secretKey = keyGenerator.generateKey();
		byte[] keyBytes = secretKey.getEncoded();

		// Key转换
		Key key = new SecretKeySpec(keyBytes, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodeResult = cipher.doFinal(Common.parseHexStr2Byte(src));
		return new String(decodeResult);

	}

}
