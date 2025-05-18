public class TrianguloRecursivo3 {
    public static void main(String[] args) {
        imprimirArbol(5, 1);
    }

    public static void imprimirArbol(int altura, int nivel) {
        if (nivel > altura) return;
        
        if (altura - nivel > 0) {
            System.out.print(new String(new char[altura - nivel]).replace('\0', ' '));
        }
        
        System.out.println(new String(new char[2 * nivel - 1]).replace('\0', '*'));
        
        imprimirArbol(altura, nivel + 1);
    }
}