package fi.hh.swd20.shaderlib.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShaderRepository extends CrudRepository<Shader, Long> {
    List<Shader> findByAuthor(String author);
}
