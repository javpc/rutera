package utilidades.unidades;

public class Medida {
    public static String mts_kms_cadena (double metros) {
        return String.format("%.2f", metros / 1000).replace(",", ".");
    }
}
