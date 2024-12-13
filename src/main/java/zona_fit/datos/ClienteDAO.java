package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConnection();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        //Por buena practica de programacion hay que cerrar la conexion
        finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e);
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConnection();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente por id" + e.getMessage());
        }

        finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e);
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConnection();
        var sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES ( ? , ? , ? )";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.execute();

            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar cliente" + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean modificarCLiente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();

        /*Listar Clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);
        */

        /*Buscar por id
        var cliente1 = new Cliente(3);
        System.out.println("Cliente antes de la busqueda: "+ cliente1);
        var encontrado = clienteDao.buscarClientePorId(cliente1);
        if (encontrado){
            System.out.println("Cliente encontrado: "+ cliente1);
        }else System.out.println("No se encontra cliente: "+cliente1.getId());
         */

        //Agregar Cliente
        var cliente1 = new Cliente("Pepe","Aguilar",300);
        var agregado= clienteDao.agregarCliente(cliente1);
        if (agregado) System.out.println("Cliente agregado: "+ cliente1);
        else System.out.println("No se agrego el cliente: "+ cliente1);






    }
}
