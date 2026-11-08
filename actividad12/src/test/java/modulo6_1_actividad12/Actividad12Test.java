package modulo6_1_actividad12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class Actividad12Test {

    private static final String SALIDA_ESPERADA = "Media: 8.0";

    private static final Pattern NUMERO = Pattern.compile("-?\\d+(?:[.,]\\d+)?");

    @Test
    void pruebaSalidaEsperada() throws Exception {
        String salida = ejecutarMain("3\n7\n8\n9\n");
        comprobarUltimaLinea(SALIDA_ESPERADA, salida);
    }

    @Test
    void pruebaOtrosDatos() throws Exception {
        String salida = ejecutarMain("2\n5\n6\n");
        comprobarUltimaLinea("Media: 5.5", salida);
    }

    @Test
    void pruebaCodigoFuente() throws Exception {
        String codigo = leerCodigoFuente();
        assertTrue(contar(codigo, "\\bfor\\s*\\(") > 0,
                "Usa un bucle for.");
    }

    private String ejecutarMain(String entrada) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
        try {
            Actividad12.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }

    // Compara solo la ultima linea, desde la etiqueta, para admitir mensajes previos
    // que pidan los datos (por ejemplo "Introduce un numero: ").
    private void comprobarUltimaLinea(String esperada, String real) {
        String[] lineas = normalizar(real).split("\n", -1);
        String ultima = lineas[lineas.length - 1];
        int posicion = ultima.lastIndexOf("Media:");
        if (posicion > 0) {
            ultima = ultima.substring(posicion);
        }
        comprobarSalida(esperada, ultima);
    }

    // Compara linea a linea ignorando espacios al principio y al final de cada linea,
    // saltos de linea finales y pequenas diferencias de redondeo en numeros decimales.
    private void comprobarSalida(String esperada, String real) {
        String[] lineasEsperadas = normalizar(esperada).split("\n", -1);
        String[] lineasReales = normalizar(real).split("\n", -1);
        boolean iguales = lineasEsperadas.length == lineasReales.length;
        for (int i = 0; iguales && i < lineasEsperadas.length; i++) {
            iguales = lineasIguales(lineasEsperadas[i], lineasReales[i]);
        }
        if (!iguales) {
            assertEquals(normalizar(esperada), normalizar(real));
        }
    }

    private String normalizar(String texto) {
        String[] lineas = texto.replace("\r\n", "\n").replace('\r', '\n').trim().split("\n", -1);
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < lineas.length; i++) {
            if (i > 0) {
                resultado.append('\n');
            }
            resultado.append(lineas[i].trim());
        }
        return resultado.toString();
    }

    private boolean lineasIguales(String esperada, String real) {
        if (esperada.equals(real)) {
            return true;
        }
        if (!NUMERO.matcher(esperada).replaceAll("#").equals(NUMERO.matcher(real).replaceAll("#"))) {
            return false;
        }
        Matcher numerosEsperados = NUMERO.matcher(esperada);
        Matcher numerosReales = NUMERO.matcher(real);
        while (numerosEsperados.find() && numerosReales.find()) {
            String numeroEsperado = numerosEsperados.group();
            String numeroReal = numerosReales.group();
            if (numeroEsperado.contains(".")) {
                if (!numeroReal.contains(".") && !numeroReal.contains(",")) {
                    return false;
                }
                double valorEsperado = Double.parseDouble(numeroEsperado);
                double valorReal = Double.parseDouble(numeroReal.replace(',', '.'));
                if (Math.abs(valorEsperado - valorReal) > 1e-6) {
                    return false;
                }
            } else if (!numeroEsperado.equals(numeroReal)) {
                return false;
            }
        }
        return true;
    }

    // Lee el codigo del alumno sin comentarios.
    private String leerCodigoFuente() throws Exception {
        String codigo = Files.readString(Path.of("src/main/java/modulo6_1_actividad12/Actividad12.java"), StandardCharsets.UTF_8);
        codigo = codigo.replaceAll("(?s)/\\*.*?\\*/", "");
        codigo = codigo.replaceAll("//[^\n]*", "");
        return codigo;
    }

    private int contar(String codigo, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(codigo);
        int total = 0;
        while (matcher.find()) {
            total++;
        }
        return total;
    }
}
