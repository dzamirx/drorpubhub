package com.warehouseservice.Service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.warehouseservice.Service.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
	@Autowired
	private MyUserDetailsService userDetailsService;//link access to the user details service
	
	@Autowired
	private JwtUtil jwtUtil;//link access a jwt generator once 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//will examine the header looks for the jwt if found checks if its valid
		//parsing each part of the header
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;//							  Authorization
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
		{
			jwt = authorizationHeader.substring(7);
			username = JwtUtil.extractUsername(jwt);
		}
		if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null) 
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (JwtUtil.validateToken(jwt, userDetails))  
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	
}
