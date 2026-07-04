public class Condicionales {
    public static void main(String[] args) {
        //! if solo
        //!double cuenta = 150000;

        //!if (cuenta > 100000) {
        //!    System.out.println("Aplica descuento");
        //!}

        //! if-else
        double cuenta = 150000;

        if (cuenta >= 200000) {
            System.out.println("Descuento VIP (15%)");
        } else if (cuenta > 100000) {
            System.out.println("Descuento Regular (10%)");
        } else {
            System.out.println("sin descuento");
        }
    }
}
