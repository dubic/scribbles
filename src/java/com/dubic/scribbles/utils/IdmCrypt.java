/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Cryptographic utilities class
 *
 * @author dubem
 */
public class IdmCrypt {

    private static final String constant = "dubine";

    public static String encrypt(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String encodeMD5(String data, String salt) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("data to encode cannot be null");
        }
        return DigestUtils.md5Hex(salt + constant + data);
    }

    public static void main(String[] atyty) {
        System.out.println("enc - " + encodeMD5("dubic", null));
    }
}
