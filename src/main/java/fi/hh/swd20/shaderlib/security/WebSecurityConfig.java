package fi.hh.swd20.shaderlib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fi.hh.swd20.shaderlib.web.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers("/register", "/registration").permitAll()
        .and()
        .formLogin()
            .loginPage("/signin")
            .permitAll()
            .defaultSuccessUrl("/dashboard", true)
        .and()
            .logout()
            .logoutSuccessUrl("/signin")
            .permitAll()
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/vertexshaders", "/shaders", "/api/**", "/login").permitAll()
        .antMatchers(HttpMethod.GET, "/newshader").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/post/**").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .anyRequest().authenticated();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
