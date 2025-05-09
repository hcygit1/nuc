package com.personal.weappadmin.utils;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JwtUtils {

    static final int expire =60000000;
    private static final String JWT_KEY="lskdfklsklsdfjklcilud";
    private static final String WEAPP_JWT_KEY="lskdfklsklsdfjklciludewer";

    public static String weappgenerateToken(WeappUser user) {
        String token = Jwts.builder()
                .claim("LoginId", user.getId())
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256, WEAPP_JWT_KEY)
                .compact();
        return token;
    }


    public static WeappUser weappgetUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(WEAPP_JWT_KEY).parseClaimsJws(token).getBody();
        WeappUser user = new WeappUser();
        user.setId(Long.parseLong(claims.get("LoginId").toString()));
        return user;
    }



    public static String pmUsergenerateToken(User user) {
        String token = Jwts.builder()
                .claim("LoginId", user.getId())
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256, JWT_KEY)
                .compact();
        return token;
    }


    public static User pmUsergetUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).getBody();
        User user = new User();
        user.setId(Long.parseLong(claims.get("LoginId").toString()));
        return user;
    }
}
