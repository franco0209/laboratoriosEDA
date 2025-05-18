public class TrianguloRecursivo2 {
    public static void main(String[] args) {
        int b = 5;
        System.out.println("Triángulo reflejado (b = " + b + "):");
        imprimirTrianguloReflejado(b);
    }
    
    public static void imprimirTrianguloReflejado(int b) {
        if (b <= 0) return; 
        
        imprimirTrianguloReflejado(b - 1);
        
        for (int i = 0; i < 5 - b; i++) System.out.print(" ");
        for (int i = 0; i < b; i++) System.out.print("*");
        System.out.println();
    }
}