package modulo6_1_actividad04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class Actividad04Test {

    private static final String SALIDA_ESPERADA = "Iteración 1\nProcesando 1\nIteración 2\nProcesando 2\nIteración 3\nProcesando 3";

    private static final Pattern NUMERO = Pattern.compile("-?\\d+(?:[.,]\\d+)?");

    @Test
    void pruebaSalidaEsperada() throws Exception {
        String salida = ejecutarMain();
        comprobarSalida(SALIDA_ESPERADA, salida);
    }

    @Test
    void pruebaCodigoFuente() throws Exception {
        String codigo = leerCodigoFuente();
        assertTrue(contar(codigo, "\\bfor\\s*\\(") > 0,
                "Usa un bucle for.");
    }

    private String ejecutarMain() throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            Actividad04.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString(StandardCharsets.UTF_8);
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
        String codigo = Files.readString(Path.of("src/main/java/modulo6_1_actividad04/Actividad04.java"), StandardCharsets.UTF_8);
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
