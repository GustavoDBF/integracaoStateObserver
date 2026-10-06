package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveNotificarUmCliente() {
        Pedido pedido = new Pedido(101, "Notebook", "Express Log");
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanharPedido(pedido);
        pedido.atualizarStatus();
        assertEquals("Cliente 1, status atualizado no Pedido{codigo=101, produto='Notebook', transportadora='Express Log'}", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientes() {
        Pedido pedido = new Pedido(101, "Notebook", "Express Log");
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        cliente1.acompanharPedido(pedido);
        cliente2.acompanharPedido(pedido);
        pedido.atualizarStatus();
        assertEquals("Cliente 1, status atualizado no Pedido{codigo=101, produto='Notebook', transportadora='Express Log'}", cliente1.getUltimaNotificacao());
        assertEquals("Cliente 2, status atualizado no Pedido{codigo=101, produto='Notebook', transportadora='Express Log'}", cliente2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarCliente() {
        Pedido pedido = new Pedido(101, "Notebook", "Express Log");
        Cliente cliente = new Cliente("Cliente 1");
        pedido.atualizarStatus();
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientePedidoA() {
        Pedido pedidoA = new Pedido(101, "Notebook", "Express Log");
        Pedido pedidoB = new Pedido(102, "Smartphone", "Rapidão Transp");
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        cliente1.acompanharPedido(pedidoA);
        cliente2.acompanharPedido(pedidoB);
        pedidoA.atualizarStatus();
        assertEquals("Cliente 1, status atualizado no Pedido{codigo=101, produto='Notebook', transportadora='Express Log'}", cliente1.getUltimaNotificacao());
        assertEquals(null, cliente2.getUltimaNotificacao());
    }
}