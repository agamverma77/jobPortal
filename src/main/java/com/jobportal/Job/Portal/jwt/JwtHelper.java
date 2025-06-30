package com.jobportal.Job.Portal.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
	private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

	// Token expiration time in milliseconds (e.g., 1 hour = 3600000 ms)//1 ghante tk chalega, ek ghante baad vo token expire ho jaega & we cannot call any more API's and we need to login again
	private static final long JWT_TOKEN_VALIDITY = 36000000;

	// Retrieve username (or subject) from the JWT token  //for a jwt token the username should be unique otherwise many can access that token to login, usually it is email
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrieve expiration date from the JWT token   // to check if token has expired?
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// Retrieve any claim from the JWT token
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {  //what claims (info) we are giving in jwt token, like username, password etc
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Get all claims from the token using the secret key
	private Key getSigningKey() {
	    return Keys.hmacShaKeyFor(secret.getBytes());
	}

	private Claims getAllClaimsFromToken(String token) { //we get the body of the token usinf th secret key
	    return Jwts.parserBuilder()
	               .setSigningKey(getSigningKey())  // ✅ secure replacement
	               .build()
	               .parseClaimsJws(token)
	               .getBody();
	}

	// Check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Generate a token for the user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>(); //to make claims, first we give the string then the object(what we wantt to give)
		CustomUserDetails customUser=(CustomUserDetails)userDetails;
		claims.put("id", customUser.getId());
		claims.put("name", customUser.getName());
		claims.put("accountType", customUser.getAccountType());
		claims.put("profileId", customUser.getProfileId());
		return doGenerateToken(claims, userDetails.getUsername()); //username should be unique, it wont check, we by own should give it unique
	}

	// Create the token by signing it with the secret key
	private Key getnewSigningKey() {
	    return Keys.hmacShaKeyFor(secret.getBytes());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
	    return Jwts.builder() // sets everything and signs with secret key and generates token
	            .setClaims(claims)
	            .setSubject(subject)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
	            .signWith(getnewSigningKey(), SignatureAlgorithm.HS512)  // ✅ new way
	            .compact();
	}

	// Validate the token
	public Boolean validateToken(String token, String username) { //I have logged in for a user and from front end I want these API's
		final String tokenUsername = getUsernameFromToken(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}
}

