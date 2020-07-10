package com.uaa.auth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author wyf
 * 生成密钥
 * keytool -genkey -alias jwt -keyalg RSA -keysize 1024 -keystore jwt.jks -validity 365
 * 密码 123456 其他默认空
 *
 * public 131533617015991163347585557045422728735696094601992166733471690848288293500010490344034650475395136720246283798539006350450121916003988622417652207276505522724775878345652997186086987437867716395207344575363198101473876325280267067451252448528533035571731042496592583706171741601670053429065420095290399551583
 */
@SuppressWarnings("SameParameterValue")
public class TokenUtil {

    public static final String JKS_FILE_NAME = "token.jks";
    public static final String JKS_PASSWORD = "123456";
    public static final String ALIAS = "jwt";

    public static void main(String[] args) throws Exception {

        PrivateKey privateKey = getPrivateKey(JKS_FILE_NAME, JKS_PASSWORD, ALIAS);
        System.out.println(privateKey.toString());
        PublicKey publicKey = getPublicKey(JKS_FILE_NAME, JKS_PASSWORD, ALIAS);
        System.out.println(publicKey.toString());
    }

    private static PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, password.toCharArray());

        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());

    }

    private static PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, password.toCharArray());

        return keyStore.getCertificate(alias).getPublicKey();

    }
}
