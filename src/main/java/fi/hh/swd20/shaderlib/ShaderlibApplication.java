package fi.hh.swd20.shaderlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.FragmentSource;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.User;
import fi.hh.swd20.shaderlib.domain.UserRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
			log.info("Create admin user");
			User admin = new User(System.getenv("adminUser"), "admin@domain",
								System.getenv("adminPass"), "ADMIN");
			users.save(admin);

			Map<String, String> data = readFiles();
			for (String key : data.keySet()) {
				VertexSource vert = new VertexSource("void main(){ \n" + "gl_Position = vec4( position, 1.0 );\n" + "}");
				vertexRepository.save(vert);
				FragmentSource frag = new FragmentSource(data.get(key));
				fragmentRepository.save(frag);
				Shader shader = new Shader(key, vert, frag);
				shaderRepository.save(shader);
			}
		};
	}

	public Map<String, String> readFiles() throws IOException, URISyntaxException {
		Map<String, String> shaders = new HashMap<>();
		String shaderFiles[] = {"checkers.fs",
								"circle.fs",
								"colorfulwaves.fs",
								"cubes.fs",
								"fractal cube.fs",
								"psychedelic.fs",
								"redwaves.fs"};
		for (String filename : shaderFiles) {
			String content = "";
			try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("shaderfolder/" + filename);
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			}
			
			shaders.put("checkers", content);
		}
		
		return shaders;
	}

}
