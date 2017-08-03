package com.rccf.util.encrypt;


public class PasswordUtil {


    /**
     * 前台传递的密码进行处理
     * @param pwd
     * @return
     */
    public static String dealPassword(String pwd){
        String password = null;
        try {
            password = new DesEncrypt().decrypt(pwd);
            if (password.length() < 6 || password.length() > 18) {
                return "1";
            }

            password = ShaEncript.encryptSHA(pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
        return  password;
    }
}
