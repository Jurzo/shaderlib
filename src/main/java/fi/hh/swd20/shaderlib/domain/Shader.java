package fi.hh.swd20.shaderlib.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Shader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "vertexId")
    private VertexSource vSource;
    @ManyToOne
    @JoinColumn(name = "shaderId")
    private FragmentSource fSource;


    public Shader() {
        super();
        this.name = null;
        this.fSource = null;
        this.vSource = null;
    }


    public Shader(String name, VertexSource vSource, FragmentSource fSource) {
        this.name = name;
        this.vSource = vSource;
        this.fSource = fSource;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VertexSource getVSource() {
        return this.vSource;
    }

    public void setVSource(VertexSource vSource) {
        this.vSource = vSource;
    }

    public FragmentSource getFSource() {
        return this.fSource;
    }

    public void setFSource(FragmentSource fSource) {
        this.fSource = fSource;
    }

    @Override
    public String toString() {
        return this.id + "-" + this.name + "\n" + this.vSource.toString() + this.fSource.toString();
    }


    
}
