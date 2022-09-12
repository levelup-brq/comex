package br.com.alura.comex.modelo;


import br.com.alura.comex.Status;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, length = 200)
    private String nomeCategoria;

    @Column(nullable = false, length = 8)
    private Status status;

    public Long getId() {
        return idCategoria;
    }

    public void setId(Long id) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nomeCategoria + " Status: " + this.status;
    }
}