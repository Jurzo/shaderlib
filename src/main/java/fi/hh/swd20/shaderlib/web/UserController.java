package fi.hh.swd20.shaderlib.web;

import fi.hh.swd20.shaderlib.web.result.LoginResult;
import fi.hh.swd20.shaderlib.web.result.LogoutResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public ResponseEntity<LoginResult> login() {

        HttpStatus status = HttpStatus.OK;
        LoginResult loginResult = new LoginResult();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        UserDetails user = userDetailsService.loadUserByUsername(name);
        loginResult.setAuthorities(user.getAuthorities());
        return new ResponseEntity<>(loginResult, status);
    }

    @PostMapping("/user/signout")
    public ResponseEntity<LogoutResult> logout() {

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
}