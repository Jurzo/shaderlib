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
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import fi.hh.swd20.shaderlib.model.FragmentRepository;
import fi.hh.swd20.shaderlib.model.FragmentSource;
import fi.hh.swd20.shaderlib.model.Shader;
import fi.hh.swd20.shaderlib.model.ShaderRepository;
import fi.hh.swd20.shaderlib.model.VertexRepository;
import fi.hh.swd20.shaderlib.model.VertexSource;

@SpringBootApplication
public class ShaderlibApplication {

	private static final Logger log = LoggerFactory.getLogger(ShaderlibApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShaderlibApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(VertexRepository vertexRepository, FragmentRepository fragmentRepository,
			ShaderRepository shaderRepository) {
		return (args) -> {

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
