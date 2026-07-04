import java.util.Scanner;

public class Opciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        System.out.println("Seleccione una opción:");
        System.out.println("1. Nuevo Pedido");
        System.out.println("2. Aplicar Descuento");
        System.out.println("3. Cerrar Caja");  
        
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Nuevo Pedido");
                break;
            case 2:
                System.out.println("Aplicar Descuento");
                break;
            case 3:
                System.out.println("Cerrar Caja");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
