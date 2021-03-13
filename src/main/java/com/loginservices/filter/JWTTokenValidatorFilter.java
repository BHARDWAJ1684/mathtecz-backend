package com.loginservices.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.loginservices.constant.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTTokenValidatorFilter extends OncePerRequestFilter{
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String jwt = request.getHeader(Constants.JWT_HEADER);
		//jwt = jwt.split(" ")[1];
		if (null != jwt) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(
						Constants.JWT_KEY.getBytes(StandardCharsets.UTF_8));	
				
				Claims claims = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt)
						.getBody();
				String username = String.valueOf(claims.get("username"));
				String authorities = (String) claims.get("authorities");
				Authentication auth = new UsernamePasswordAuthenticationToken(username,null,
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}catch (Exception e) {
				throw new BadCredentialsException("Invalid Token received!");
			}
			
		}
		chain.doFilter(request, response);
	}

	
	  @Override protected boolean shouldNotFilter(HttpServletRequest request) {
	  return (request.getServletPath().equals("/studentlogin") && 
			  		request.getServletPath().equals("/createstudent") );
	  }
	 
	

}
