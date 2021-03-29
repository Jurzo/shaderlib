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

    @Column(length = 2000)
    @NotNull
    @Size(min = 10, max = 2000)
    private String source;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fSource")
    private List<Shader> shaders;

    public FragmentSource() {
        this.source = "";
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
