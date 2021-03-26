package fi.hh.swd20.shaderlib.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd20.shaderlib.model.FragmentRepository;
import fi.hh.swd20.shaderlib.model.ShaderRepository;
import fi.hh.swd20.shaderlib.model.VertexRepository;
import fi.hh.swd20.shaderlib.model.VertexSource;

@RestController
public class ShaderController {

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


}
