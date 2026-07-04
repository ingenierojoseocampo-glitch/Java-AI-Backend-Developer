import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            Menu.mostrar();
            opcion = Menu.leerOpcion(sc);
            switch (opcion) {
                case 1:
                    Operaciones.agendar(sc);
                    break;
                case 2:
                     Operaciones.listar();
                     break;
                // case 3:
                //     Operaciones.cancelar(sc);
                //     break;
                // case 4:
                //     Operaciones.verReporteDiario();
                //     break;
                case 5:
                    System.out.println("Gracias por usar nuestro servicio. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
