package com.example.aplicacion;

import java.sql.Connection;
import java.sql.SQLException;

public class PedidoProducto {
    private int idPedido;
    private int idProducto;
    private int cantidad;
    
    public void añadirProductoAlPedido(int idPedido, int idProducto, int cantidad, Connection conexion) throws SQLException {
        java.sql.Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("insert into pedido_producto (id_pedido, id_producto, cantidad) values ('"+idPedido+"', '"+idProducto+"', '"+cantidad+"')");
        ejecuciones.close();
    }
}
