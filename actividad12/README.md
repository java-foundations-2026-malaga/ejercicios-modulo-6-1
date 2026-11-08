# JFo 6.1 - Actividad 12

## Calcula la media de varias notas

Lee con `Scanner` un número entero N y después N notas enteras; súmalas con un `for`, calcula la media como `double` (cuidado con la división entera) e imprime `Media: <media>`. El test introduce `3` y después `7`, `8` y `9` (y prueba también con otros datos). Si muestras mensajes para pedir los datos, `Media: ...` debe ser lo último que se imprima.

## Objetivo

- Leer el código base y corregirlo o completarlo.
- Ejecutar el programa y comprobar el resultado.
- Ejecutar los tests JUnit para confirmar la autocorrección.
- Hacer `git add`, `git commit` y `git push`.

## Salida esperada

```text
Media: 8.0
```

## Instrucciones para Eclipse

1. Importa este proyecto como proyecto Maven.
2. Abre `src/main/java/modulo6_1_actividad12/Actividad12.java`.
3. Corrige o completa el código.
4. Ejecuta `Run As > Java Application`.
5. Ejecuta `Actividad12Test.java` con `Run As > JUnit Test`.
6. Haz commit y push cuando la actividad funcione.

## GitHub Actions

- Sube tu trabajo a **tu rama** (no a `main`): el workflow `Autocorrección` compila y ejecuta los tests de cada actividad que hayas modificado.
- En la pestaña **Actions** del repositorio verás ✅ si la actividad está aprobada o ❌ si hay errores de compilación o tests que fallan.
