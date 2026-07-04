import java.util.Scanner;

public class Operaciones {
    static final int MAX = 10;
    static String[] clientes = new String[MAX];
    static int[] horas = new int[MAX];
    static int[] servicios = new int[MAX];
    static int total = 0;

    public static void agendar(Scanner sc) {

        if (total >= MAX) {
            System.out.println("No hay cupos disponibles.");
            return;
        }

        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        }   
        while (!Validador.nombreValido(nombre));

        int hora;
        boolean ocupada;

        do {
            do {
                System.out.print("Hora (8-17): ");
                hora = sc.nextInt();
                sc.nextLine();
            } 
            while (!Validador.horaValida(hora));

            ocupada = false;

            for (int i = 0; i < total; i++) {
                if (horas[i] == hora) {
                    ocupada = true;
                    System.out.println("Esa hora ya está ocupada.");
                }
            }
        }
        while (ocupada);

        int servicio;
        do {
            System.out.print("Servicio (1-3): ");
            servicio = sc.nextInt();
            sc.nextLine();
        } 
        while (!Validador.servicioValido(servicio));

        clientes[total] = nombre;
        horas[total] = hora;
        servicios[total] = servicio;
        total++;

        System.out.println("Reserva registrada.");
    }
        public static String nombreServicio(int servicio) {
        switch (servicio) {
            case 1:
                return "Corte de cabello";
            case 2:
                return "Tinte";
            case 3:
                return "Manicure";
            default:
                return "Desconocido";
        }
    }

    public static void listar() {
        if (total == 0) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        System.out.println("=== LISTA DE RESERVAS ===");
        for (int i = 0; i < total; i++) {
            System.out.printf("%d. %s - %d:00 - Servicio %d (%s)%n", i + 1, clientes[i], horas[i], servicios[i], nombreServicio(servicios[i]));
        }
    }
    
}
