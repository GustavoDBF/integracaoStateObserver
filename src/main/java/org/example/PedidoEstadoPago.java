package org.example;

public class PedidoEstadoPago extends PedidoEstado {

    private PedidoEstadoPago() {};
    private static PedidoEstadoPago instance = new PedidoEstadoPago();
    public static PedidoEstadoPago getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Pago";
    }

    public boolean enviar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        return true;
    }

    public boolean entregar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }

    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}