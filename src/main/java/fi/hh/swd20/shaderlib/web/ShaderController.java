package fi.hh.swd20.shaderlib.web;

import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.FragmentSource;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = { "*" })
public class ShaderController {

    @Autowired
    private ShaderRepository shaderRepository;
    @Autowired
    private VertexRepository vertexRepository;
    @Autowired
    private FragmentRepository fragmentRepository;
    
    @GetMapping("/vertexshaders")
    //@PreAuthorize("hasAuthority('USER')")
    public List<VertexSource> vertexList() {
        return (List<VertexSource>) vertexRepository.findAll();
    }

    @GetMapping("/shaders")
    public List<Shader> shaderList() {
        return (List<Shader>) shaderRepository.findAll();
    }

    @PostMapping(value = "/post/vertexshader", produces = "application/json", consumes = "application/json")
    public VertexSource postVertex(@RequestBody VertexSource vertex) {
        VertexSource saved = vertexRepository.save(vertex);
        return saved;
    }

    @PostMapping(value = "/post/fragmentshader", produces = "application/json", consumes = "application/json")
    public FragmentSource postFragment(@RequestBody FragmentSource fragment) {
        FragmentSource saved = fragmentRepository.save(fragment);
        return saved;
    }

    @PostMapping(value = "/post/shader", produces = "application/json", consumes = "application/json")
    public Shader postShader(@RequestBody Shader shader) {
        Shader saved = shaderRepository.save(shader);
        return saved;
    }

}
