package com.loginservices.configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.loginservices.model.Authority;
import com.loginservices.model.Student;
import com.loginservices.repository.StudentRepository;

@Component
public class MathTeczUsernamePasswordAuthenticationProvider implements AuthenticationProvider{
//	@Autowired
//	private CustomerRepository customerRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		List<Student> student = studentRepository.findByEmail(username);
		if (student.size() > 0) {
			if (passwordEncoder.matches(pwd, student.get(0).getPwd())) {
				//List<GrantedAuthority> authorities = new ArrayList<>();
				//authorities.add(new SimpleGrantedAuthority(student.get(0).getRole()));
			//using Authority from Granted Authority
				return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(student.get(0).getAuthorities()));
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}
}
