package modulo6_1_actividad13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class Actividad13Test {
  @Test
  void salidaEsperada() throws Exception {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;
    System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
    System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
    try {
      Actividad13.main(new String[0]);
    } finally {
      System.setOut(originalOut);
      System.setIn(originalIn);
    }
    String output = buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n").trim();
    assertEquals(
        "7 x 1 = 7\n"
            + "7 x 2 = 14\n"
            + "7 x 3 = 21\n"
            + "7 x 4 = 28\n"
            + "7 x 5 = 35\n"
            + "7 x 6 = 42\n"
            + "7 x 7 = 49\n"
            + "7 x 8 = 56\n"
            + "7 x 9 = 63\n"
            + "7 x 10 = 70",
        output);
  }
}
