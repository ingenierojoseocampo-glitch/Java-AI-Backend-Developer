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

    public static void cancelar(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        int reserva;
        do {
            System.out.print("Ingrese el numero de la reserva: ");
            reserva = sc.nextInt();
            sc.nextLine();
        }while (reserva < 1 || reserva > total);

        int indice = reserva - 1;
        for (int i = indice; i < total - 1; i++) {
            clientes[i] = clientes[i + 1];
            horas[i] = horas[i + 1];
            servicios[i] = servicios[i + 1];
        }
        clientes[total - 1] = null;
        horas[total - 1] = 0;
        servicios[total - 1] = 0;
        total--;
        System.out.println("Reserva cancelada correctamente.");
    }
    public static void verReporteDiario() {
        if (total == 0) {
            System.out.println("No hay reservas registradas.");
        }
        int dineroFacturado = 0;
        for (int i = 0; i < total; i++) {
            if (servicios[i] == 1) {
                dineroFacturado += 25000;
            }
            else if (servicios[i] == 2) {
                dineroFacturado += 60000;
            }
            else {
                dineroFacturado += 30000;
            }
        }
        System.out.println("===== REPORTE DIARIO =====");
        System.out.println("Total de citas: " + total);
        System.out.println("Dinero facturado: $" + dineroFacturado);
    }
}
