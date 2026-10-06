package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracaoPedidoTest {

    @Test
    void deveNotificarClienteQuandoEstadoMudar() {
        Pedido pedido = new Pedido(101, "Notebook", "Express Log");
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanharPedido(pedido);

        assertTrue(pedido.pagar());

        assertEquals(PedidoEstadoPago.getInstance(), pedido.getEstado());
        assertEquals("Cliente 1, status atualizado no Pedido{codigo=101, produto='Notebook', transportadora='Express Log'}", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoCancelarPedido() {
        Pedido pedido = new Pedido(102, "Smartphone", "Rapidão Transp");
        Cliente cliente = new Cliente("Cliente 2");
        cliente.acompanharPedido(pedido);

        assertTrue(pedido.cancelar());

        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
        assertEquals("Cliente 2, status atualizado no Pedido{codigo=102, produto='Smartphone', transportadora='Rapidão Transp'}", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteEmFluxoCompletoDeEstados() {
        Pedido pedido = new Pedido(103, "Monitor", "Logística Fast");
        Cliente cliente = new Cliente("Cliente 3");
        cliente.acompanharPedido(pedido);

        assertTrue(pedido.pagar());
        assertEquals("Cliente 3, status atualizado no Pedido{codigo=103, produto='Monitor', transportadora='Logística Fast'}", cliente.getUltimaNotificacao());

        assertTrue(pedido.enviar());
        assertEquals("Cliente 3, status atualizado no Pedido{codigo=103, produto='Monitor', transportadora='Logística Fast'}", cliente.getUltimaNotificacao());

        assertTrue(pedido.entregar());
        assertEquals("Cliente 3, status atualizado no Pedido{codigo=103, produto='Monitor', transportadora='Logística Fast'}", cliente.getUltimaNotificacao());
        assertEquals(PedidoEstadoEntregue.getInstance(), pedido.getEstado());
    }
}