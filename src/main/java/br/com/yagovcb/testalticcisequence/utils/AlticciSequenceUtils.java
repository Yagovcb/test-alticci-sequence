package br.com.yagovcb.testalticcisequence.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlticciSequenceUtils {


    public static boolean isNumberLowerThanZero(Long number) {
        return number < 0;
    }

    public static boolean isNumberEqualThanZero(Long number) {
        return number == 0;
    }

    public static boolean isNumberLowerThanTwo(Long number) {
        return number <= 2;
    }
}
