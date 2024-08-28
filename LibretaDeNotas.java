package Tarea2daparte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibretaDeNotas {
    public static void main(String[] args) {
        Scanner variable = new Scanner(System.in);
        HashMap<String, ArrayList<Double>> alumnosNotas = new HashMap<>();

        System.out.println("Introduce el número de alumnos: ");
        int numeroDeAlumnos = variable.nextInt();

        System.out.println("Introduce el número de notas por alumno: ");
        int numeroDeNotas = variable.nextInt();

        for (int i = 0; i < numeroDeAlumnos; i++) {
            System.out.println("Introduce el nombre del alumno: ");
            variable.nextLine(); // Consumir el salto de línea
            String nombre = variable.nextLine();

            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < numeroDeNotas; j++) {
                System.out.println("Introduce la nota " + (j + 1) + " de " + nombre + ": ");
                double nota = variable.nextDouble();
                while (nota <= 0 || nota >= 10) { // Validación de nota
                    System.out.println("Nota inválida. Introduce una nota entre 0 y 10: ");
                    nota = variable.nextDouble();
                }
                notas.add(nota);
            }
            alumnosNotas.put(nombre, notas);
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");
            int opcion = variable.nextInt();

            switch (opcion) {
                case 1:
                    mostrarPromedios(alumnosNotas);
                    break;
                case 2:
                    verificarAprobacion(alumnosNotas, variable);
                    break;
                case 3:
                    compararConPromedioCurso(alumnosNotas, variable);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
    }

    public static void mostrarPromedios(HashMap<String, ArrayList<Double>> alumnosNotas) {
        for (String alumno : alumnosNotas.keySet()) {
            ArrayList<Double> notas = alumnosNotas.get(alumno);
            double promedio = calcularPromedio(notas);
            System.out.println(alumno + " - Promedio: " + promedio);
        }
    }

    public static void verificarAprobacion(HashMap<String, ArrayList<Double>> alumnosNotas, Scanner variable) {
        System.out.println("Introduce el nombre del alumno: ");
        variable.nextLine(); // Consumir el salto de línea
        String nombre = variable.nextLine();
        if (alumnosNotas.containsKey(nombre)) {
            System.out.println("Introduce la nota a verificar: ");
            double nota = variable.nextDouble();
            if (nota >= 4.0) {
                System.out.println(nombre + " ha aprobado con una nota de " + nota);
            } else {
                System.out.println(nombre + " ha reprobado con una nota de " + nota);
            }

            } else {
            System.out.println("El alumno no existe.");
        }
    }

    public static void compararConPromedioCurso(HashMap<String, ArrayList<Double>> alumnosNotas, Scanner variable) {
        double promedioCurso = calcularPromedioCurso(alumnosNotas);
        System.out.println("Introduce el nombre del alumno: ");
        variable.nextLine(); // Consumir el salto de línea
        String nombre = variable.nextLine();
        if (alumnosNotas.containsKey(nombre)) {
            System.out.println("Introduce la nota a comparar: ");
            double nota = variable.nextDouble();
            if (nota > promedioCurso) {
                System.out.println(nombre + " tiene una nota por sobre el promedio del curso.");
            } else if (nota < promedioCurso) {
                System.out.println(nombre + " tiene una nota por debajo del promedio del curso.");
            } else {
                System.out.println(nombre + " tiene una nota igual al promedio del curso.");
            }
        } else {
            System.out.println("El alumno no existe.");
        }
    }

    public static double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    public static double calcularPromedioCurso(HashMap<String, ArrayList<Double>> alumnosNotas) {
        double sumaTotal = 0;
        int cantidadNotas = 0;
        for (ArrayList<Double> notas : alumnosNotas.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                cantidadNotas++;
            }
        }
        return sumaTotal / cantidadNotas;

    }
}
