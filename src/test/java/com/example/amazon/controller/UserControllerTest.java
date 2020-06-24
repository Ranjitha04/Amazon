package com.example.amazon.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.amazon.dto.Address;
import com.example.amazon.dto.Credentials;
import com.example.amazon.dto.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	MockMvc mockMvc;
	ObjectMapper objectMapper;
	
	@Mock
	RestTemplate restTemplate;
	

	@InjectMocks
	UserController userController;
	
	UserRequestDto userRequestDto;
	Address homeaddress;
	Address officeAddress;
	Credentials credentials;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		objectMapper = new ObjectMapper();
		
		homeaddress = new Address();
		homeaddress.setStreet("home street");
		homeaddress.setCity("Bengaluru");
		homeaddress.setState("Karnataka");
		homeaddress.setPincode(560072);

		officeAddress = new Address();
		homeaddress.setStreet("office street");
		homeaddress.setCity("Bengaluru");
		homeaddress.setState("Karnataka");
		homeaddress.setPincode(560052);

		userRequestDto = new UserRequestDto();
		userRequestDto.setHomeAddress(homeaddress);
		userRequestDto.setOfficeAddress(officeAddress);
		userRequestDto.setMailId("ranj@gmail.com");
		userRequestDto.setMobileNo("9667667866");
		userRequestDto.setUserName("ranj");
		userRequestDto.setPassword("1234");
		
		credentials = new Credentials();
		credentials.setUsername("harita");
		credentials.setPassword("1234");
	}
	
	@Test
	public void registerTest() throws Exception{
		URI uri = new URI("http://localhost:8080/users");
		when(restTemplate.postForObject(eq(uri),any(UserRequestDto.class),eq(String.class))).thenReturn("sucess");
             
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userRequestDto)))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", Matchers.is("sucess")));
		
		verify(restTemplate).postForObject(eq(uri),any(UserRequestDto.class),eq(String.class));
	}
	
	@Test
	public void loginTest() throws Exception{
		URI uri = new URI("http://localhost:8080/users/login");
		ResponseEntity<String> responseEntity =  new ResponseEntity<String>("login success", HttpStatus.OK);
		
		when(restTemplate.exchange(eq(uri),eq(HttpMethod.POST),any(HttpEntity.class),eq(String.class))).thenReturn(responseEntity);
             
		mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(credentials)))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", Matchers.is("login success")));
		
		verify(restTemplate).exchange(eq(uri),eq(HttpMethod.POST),any(HttpEntity.class),eq(String.class));
	}
}
