package fi.hh.swd20.shaderlib.web.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LogoutResult {

    @JsonProperty("authorities")
    private List<String> authorities;

}
