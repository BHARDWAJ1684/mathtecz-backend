package com.loginservices.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginservices.model.Customer;
import com.loginservices.model.SpringSecurityCustomer;
import com.loginservices.repository.CustomerRepository;

@Service
public class MathTeczUserDetails implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customer = customerRepository.findByEmail(username);
		if (customer.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		}
		return new SpringSecurityCustomer(customer.get(0));
	}

}