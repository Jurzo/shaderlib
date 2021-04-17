package fi.hh.swd20.shaderlib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.shaderlib.web.ShaderController;
import fi.hh.swd20.shaderlib.web.ShaderRestController;
import fi.hh.swd20.shaderlib.web.UserController;
import fi.hh.swd20.shaderlib.web.UserDetailsServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShaderlibApplicationTests {

	@Autowired
	private ShaderController shaderController;
	@Autowired
	private ShaderRestController shaderRestController;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(shaderController).isNotNull();
		assertThat(shaderRestController).isNotNull();
		assertThat(userDetailsService).isNotNull();
		assertThat(userController).isNotNull();
	}

}
