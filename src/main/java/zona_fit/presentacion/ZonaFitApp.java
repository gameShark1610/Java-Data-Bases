package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        //Creamos un objeto de la clase clienteDAO
        IClienteDAO clienteDao = new ClienteDAO();

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                *** Zona Fit (Gym)
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO) {

        var salir = false;
        switch (opcion) {
            case 1 -> { // 1. Listar Clientes
                System.out.println("--- Listado de clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // 2. Buscar cliente por id
                System.out.println("Introduce el id del Cliente a buscar");
                var cliente = new Cliente(Integer.parseInt(consola.nextLine()));
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado){
                    System.out.println("Cliente encontrado: "+ cliente);
                }else System.out.println("Cliente NO encontrado: "+cliente.getId());
            }
            case 3 -> { // 3. Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombre,apellido,membresia);
                var agregar = clienteDAO.agregarCliente(cliente);
                if (agregar){
                    System.out.println("Cliente agregado: "+ cliente);
                }else System.out.println("Cliente NO agregado: "+cliente.getId());

            }
            case 4 -> { // 4. Modificar cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.println("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente,nombre,apellido,membresia);
                var modificar = clienteDAO.modificarCLiente(cliente);
                if (modificar){
                    System.out.println("Cliente modificado: "+ cliente);
                }else System.out.println("Cliente NO modifica: "+cliente.getId());
            }
            case 5 -> { // 5. Eliminar cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.println("Introduce el id del Cliente a Eliminar");
                var cliente = new Cliente(Integer.parseInt(consola.nextLine()));
                var eliminar = clienteDAO.eliminarCliente(cliente);
                if (eliminar){
                    System.out.println("Cliente eliminado: "+ cliente);
                }else System.out.println("Cliente NO eliminado: "+cliente.getId());
            }
            case 6 -> {// 6. Salir
                System.out.println("Hasta pronto");
                salir=true;
            }
            default -> System.out.println("Opcion no reconocida: "+ opcion);

        }
        return salir;

    }
}
