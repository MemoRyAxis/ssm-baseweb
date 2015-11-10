package com.memory.base.util;

import java.util.regex.Pattern;

/**
 * validate util
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 19:05
 */
public class ValidateUtil {

    public static final String MAIL = "^(?:\\w[-.\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)$";
    public static final String IP = "^\\d+\\.\\d+\\.\\d+\\.\\d+$";
    public static final String COMMON = "^\\w+$";
    public static final String ID_CARD_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
    public static final String BANK_CARD = "^[0-9]{16,19}$";
    public static final String MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";
    public static final String PHONE = "^[0][1-9][0-9]{1,2}-[0-9]{5,10}$";
    public static final String PHONE_WITHOUT_ZONE = "^[1-9]{1}[0-9]{5,8}$";
    public static final String NUMBER = "^[0-9]\\d*$";

    private static final Pattern MAIL_PATTERN = Pattern.compile(MAIL);
    private static final Pattern IP_PATTERN = Pattern.compile(IP);
    private static final Pattern COMMON_PATTERN = Pattern.compile(COMMON);
    private static final Pattern ID_CARD_15_PATTERN = Pattern.compile(ID_CARD_15);
    private static final Pattern ID_CARD_18_PATTERN = Pattern.compile(ID_CARD_18);
    private static final Pattern BANK_CARD_PATTERN = Pattern.compile(BANK_CARD);
    private static final Pattern MOBILE_PATTERN = Pattern.compile(MOBILE);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE);
    private static final Pattern PHONE_WITHOUT_ZONE_PATTERN = Pattern.compile(PHONE_WITHOUT_ZONE);
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER);

    /**
     * validate number
     */
    public static boolean isNumber(String number) {
        if (isEmptyString(number)) {
            return false;
        }
        return NUMBER_PATTERN.matcher(number).matches();
    }

    /**
     * validate mail
     */
    public static boolean isMail(String mail) {
        if (isEmptyString(mail)) {
            return false;
        }
        return MAIL_PATTERN.matcher(mail).matches();
    }

    /**
     * validate ip
     */
    public static boolean isIp(String ip) {
        if (isEmptyString(ip)) {
            return false;
        }
        return IP_PATTERN.matcher(ip).matches();
    }

    /**
     * common string with a~Z, 0~9, _
     */
    public static boolean isCommon(String common) {
        if (isEmptyString(common)) {
            return false;
        }
        return COMMON_PATTERN.matcher(common).matches();
    }

    /**
     * validate id card
     */
    public static boolean isIdCard(String idCard) {
        if (isEmptyString(idCard)) {
            return false;
        }
        int len = idCard.length();
        if (len == 15) {
            return ID_CARD_15_PATTERN.matcher(idCard).matches();
        } else if (len == 18) {
            return ID_CARD_18_PATTERN.matcher(idCard).matches();
        } else {
            return false;
        }
    }

    /**
     * 通过脱敏字符串对输入身份证进行校验
     *
     * @param idCard     输入身份证
     * @param idCardMask 身份证的脱敏字符串
     * @param first      前几位匹配
     * @param last       后几位匹配
     */
    public static boolean isIdCard(String idCard, String idCardMask, int first, int last) {
        if (!isIdCard(idCard)) {
            return false;
        }
        int maxLen = first > last ? first : last;
        if (ValidateUtil.isNotEmptyString(idCardMask) && ValidateUtil.isNotEmptyString(idCard) && idCardMask.length() > maxLen) {
            String beforeMask = idCardMask.substring(0, first);
            String afterMask = idCardMask.substring(idCardMask.length() - last, idCardMask.length());
            String before = idCard.substring(0, first);
            String after = idCard.substring(idCard.length() - last, idCard.length());
            if (before.equals(beforeMask) && after.contains(afterMask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if string given is not empty, otherwise false
     */
    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    /**
     * return true if string given is empty, otherwise false
     */
    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str);
    }

    /**
     * verify whether the string given is a bank card number
     */
    public static boolean isBankCard(String bankCard) {
        if (!BANK_CARD_PATTERN.matcher(bankCard).matches()) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * check card code by Luhn algorithm
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * verify whether the string given is a mobile number
     */
    public static boolean isMobile(String mobile) {
        if (isEmptyString(mobile)) return false;
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    /**
     * verify whether the string given is a phone number
     */
    public static boolean isPhone(String phone) {
        if (isEmptyString(phone)) return false;
        boolean isMatch = false;
        if (phone.length() > 9) {
            isMatch = PHONE_PATTERN.matcher(phone).matches();
        } else {
            isMatch = PHONE_WITHOUT_ZONE_PATTERN.matcher(phone).matches();
        }
        return isMatch;
    }

}
