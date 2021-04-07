package fi.hh.swd20.shaderlib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fi.hh.swd20.shaderlib.domain.FragmentRepository;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;
import fi.hh.swd20.shaderlib.domain.VertexRepository;

@Controller
public class ShaderController {

    @Autowired
    private ShaderRepository shaderRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("shaders", shaderRepository.findAll());
        return "dashboard";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
        shaderRepository.deleteById(id);
        return "redirect:../dashboard";
    }
}
