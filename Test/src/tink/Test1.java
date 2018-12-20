package tink;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.config.TinkConfig;
import com.google.crypto.tink.hybrid.HybridDecryptFactory;
import com.google.crypto.tink.hybrid.HybridEncryptFactory;
import com.google.crypto.tink.hybrid.HybridKeyTemplates;

import proto.Test;

public class Test1 {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		// 初始化
		TinkConfig.register();
//
//		//AEAD 初始化
		AeadConfig.register();
//		//自定义初始化
////		Registry.registerKeyManager(new MyAeadKeyManager());
//
//		//如果需要用Java代码直接生成具有新密钥的KeysetHandle，则可以使用KEYSETHANDELE。例如，您可以生成包含随机生成的AES128 GCM密钥的密钥集
//		KeyTemplate keyTemplate = AeadKeyTemplates.AES128_GCM;
//	    KeysetHandle keysetHandle = KeysetHandle.generateNew(keyTemplate);
//		
//	    //生成密钥材料后，您可能希望将其持久化到存储系统，例如，写入文件：
//	    String keysetFilename = "my_keyset.txt";
//	    CleartextKeysetHandle.write(keysetHandle, JsonKeysetWriter.withFile(
//	        new File(keysetFilename)));
		// 1. Generate the private key material.
	    KeysetHandle privateKeysetHandle = KeysetHandle.generateNew(
	        HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM);

	    // Obtain the public key material.
	    KeysetHandle publicKeysetHandle =
	        privateKeysetHandle.getPublicKeysetHandle();

	    // ENCRYPTING

	    // 2. Get the primitive.
	    HybridEncrypt hybridEncrypt = HybridEncryptFactory.getPrimitive(
	        publicKeysetHandle);

	    
	    byte[] plaintext ={1,2,3,4,5};
	    byte[] data =Test.getByte();
	    // 3. Use the primitive.
	    byte[] ciphertext = hybridEncrypt.encrypt(plaintext, data);

	    // DECRYPTING

	    // 2. Get the primitive.
	    HybridDecrypt hybridDecrypt = HybridDecryptFactory.getPrimitive(
	        privateKeysetHandle);

	    // 3. Use the primitive.
	    byte[] plaintext2 = hybridDecrypt.decrypt(ciphertext, plaintext);
	    
	    Test.printByte(plaintext2);
	    
	}
	
}

