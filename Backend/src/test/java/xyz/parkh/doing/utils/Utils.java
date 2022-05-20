package xyz.parkh.doing.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.mapper.UserMapper;

public class Utils {

    // 랜덤 UserVo 생성
    public static UserVo generateUser() {
        String userId = generatedStringWithInt(10);
        String name = generatedOnlyString(5);
        String company = generatedOnlyString(10);
        String email = generatedOnlyString(5) + "@" + generatedOnlyString(5) + "." + generatedOnlyString(3);

        UserVo user = new UserVo().builder()
                .userId(userId).name(name).company(company).email(email).build();

        return user;
    }

    // 랜덤 String 생성 ( 문자열 )
    public static String generatedOnlyString(int length) {
        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    // 랜덤 String 생성 ( 문자열, 숫자 )
    public static String generatedStringWithInt(int length) {
        boolean useLetters = true;
        boolean useNumbers = true;

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }
}
