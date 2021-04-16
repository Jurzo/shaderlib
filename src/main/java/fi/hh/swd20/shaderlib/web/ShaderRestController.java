package fi.hh.swd20.shaderlib.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.FragmentSource;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;



@RestController
public class ShaderRestController {

    @Autowired
    private ShaderRepository shaderRepository;
    @Autowired
    private VertexRepository vertexRepository;
    @Autowired
    private FragmentRepository fragmentRepository;
    
    @GetMapping("/vertexshaders")
    public List<VertexSource> vertexList() {
        return (List<VertexSource>) vertexRepository.findAll();
    }

    @GetMapping("/shaders")
    public List<Shader> shaderList() {
        return (List<Shader>) shaderRepository.findAll();
    }

    @GetMapping("/newshader")
    public Shader newFragment() {
        FragmentSource fragment = new FragmentSource();
        VertexSource vert = new VertexSource("void main(){ \n" + "gl_Position = vec4( position, 1.0 );\n" + "}");
        Shader shader = new Shader("New", vert, fragment);
        return shader;
    }

    @PostMapping(value = "/post/vertexshader", produces = "application/json", consumes = "application/json")
    public ResponseEntity<VertexSource> postVertex(@Valid @RequestBody VertexSource vertex, BindingResult bindingResult) {
        HttpStatus status = HttpStatus.OK;
        VertexSource resp = new VertexSource();
        if (bindingResult.hasErrors()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            resp = vertexRepository.save(vertex);
        }
        return new ResponseEntity<>(resp, status);
    }

    @PostMapping(value = "/post/fragmentshader", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FragmentSource> postFragment(@Valid @RequestBody FragmentSource fragment, BindingResult bindingResult) {
        HttpStatus status = HttpStatus.OK;
        FragmentSource resp = new FragmentSource();
        if (bindingResult.hasErrors()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            resp = fragmentRepository.save(fragment);
        }
        return new ResponseEntity<>(resp, status);
    }

    @PostMapping(value = "/post/shader", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Shader> postShader(@Valid @RequestBody Shader shader, BindingResult bindingResult) {
        HttpStatus status = HttpStatus.OK;
        System.out.println(shader);
        Shader resp = new Shader();
        if (bindingResult.hasErrors()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            resp = shaderRepository.save(shader);
        }
        return new ResponseEntity<>(resp, status);
    }

    @DeleteMapping(value = "/post/shader", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteShader(@Valid @RequestBody Shader shader, BindingResult bindingResult) {
        HttpStatus status = HttpStatus.OK;
        String resp = "Deletion successful";
        if (bindingResult.hasErrors()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            resp = "Invalid shader";
        } else {
            shaderRepository.deleteById(shader.getId());
            vertexRepository.deleteById(shader.getVSource().getId());
            fragmentRepository.deleteById(shader.getFSource().getId());
        }
        
        return new ResponseEntity<>(resp, status);
    }

}
