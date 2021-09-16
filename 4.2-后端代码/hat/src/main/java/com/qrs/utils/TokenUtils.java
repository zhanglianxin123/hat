package com.qrs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qrs.pojo.User;


/**
 * @author zyx 2134208960@qq.com
 * @version 0.1.0
 * @create 2021-03-18 08:00
 * @since 0.1.0
 **/
public class TokenUtils {
    private static final long EXPIRE_TIME= 2*60*60*1000;


    /**
     * 生成token
     * @param user
     * @return
     */
    public static String getUserToken(User user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getUser_id()))
                .sign(Algorithm.HMAC256(user.getUser_password()));
        return token;
    }
}
