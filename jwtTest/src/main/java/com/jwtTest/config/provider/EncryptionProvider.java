package com.jwtTest.config.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${secretKey.encrypionProvider.AESSecretKey}")
	private String aesSecretKey;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	EncryptionProvider(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public String pwEncryption(String rawPw) throws Exception {
		return passwordEncoder.encode(rawPw);
	}

	public String aesEncryption(String rawPw) {
		String salt = KeyGenerators.string().generateKey();

		TextEncryptor tEnc = Encryptors.text(aesSecretKey, salt);

		// 암호화
		String encText = tEnc.encrypt("text");

		// 복호화
		String dencText = tEnc.decrypt(encText);

		return null;
	}

}
