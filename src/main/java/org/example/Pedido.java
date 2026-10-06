package org.example;

import java.util.Observable;

public class Pedido extends Observable {

    private Integer codigo;
    private String produto;
    private String transportadora;
    private String descricao;
    private PedidoEstado estado = PedidoEstadoNovo.getInstance();

    public Pedido() {
    }

    public Pedido(Integer codigo, String produto, String transportadora) {
        this.codigo = codigo;
        this.produto = produto;
        this.transportadora = transportadora;
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        atualizarStatus();
    }

    public boolean pagar() {
        return estado.pagar(this);
    }

    public boolean enviar() {
        return estado.enviar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public void atualizarStatus() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", produto='" + produto + '\'' +
                ", transportadora='" + transportadora + '\'' +
                '}';
    }
}