package org.opencage.lindwurm.setec;

import org.opencage.kleinod.crypt.KeySize;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import static org.opencage.kleinod.errors.Unchecked.statement;
import static org.opencage.kleinod.errors.Unchecked.val;
import static org.opencage.lindwurm.setec.SetecConstants.*;

/**
 * Created by stephan on 29/07/14.
 */
public class DeEnc {
    private final byte[] salt;
    private final SecretKeySpec keyspec;


    public DeEnc( char[] passwd )  {
        this( passwd, new SecureRandom().generateSeed(8));
    }

    public DeEnc(char[] passwd, byte[] salt) {
        this.salt = salt;
        keyspec = val(() -> getSecretKeySpec(passwd, salt));
    }

    static SecretKeySpec getSecretKeySpec( char[] passwd, byte[] salt ) {

        SecretKeyFactory factory = val( () -> SecretKeyFactory.getInstance( KEYFACTORY_KIND ));
        KeySpec spec             = val( () -> new PBEKeySpec(passwd, salt, ITERATION_COUNT, KeySize.get(BLOCK_CIPHER)));
        SecretKey tmp            = val( () -> factory.generateSecret(spec));


        return new SecretKeySpec(tmp.getEncoded(), CIPHER );
    }


    public Cipher getEncryptingCipher() {
        Cipher ecipher = val(() -> Cipher.getInstance(BLOCK_CIPHER));
        statement( () -> ecipher.init(Cipher.ENCRYPT_MODE, keyspec));
        return ecipher;
    }

    public Cipher getDecryptingCipher( byte[] iv )  {
        Cipher decipher = val(() -> Cipher.getInstance(BLOCK_CIPHER));
        statement( () -> decipher.init(Cipher.DECRYPT_MODE, keyspec, new IvParameterSpec( iv )));
        return decipher;
    }

    public byte[] getSalt() {
        return salt;
    }
}
