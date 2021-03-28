package fi.hh.swd20.shaderlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.FragmentSource;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.User;
import fi.hh.swd20.shaderlib.domain.UserRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ShaderlibApplication {

	private static final Logger log = LoggerFactory.getLogger(ShaderlibApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShaderlibApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(VertexRepository vertexRepository, FragmentRepository fragmentRepository,
			ShaderRepository shaderRepository, UserRepository users) {
		return (args) -> {

			// admin:admin, user:user
			log.info("Create some users to database");
			User user = new User("user", "user@domain", "$2b$10$f6ug1gn42FXc.S4MYNSxBO2o2HjMw4YD51408DxxQ2bferldEIxy6", "USER");
			User admin = new User("admin", "admin@domain", "$2b$10$9so7Ic6Z5Nn1yPrMPeU5humBW6PMxJG573EgG9zHi7vi.KQbDPjAO", "ADMIN");
			users.save(user);
			users.save(admin);

			VertexSource vert1 = new VertexSource(
					"void main(){ \n" + 
					"gl_Position = vec4( position, 1.0 );\n" +
					"}");

			String output = readFile("/shaders/redwaves.fs");
			FragmentSource frag1 = new FragmentSource(output);

			output = readFile("/shaders/psychedelic.fs");
			FragmentSource frag2 = new FragmentSource(output);

			Shader shader = new Shader("red waves", vert1, frag1);
			Shader shader2 = new Shader("psychedelic", vert1, frag2);

			vertexRepository.save(vert1);
			fragmentRepository.save(frag1);
			fragmentRepository.save(frag2);
			shaderRepository.save(shader);
			shaderRepository.save(shader2);
		};
	}

	public String readFile(String filename) throws IOException {
		String output = "";
		Resource resource = new ClassPathResource(filename);
		InputStream inputStream = resource.getInputStream();
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			String data = new String(bdata, StandardCharsets.UTF_8);
			output = data;
		} catch (IOException e) {
			log.error("IOException", e);
		} finally {
			inputStream.close();
		}
		return output;
	}

}
