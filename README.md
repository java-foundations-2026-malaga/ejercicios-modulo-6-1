# JFo 6.1 - Bucles for

Esta carpeta contiene 15 actividades autocorregibles.

## Actividades

01. Corrige un for sin punto y coma en la cabecera
02. Corrige un bucle que no se ejecuta
03. Corrige un bucle infinito por actualización incorrecta
04. Corrige un cuerpo de bucle sin llaves
05. Corrige una variable fuera de ámbito
06. Crea una cuenta atrás de lanzamiento
07. Imprime números del 0 al 5
08. Imprime números pares del 0 al 20
09. Imprime cuadrados del 1 al 5
10. Suma los números del 1 al 10
11. Suma N números introducidos por teclado
12. Calcula la media de varias notas
13. Tabla de multiplicar
14. Generador de potencias de 2
15. Reescribe un programa repetitivo con for

Cada subcarpeta `actividadNN` es un proyecto Maven independiente con tests JUnit 5 y workflow de GitHub Actions.

## Cómo entregar las actividades

La rama `main` está protegida: contiene los enunciados originales y no se puede modificar.
Cada alumno trabaja en **su propia rama**, con su nombre.

1. Clona el repositorio:
   `git clone https://github.com/java-foundations-2026-malaga/REPOSITORIO.git`
2. Crea tu rama con tu nombre y apellido, en minúsculas y sin tildes ni espacios (por ejemplo `juan-perez`):
   `git checkout -b juan-perez`
   (En Eclipse: botón derecho sobre el proyecto > `Team > Switch To > New Branch...`).
3. Importa en Eclipse la carpeta `actividadNN` que vayas a hacer (`File > Import... > Maven > Existing Maven Projects`), resuélvela y comprueba los tests con `Run As > JUnit Test`.
4. Haz commit y sube **tu rama**:
   `git add .` → `git commit -m "Actividad NN"` → `git push -u origin juan-perez`
   (En Eclipse: `Git Staging` > `Commit and Push...`).
5. En la pestaña **Actions** del repositorio verás el resultado de la autocorrección de cada actividad que hayas modificado: ✅ aprobada o ❌ con los errores.

Si al hacer push te aparece un error sobre `main`, es que estás en la rama equivocada: cambia a tu rama con `git checkout juan-perez`.
