package modulo6_1_actividad08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class Actividad08Test {
  @Test
  void salidaEsperada() throws Exception {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;
    System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
    System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
    try {
      Actividad08.main(new String[0]);
    } finally {
      System.setOut(originalOut);
      System.setIn(originalIn);
    }
    String output = buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n").trim();
    assertEquals("0 2 4 6 8 10 12 14 16 18 20", output);
  }
}
