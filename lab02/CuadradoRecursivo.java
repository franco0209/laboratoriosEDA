public class CuadradoRecursivo {
    public static void main(String[] args) {
        dibujarCuadrado(5, 1, 1);
    }

    public static void dibujarCuadrado(int tamaño, int fila, int col) {
        if (fila > tamaño) return;
        
        if (col > tamaño) {
            System.out.println();
            dibujarCuadrado(tamaño, fila + 1, 1);
            return;
        }

        if (fila == 1 || fila == tamaño || col == 1 || col == tamaño) {
            System.out.print("*");
        } else {
            System.out.print(" ");
        }

        dibujarCuadrado(tamaño, fila, col + 1);
    }
}