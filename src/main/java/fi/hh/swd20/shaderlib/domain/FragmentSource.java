package fi.hh.swd20.shaderlib.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FragmentSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(length = 4000)
    @NotNull
    @Size(min = 10, max = 4000)
    private String source;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fSource")
    private List<Shader> shaders;

    public FragmentSource() {
        this.source =
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "\n" +
        "uniform vec2 u_resolution;\n" +
        "uniform vec2 u_mouse;\n" +
        "uniform float u_time;\n" +
        "\n" +
        "void main() {\n" +
        "    vec2 st = gl_FragCoord.xy/u_resolution.xy;\n" +
        "    st.x *= u_resolution.x/u_resolution.y;\n" +
        "\n" +
        "    vec3 color = vec3(0.);\n" +
        "    color = vec3(st.x,st.y,abs(sin(u_time)));\n" +
        "\n" +
        "    gl_FragColor = vec4(color,1.0);\n" +
        "}";
    }

    public FragmentSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Shader> getShaders() {
        return shaders;
    }

    public void setShaders(List<Shader> shaders) {
        this.shaders = shaders;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return this.id + "\n" + this.source;
    }
    
}
