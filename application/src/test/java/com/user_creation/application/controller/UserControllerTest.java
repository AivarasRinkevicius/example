package com.user_creation.application.controller;

import com.user_creation.application.interfaces.Validator;
import com.user_creation.application.model.User;
import com.user_creation.application.service.UserService;
import com.user_creation.application.service.ValidatorFactory;
import org.hamcrest.Matchers;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@WebMvcTest(value = UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@Mock
	private Validator validator;

	@MockBean
	ValidatorFactory validatorFactory;


	@Test
	void testShowAll() throws Exception {
		List<User> userList = new ArrayList<>();
		User user = new User("Jonas", "Jonaitis", "+37063914578", "jonaitis@gmail.com", "Vilnius, traku gatve 34", "Jomnas124?");
		userList.add(user);
		when(userService.findAll()).thenReturn(userList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/list-users")
				.accept(MediaType.TEXT_HTML);

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("list-users"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-users.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("users"))
				.andReturn();
	}

	@Test
	public void testShowAddPage() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/add-user");

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/user.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("user"))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("vardas", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("pavarde", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("telefonoNumeris", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("email", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("adresas", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("slaptazodis", emptyOrNullString())))
				.andReturn();
	}


	@Test
	void testAdd() throws Exception {

		User user = new User("Jonas", "Jonaitis", "+37063914578", "jonaitis@gmail.com", "Vilnius, traku gatve 34", "Jomnas124?");

		when(validatorFactory.getValidator(Mockito.anyString())).thenReturn(validator);
		when(userService.add(Mockito.any(User.class))).thenReturn(user);


		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/add-user")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas", "Jonas")
				.param("pavarde", "Jonaitis")
				.param("telefonoNumeris", "37063914578")
				.param("email", "jonaitis@gmail.com")
				.param("adresas", "Vilnius, traku gatve 34")
				.param("slaptazodis", "Jomnas124?")
				.flashAttr("user", new User());

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/list-users"));

		verify(userService).add(Mockito.any(User.class));
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete-user/1");

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/list-users"))
				.andReturn();

		verify(userService).deleteById(Mockito.anyLong());
	}

	@Test
	public void testShowUpdatePage() throws Exception {
		User user = new User("Jonas", "Jonaitis", "+37063914578", "jonaitis@gmail.com", "Vilnius, traku gatve 34", "Jomnas124?");

		when(userService.findById(Mockito.anyLong())).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/update-user/1");

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/user.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("user"))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("vardas", Matchers.equalTo("Jonas"))))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("pavarde", Matchers.equalTo("Jonaitis"))))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("telefonoNumeris", Matchers.equalTo("+37063914578"))))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("email", Matchers.equalTo("jonaitis@gmail.com"))))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("adresas", Matchers.equalTo("Vilnius, traku gatve 34"))))
				.andExpect(MockMvcResultMatchers.model().attribute("user",  hasProperty("slaptazodis", Matchers.equalTo("Jomnas124?"))))
				.andReturn();
	}

	@Test
	void testUpdate() throws Exception {

		User user = new User("Jonas", "Jonaitis", "+37063914578", "jonaitis@gmail.com", "Vilnius, traku gatve 34", "Jomnas124?");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/update-user/1")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas", "slaptazodis")
				.param("pavarde", "Jonaitis")
				.param("telefonoNumeris", "+37063914578")
				.param("email", "jonaitis@gmail.com")
				.param("adresas", "Vilnius, traku gatve 34")
				.param("slaptazodis", "Jomnas124?")
				.flashAttr("user", user);

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/list-users"))
				.andReturn();

		verify(userService).update(Mockito.any(User.class));
	}
}
