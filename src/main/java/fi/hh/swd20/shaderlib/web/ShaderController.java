package fi.hh.swd20.shaderlib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fi.hh.swd20.shaderlib.domain.Shader;
import fi.hh.swd20.shaderlib.domain.ShaderRepository;

@Controller
public class ShaderController {

    @Autowired
    private ShaderRepository shaderRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("admin")) {
            model.addAttribute("shaders", shaderRepository.findAll());
        } else {
            String currentPrincipalName = authentication.getName();
            model.addAttribute("shaders", shaderRepository.findByAuthor(currentPrincipalName));
        }
        return "dashboard";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String deleteBook(@PathVariable("id") Long id) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Shader shader = shaderRepository.findById(id).get();
        if (user.equals("admin") || user.equals(shader.getAuthor())) {
            shaderRepository.deleteById(id);
        }
        return "redirect:../dashboard";
    }
}
