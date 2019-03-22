package Tables;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
public class FunctionUtil {

    /**
     * different dictionaries used
     */
    private static SecureRandom random = new SecureRandom();
    private static final int length = 6;
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
    private static final String dic = ALPHA_CAPS + NUMERIC + ALPHA;

    /**
     *
     * @param date date to caste as string
     * @return sqlDate format
     * @throws ParseException
     */
    public static java.sql.Date castDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    /**
     *
     * Method will generate random string based on the parameters
     *
     * @return the random password
     */
    public static String generatePassword() {
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;

    }

}
