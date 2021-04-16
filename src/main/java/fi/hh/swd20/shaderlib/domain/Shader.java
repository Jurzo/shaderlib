package fi.hh.swd20.shaderlib.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
public class Shader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Size(min=2, max=30)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "vertexId")
    @NotNull
    private VertexSource vSource;

    @ManyToOne
    @JoinColumn(name = "shaderId")
    @NotNull
    private FragmentSource fSource;

    private String author;


    public Shader() {
        super();
        this.name = "";
        this.vSource = null;
        this.fSource = null;
        this.author = "-";
    }

    public Shader(String name, VertexSource vSource, FragmentSource fSource, String author) {
        this.name = name;
        this.vSource = vSource;
        this.fSource = fSource;
        this.author = author;
    }

    public Shader(String name, VertexSource vSource, FragmentSource fSource) {
        this(name, vSource, fSource, "-");
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        return this.author + ": " + this.name + "\n" + this.vSource.toString() + this.fSource.toString();
    }


    
}
