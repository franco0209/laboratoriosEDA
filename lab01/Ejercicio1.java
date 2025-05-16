import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class Ejercicio1{


    public static double calcularMediana(int[]calificaciones){
        int numEstudiantes;
        double mediana;
        calificaciones = ordenarArray(calificaciones);
        numEstudiantes = calificaciones.length;
        if(numEstudiantes % 2 ==0){
            mediana = (calificaciones[(numEstudiantes/2)-1]+calificaciones[(numEstudiantes/2)])/2.0;
        }
        else{
            mediana= calificaciones[((numEstudiantes+1)/2)-1];
        }
        return mediana;
    }

    public static int mayor(int[]numeros){
        int mayor=numeros[0];
        int pos=0;
        for (int i=0; i<numeros.length;i++){
            if (numeros[i]>mayor){
                mayor = numeros[i];
                pos =i;
            }
        }
        return pos;
    }
    public static int [] ordenarArray(int[]array){
        int auxiliar;
        for(int i=0;i<array.length;i++){
            for(int j=0; j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                auxiliar = array[j];
                array[j] = array[j+1];
                array[j+1]=auxiliar;
            }
            }
        }
        return array;
    }


    public static int calcularModa(int [] calificaciones){
        ArrayList<Integer> indices=new ArrayList<>();
        int numCalificaciones, calificacionActual;
        numCalificaciones= calificaciones.length;
        boolean create;
        int[] frecuencia = new int[numCalificaciones];

        for (int i=0; i<numCalificaciones;i++){
            create=true;
            calificacionActual = calificaciones[i];
            for (int j =0; j<indices.size();j++){
                if (calificacionActual == indices.get(j)){
                    frecuencia[j]+=1;
                    create = false;
                }
            }
            if (create){
                indices.add(calificacionActual);
                frecuencia[indices.size() - 1] = 1;
            }
        }

        return indices.get(mayor(frecuencia));
    
    }
    public static double calcularMedia(int[]calificaciones){
        double suma =0;
        int numElementos =calificaciones.length;
        for(int i=0;i<calificaciones.length;i++){
            suma=suma+calificaciones[i];
        }
        return suma/numElementos;
    }
    public static double calcularDesviacionEstandar(int[] calificaciones){
        double suma, media;
        suma =0;
        media = calcularMedia(calificaciones);
        for (int i=0;i<calificaciones.length;i++){
            suma= suma +Math.pow((calificaciones[i]-media), 2);
        }
        return Math.sqrt(suma/calificaciones.length);

    }
    public static void main(String[] args) {


        int numEstudiantes, nota, moda;
        double mediana, desviacionEstandar;


        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese el número de estudiantes: ");
        numEstudiantes = scan.nextInt();
        int [] calificaciones = new int[numEstudiantes];


        for(int i =0; i<numEstudiantes;i++){
            System.out.println("Ingrese la calificacion del estudiante número "+(i+1));
            nota = scan.nextInt();
            calificaciones[i] = nota;
        }


        moda = calcularModa(calificaciones);
        mediana = calcularMediana(calificaciones);
        desviacionEstandar= calcularDesviacionEstandar(calificaciones);
        System.err.println("Moda: "+moda);
        System.err.println("Mediana: "+mediana);
        System.err.println("Desviacion estándar: "+desviacionEstandar);
       
    }
}
