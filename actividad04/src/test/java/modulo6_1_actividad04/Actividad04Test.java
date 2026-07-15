package modulo6_1_actividad04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class Actividad04Test {
  @Test
  void salidaEsperada() throws Exception {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;
    System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
    System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
    try {
      Actividad04.main(new String[0]);
    } finally {
      System.setOut(originalOut);
      System.setIn(originalIn);
    }
    String output = buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n").trim();
    assertEquals(
        "Iteración 1\nProcesando 1\nIteración 2\nProcesando 2\nIteración 3\nProcesando 3", output);
  }
}
