package be.ucll.forecast.raspberry.util;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by tompl on 12/20/2016.
 */

public class SimpleKeyGenerator implements KeyGenerator {

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}