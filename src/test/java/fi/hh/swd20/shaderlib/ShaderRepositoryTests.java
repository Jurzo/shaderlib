package fi.hh.swd20.shaderlib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.FragmentSource;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ShaderRepositoryTests {
    @Autowired
    private ShaderRepository shaderRepository;
    @Autowired
    private VertexRepository vertexRepository;
    @Autowired
    private FragmentRepository fragmentRepository;

    @Test
    public void presetShadersFound() {
        assertThat(shaderRepository.findAll()).isNotEmpty();
    }

    @Test
    public void newShader() {
        VertexSource vsource = new VertexSource();
        FragmentSource fsource = new FragmentSource();
        vertexRepository.save(vsource);
        fragmentRepository.save(fsource);
        assertThat(vsource.getId()).isGreaterThan(0l);
        assertThat(fsource.getId()).isGreaterThan(0l);
        Shader shader = new Shader("test", vsource, fsource);
        shaderRepository.save(shader);
        assertThat(shader.getId()).isGreaterThan(0l);
    }

    @Test
    public void deleteShader() {
        List<Shader> shaders = (List<Shader>) shaderRepository.findAll();
        Shader s = shaders.get(0);
        shaderRepository.delete(s);
        assertThat(shaderRepository.findById(s.getId()).isPresent()).isFalse();
    }
    
}
