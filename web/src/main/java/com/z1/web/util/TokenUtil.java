package com.z1.web.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.z1.util.KeyUtil;
import com.z1.web.Const;
import com.z1.web.exception.TokenException;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * Token工具类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class TokenUtil {
	
	/**
	 * 国密4
	 */
	private static final SymmetricCrypto SM4 = SmUtil.sm4(
		KeyUtil.getBitKey("SIC", 16)
	);

	private static final String SECRET = "JWT";
	private static Algorithm algorithm = null;
	static {
		try {
			algorithm = Algorithm.HMAC256(SECRET);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	通过登录名称密码id生成token(SM4)
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public static String createToken(Map<String, String> claimMap) {
		if (null == claimMap) {
			return null;
		}
		
		Map<String, Object> header = new HashMap<String, Object>();  
		header.put("alg", "HS256");
		header.put("type", "JWT");
        
		//设置Token有效时间为7天（即：到期时间为7天后的凌晨3点）
		//若存在跨月越情况，到期时间为下月1日凌晨0点
        Calendar c = Calendar.getInstance();
        
        int curMonth = c.get(Calendar.MONTH);
        
        c.add(Calendar.DAY_OF_YEAR, 7);
        int newMonth = c.get(Calendar.MONTH);
        if (newMonth != curMonth) {
        	c.set(Calendar.DAY_OF_MONTH, 1);
        	c.set(Calendar.HOUR_OF_DAY, 0);
        }
        else {
        	c.set(Calendar.HOUR_OF_DAY, 3);
        }
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        
        Date expires = new Date(c.getTimeInMillis());
        Builder builder = JWT.create()
        	.withHeader(header)
        	.withExpiresAt(expires)//设置到期时间;
        	;
        for (String key : claimMap.keySet()) {
        	builder.withClaim(key, claimMap.get(key));
        }
        
        return SM4.encryptHex(builder.sign(algorithm), Const.UTF8_CHARSET);
	}
	
	/**
	 *	校验token(SM4)
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> verifyToken(String token) {
		
		try {
			token = SM4.decryptStr(token, Const.UTF8_CHARSET);
		}
		catch (Throwable e) {
			throw new TokenException("无效的token", e);
		}
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		}
		catch (TokenExpiredException e) {
			throw new TokenException("Token已过期!");
		}
		catch (Throwable e) {
			throw new TokenException("无效的token", e);
		}
		
		Map<String, Claim> claims = jwt.getClaims();
		
		Map<String, String> claimMap = new HashMap<String, String>();
		for (String key : claims.keySet()) {
			Claim claim = claims.get(key);
			claimMap.put(key, null == claim ? null : claim.asString());
		}
		return claimMap;
	}
	
}
