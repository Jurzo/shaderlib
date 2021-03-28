package fi.hh.swd20.shaderlib.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;
import fi.hh.swd20.shaderlib.domain.VertexSource;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
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

}
