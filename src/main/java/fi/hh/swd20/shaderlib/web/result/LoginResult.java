package fi.hh.swd20.shaderlib.web.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginResult {

    @JsonProperty("authorities")
    private List<String> authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities.stream().map(x -> x.getAuthority()).collect(Collectors.toList());
    }
}
