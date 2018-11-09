package tt.service;


import javax.annotation.Resource;

import org.junit.Test;

import tt.entity.User;

public class UserServiceTest {
@Resource(name="userService")
private UserService userService;
	@Test
	public void test() throws Exception {
		User user = new User();
		user.setName("test");
		user.setPassword("12");
		userService.save(user);
	}

}
