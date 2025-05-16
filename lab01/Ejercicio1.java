import java.util.Scanner;


public class Ejercicio1{


    public static double calcularMediana(int[]calificaciones){
        int numEstudiantes;
        double mediana;
        numEstudiantes = calificaciones.length;
        if(numEstudiantes % 2 ==0){
            mediana = (calificaciones[(numEstudiantes/2)-1]+calificaciones[(numEstudiantes/2)])/2.0;
        }
        else{
            mediana= calificaciones[((numEstudiantes+1)/2)-1];
        }
        return mediana;
    }


    public static int calcularModa(int [] calificaciones){
        int [] frecuencia, buscados;
        frecuencia = new int[calificaciones.length];
        buscados = new int[calificaciones.length];
        for(int i =0;i<calificaciones.length;i++){
            int actual = calificaciones[i];
            for (int j=0; j<caloi)


        }

    }
    public static void main(String[] args) {


        int numEstudiantes, nota, moda;
        double mediana, desviacionEstandar;


        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el número de estudiantes: ");
        numEstudiantes = scan.nextInt();
        int [] calificaciones = new int[numEstudiantes];


        for(int i =0; i<numEstudiantes;i++){
            nota = scan.nextInt();
            calificaciones[i] = nota;
        }


        mediana = calcularMediana(calificaciones);
        System.err.println(mediana);


       
       
    }
}
