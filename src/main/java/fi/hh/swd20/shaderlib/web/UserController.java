package fi.hh.swd20.shaderlib.web;

import fi.hh.swd20.shaderlib.domain.User;
import fi.hh.swd20.shaderlib.domain.UserDto;
import fi.hh.swd20.shaderlib.domain.UserRepository;
import fi.hh.swd20.shaderlib.web.result.LoginResult;
import fi.hh.swd20.shaderlib.web.result.LogoutResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public @ResponseBody ResponseEntity<LoginResult> login() {

        HttpStatus status = HttpStatus.OK;
        LoginResult loginResult = new LoginResult();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        UserDetails user = userDetailsService.loadUserByUsername(name);
        loginResult.setAuthorities(user.getAuthorities());
        return new ResponseEntity<>(loginResult, status);
    }

    @PostMapping("/user/signout")
    public @ResponseBody ResponseEntity<LogoutResult> logout() {

        HttpStatus status = HttpStatus.OK;
        LogoutResult logoutResult = new LogoutResult();

        return new ResponseEntity<>(logoutResult, status);
    }

    @GetMapping("/signin")
    public String signin() {
        return "signinform";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:signin";
    }

    @GetMapping("/register")
    public String registerForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("refer", request.getHeader("Referer").split("\\?")[0]);
        return "registerform";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserDto userDto,
                                @RequestParam(name="refer", required = false, defaultValue = "signin") String refer,
                                Model model) {
        User user = new User();
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            model.addAttribute("user", userDto);
            model.addAttribute("refer", refer);
            model.addAttribute("err", "Username taken");
            return "registerform";
        } else {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setRole("USER");
            user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
            return "redirect:" + refer;
        }
    }
}