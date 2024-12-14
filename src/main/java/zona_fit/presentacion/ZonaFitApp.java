package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitapp();
    }

    private void zonaFitapp(){
        var salir = false;
        var consola = new Scanner(System.in);

        //Creamos un objeto de la clase clienteDAO
        var clienteDao = new ClienteDAO();

        while (!salir){
            try {
                mostrarMenu();
                //salir = ejecutarOpciones(consola, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: "+e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu(){
        System.out.println("""
                *** Zona Fit (Gym)
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opcion:\s""");
    }
}
