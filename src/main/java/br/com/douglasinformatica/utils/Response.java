package br.com.douglasinformatica.utils;

import java.util.ArrayList;

import br.com.douglasinformatica.models.Precos;
import br.com.douglasinformatica.models.Produto;

public class Response {
    private int tempo;
    private String local;
    private ArrayList<Produto> produtos;
    private int total;
    private Precos precos;

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Precos getPrecos() {
        return precos;
    }

    public void setPrecos(Precos precos) {
        this.precos = precos;
    }
}
