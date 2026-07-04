public class Validador {

    public static boolean horaValida(int hora) {
        return hora >= 8 && hora <= 17;
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty() && nombre.matches("[a-zA-Z ]+");
    }

    public static boolean servicioValido(int servicio) {
        return servicio >= 1 && servicio <= 3;
    }
}
