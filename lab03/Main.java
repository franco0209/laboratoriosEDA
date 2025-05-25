import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Raíz Cúbica");
            System.out.println("8. Salir del Programa");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            
            if (opcion >= 1 && opcion <= 7) {
                System.out.print("Ingrese el primer valor: ");
                double num1 = scanner.nextDouble();
                System.out.print("Ingrese el segundo valor: ");
                double num2 = scanner.nextDouble();
                
                Operador<Double> operador = new Operador<>(num1, num2);
                
                switch (opcion) {
                    case 1:
                        System.out.println("Resultado: " + suma(operador));
                        break;
                    case 2:
                        System.out.println("Resultado: " + resta(operador));
                        break;
                    case 3:
                        System.out.println("Resultado: " + producto(operador));
                        break;
                    case 4:
                        System.out.println("Resultado: " + division(operador));
                        break;
                    case 5:
                        System.out.println("Resultado: " + potencia(operador));
                        break;
                    case 6:
                        System.out.println("Raíz cuadrada de " + num1 + ": " + raizCuadrada(num1));
                        System.out.println("Raíz cuadrada de " + num2 + ": " + raizCuadrada(num2));
                        break;
                    case 7:
                        System.out.println("Raíz cúbica de " + num1 + ": " + raizCubica(num1));
                        System.out.println("Raíz cúbica de " + num2 + ": " + raizCubica(num2));
                        break;
                }
            } else if (opcion != 8) {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 8);
        
        System.out.println("Programa terminado.");
        scanner.close();
    }

    public static <T extends Number> double suma(Operador<T> operador) {
        return operador.getValor1().doubleValue() + operador.getValor2().doubleValue();
    }

    public static <T extends Number> double resta(Operador<T> operador) {
        return operador.getValor1().doubleValue() - operador.getValor2().doubleValue();
    }

    public static <T extends Number> double producto(Operador<T> operador) {
        return operador.getValor1().doubleValue() * operador.getValor2().doubleValue();
    }

    public static <T extends Number> double division(Operador<T> operador) {
        if (operador.getValor2().doubleValue() == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return operador.getValor1().doubleValue() / operador.getValor2().doubleValue();
    }

    public static <T extends Number> double potencia(Operador<T> operador) {
        return Math.pow(operador.getValor1().doubleValue(), operador.getValor2().doubleValue());
    }

    public static double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new ArithmeticException("No existe raíz cuadrada real para números negativos");
        }
        return Math.sqrt(numero);
    }

    public static double raizCubica(double numero) {
        return Math.cbrt(numero);
    }
}