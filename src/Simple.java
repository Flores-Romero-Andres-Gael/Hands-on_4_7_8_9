// Flores Romero Andres Gael
// Simple Linear Regression

import DataSets.DatosVariables;

import java.util.ArrayList;

public class Simple {

    public static void main(String[] args) {
        int a = 1;

        //Cambiar DataSets
        if (a == 1){
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.A());
            OPERATIONS(Datos);
        }else {
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Datos50StartUps());
            OPERATIONS(Datos);
        }
    }

    public static void OPERATIONS(ArrayList<DatosVariables> Datos){

        double sumadorx = 0, sumadory = 0, multxy = 0, multx = 0, B0 = 0, B1 = 0, totalmultxy = 0 , totalmultx = 0, calc = 0;

        for (DatosVariables p : Datos){

            //Calcular los datos de la ecuacion
            sumadorx += p.x;
            sumadory += p.y;
            multxy = p.x * p.y;
            multx = p.x * p.x;
            totalmultxy += multxy;
            totalmultx += multx;
        }

        //Calular Pendiente y Intercepto
        System.out.println("\nFormula Pendiente : ("+totalmultxy+"-(("+sumadorx+"*"+sumadory+")/"+Datos.size()+"))/(("+totalmultx+")-("+sumadorx+"*"+sumadorx+")/"+Datos.size()+")");
        B1 = (totalmultxy - ((sumadorx * sumadory)/ Datos.size())) / ((totalmultx) - (sumadorx*sumadorx)/ Datos.size());
        System.out.println("Pendiente: " + B1);
        System.out.println("Forumla Intercepto: (("+sumadory+")/"+Datos.size()+")-("+B1+"*("+sumadorx+"/"+Datos.size()+"))");
        B0 = ((sumadory)/ Datos.size())-(B1 * (sumadorx/ Datos.size()));
        System.out.println("Intercepto: " + B0 + "\n");

        for (DatosVariables p: Datos){
            calc = B0 + B1 * p.x;
            System.out.println("Simulacion: " + calc);
        }
        System.out.println("Ecuacion:\ny = B0 + B1(x)");
        System.out.println("Ejemplos:");
        System.out.println("1. X = 143 : Simulacion: " + (B0 + B1 * 143));
        System.out.println("2. X = 651 : Simulacion: " + (B0 + B1 * 651));
        System.out.println("3. X = 35  : Simulacion: " + (B0 + B1 * 35));

    }

}
