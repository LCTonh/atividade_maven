package br.com.mvc.model;
import java.time.LocalDateTime;

public class Review {
    private Long id;
    private Long cafeId;
    private String autor;
    private Long notaBebida;
    private Long notaAmbiente;
    private Long notaAtendimento;
    private String comentario;
    private LocalDateTime criadoEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCafeId() {
        return cafeId;
    }

    public void setCafeId(Long cafe_id) {
        this.cafeId = cafe_id;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getNotaBebida() {
        return notaBebida;
    }

    public void setNotaBebida(Long notaBebida) {
        this.notaBebida = notaBebida;
    }

    public Long getNotaAmbiente() {
        return notaAmbiente;
    }

    public void setNotaAmbiente(Long notaAmbiente) {
        this.notaAmbiente = notaAmbiente;
    }


    public Long getNotaAtendimento() {
        return notaAtendimento;
    }

    public void setNotaAtendimento(Long notaAtendimento) {
        this.notaAtendimento = notaAtendimento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
