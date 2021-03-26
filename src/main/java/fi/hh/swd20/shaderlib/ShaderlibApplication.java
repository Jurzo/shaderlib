package fi.hh.swd20.shaderlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.shaderlib.model.VertexRepository;
import fi.hh.swd20.shaderlib.model.VertexSource;

@SpringBootApplication
public class ShaderlibApplication {

	private static final Logger log = LoggerFactory.getLogger(ShaderlibApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShaderlibApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(VertexRepository vertexRepository) {
        return (args) -> {

			log.info("Save some test sources");
			VertexSource vert1 = new VertexSource("testing");
			VertexSource vert2 = new VertexSource("yizz");
			VertexSource vert3 = new VertexSource("booyaa");

			vertexRepository.save(vert1);
			vertexRepository.save(vert2);
			vertexRepository.save(vert3);
        };
    }

}
