package com.example.demo.Util;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtils {
	/**
	 * 生成JWT Token
	 *
	 * @param userId 签发对象
	 * @param realName payload
	 * @param userName payload
	 * @return
	 */
   public static String createToken(String userId,String realName, String userName) {

       Calendar nowTime = Calendar.getInstance();
       nowTime.add(Calendar.MINUTE,30);
       Date expiresDate = nowTime.getTime();

       return JWT.create().withAudience(userId)   	//签发对象
               .withIssuedAt(new Date())           		//发行时间
               .withExpiresAt(expiresDate)         		//有效时间
               .withClaim("userName", userName)    		// payload
               .withClaim("realName", realName)    		// payload
               .sign(Algorithm.HMAC256(userId + "solt"));   //加密
   }

   /**
    * 检验合法性，其中secret参数就应该传入的是用户的id
    * @param token
    * @throws TokenUnavailable
    */
   public static void verifyToken(String token, String secret) throws Exception {
       DecodedJWT jwt = null;
       try {
           JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret+"solt")).build();
           jwt = verifier.verify(token);
       } catch (Exception e) {
           // 效验失败
           throw new Exception();
       }
   }

   /**
    * 获取签发对象
    * @param token
    * @return
    * @throws Exception
    */
   public static String getAudience(String token) throws Exception {
       String audience = null;
       try {
           audience = JWT.decode(token).getAudience().get(0);
       } catch (JWTDecodeException j) {
           // token解析失败
           throw new Exception();
       }
       return audience;
   }

   /**
    * 通过载荷名字获取载荷的值
    * @param token
    * @param name
    * @return
    */
   public static Claim getClaimByName(String token, String name){
       return JWT.decode(token).getClaim(name);
   }
}
