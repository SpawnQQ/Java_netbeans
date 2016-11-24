package fcfs;

import java.util.Scanner;

public class FCFS {
   public static void FCFS(double tllegada[],int tamaño,String JOB[],int rafaga[],double tiempoAcumulado[]){
        double aux=999,verificador=0.0;
        double vectorOperacion[]=new double[tamaño];
        int vectorJob[]=new int[tamaño];
        int job=0,i;

        
        for(int j=0;j<tamaño;j++){
            for(i=0;i<tamaño;i++){
                if(tllegada[i]<aux && aux!=verificador){
                    aux=tllegada[i];
                    job=i+1;
                }
            }
        vectorOperacion[j]=aux;
        vectorJob[j]=job;
        job=0;
        } 
        
        for(i=0;i<aux;i++){
            JOB[i]="JOB"+vectorJob[i];
        }
        for(i=0;i<(aux+1);i++){
            if(i==0){
                tiempoAcumulado[i]=vectorOperacion[i];
            }
            else{
                tiempoAcumulado[i]=tiempoAcumulado[i-1]+rafaga[vectorJob[i-1]];
            }
        }
        
        
    }
    public static void main(String[] args) {
         int aux,i;
         System.out.println("Ingrese el numero de JOB con los que trabajara");
         Scanner sc=new Scanner(System.in);
         Scanner sc1;
         aux=sc.nextInt();
         double tiempoLlegada[]=new double[aux]; 
         String vectorJOB[]=new String[100];
         double vectorTT[]=new double[aux];
         double vectorWT[]=new double[aux];
         int rafaga[]=new int[aux];
         int prioridad[]=new int[aux];
         double tiempoAcumulado[]=new double[100];
         
         for(i=0;i<aux;i++){
             System.out.println("Ingrese tiempo de llegada de JOB"+(i+1));
             sc1=new Scanner(System.in);
             tiempoLlegada[i]=sc1.nextDouble();
         }
         for(i=0;i<aux;i++){
             System.out.println("Ingrese rafaga de JOB"+(i+1));
             sc1=new Scanner(System.in);
             rafaga[i]=sc1.nextInt();
         }
         for(i=0;i<aux;i++){
             System.out.println("Ingrese prioridad de JOB"+(i+1));
             sc1=new Scanner(System.in);
             prioridad[i]=sc1.nextInt();
         }
         
         FCFS(tiempoLlegada,aux,vectorJOB,rafaga,tiempoAcumulado);
         for(i=0;i<(aux+1);i++){
             System.out.println(tiempoAcumulado[i]);
         }
    }
    
}
