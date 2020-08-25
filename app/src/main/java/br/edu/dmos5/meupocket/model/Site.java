package br.edu.dmos5.meupocket.model;

import androidx.annotation.NonNull;

public class Site {

    private String titulo;
    private String endereco;
    private boolean favorito;

    public Site(String titulo, String endereco) {
        this.titulo = titulo;
        this.endereco = endereco;
        favorito = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isFavorito() {
        return favorito;
    }

    /*** Torna o site um favorito.*/
    public void doFavotite() {
        this.favorito = true;
    }

    /*** Faz com que o site não seja mais favorito.*/
    public void undoFavorite() {
        this.favorito = false;
    }

    @NonNull
    @Override
    public String toString() {
        return getTitulo();
    }
}
