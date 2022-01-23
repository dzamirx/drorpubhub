package com.warehouseservice.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
////////////////////////////////complete JWT setup methods/////////////////////////////////////////

@Service
public class JwtUtil 
{
	/***The private system creator secret word to be encoded on every JWT payload***/
	private static String SECRET_KEY = "drorsecret";//can be  moving target defense array to skip between passwords 
	
	/***Method to take just the user name from a JWT token***/
	public static String extractUsername(String token) 
	{
		return extractClaim(token,Claims::getSubject);
	}
	
	/***Method to take extract just the expiration date from a JWT token by sending it to helper method below***/
	public static Date extractExpiration(String token)
	{
		return extractClaim(token,Claims::getExpiration);
	}
	
	/***Main method to RECIEVE a token claim to resolve the claims fields for the top methods***/
	public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private static Claims extractAllClaims (String token)
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	/***method to verify that a token is still valid according to the time of issuance***/
	private static Boolean isTokenExpired(String token) 
	{
		return extractExpiration(token).before(new Date());
		//taking the expedition from a specific token and then checks that it is below/before the current date and time
	}
	
	/***Main method to actually create a new JWT token based on specific userDetails object we provided from DB***/
	public String generateToken(UserDetails userDetails) 
	{
		Map<String, Object> claims = new HashMap<>();//creates a hashmap to include many other details that we would like to include in the payload
		return createToken(claims, userDetails.getUsername());
		//sends it to the tokenCreate method below, which knows how to build a token 
		//combining our secret phrase and a time limit that we choose
	}

	/***The Builder of a JWT token***/ 
	private String createToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		//subject is the person who authenticated, sets the date and the limit of the timing about 10 hours limit here
		//and add coded form of our secret phrase as a signing uniqueness
	}
	
	/***get the user name extracted from a received token and checks if it is matching the user name in our repository also verify expiration***/
	public static Boolean validateToken(String token, UserDetails userDetails) 
	{
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
}
