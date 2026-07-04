import java.util.Scanner;

public class Menu {

    public static void mostrar() {
        System.out.println("=== GESTOR DE RESERVAS ===");
        System.out.println("1. Agendar reserva");
        System.out.println("2. Listar reservas");
        System.out.println("3. Cancelar reserva");
        System.out.println("4. Reporte Diario");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }

    public static int leerOpcion(Scanner sc) {
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

}
