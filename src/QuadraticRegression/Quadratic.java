package QuadraticRegression;// Flores Romero Andres Gael
// QuadraticRegression.Quadratic Regression

import DataSets.DatosVariables;
import EcuacionesALL.Ecuaciones;

import java.util.ArrayList;

public class Quadratic {

    public static void main(String[] args) {
        ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.DatosQuadratic());
        OPERATIONS(Datos);
    }

    public static void OPERATIONS(ArrayList<DatosVariables> Datos){

        double[][] MatrizX = Ecuaciones.OperacionesBasicas(Datos,3,4);
        System.out.println("Matriz X:");
        Ecuaciones.ImprimirMatriz(MatrizX);

        System.out.println("Matriz Resuelta por Gauss Jordan: ");
        Ecuaciones.GussJordan(MatrizX);
        Ecuaciones.ImprimirMatriz(MatrizX);

        double BO = MatrizX[0][3];
        double B1 = MatrizX[1][3];
        double B2 = MatrizX[2][3];

        System.out.println("\nEcuacion:\nY = B0x^2 * B1x + B2");
        System.out.println("Resultado: " + BO + "x^2 * " + B1 + "x + " + B2 );

        double resultado = 0;

        System.out.println("\nSimulacion: ");
        resultado = BO + 5*5 + B1 + 5 + B2;
        System.out.println("Ejemplo 1: B0(5)^2 * B1(5)+ B2 = " + resultado );
        resultado = BO + 7*7 + B1 + 7 + B2;
        System.out.println("Ejemplo 2: B0(7)^2 * B1(7)+ B2 = " + resultado );
        resultado = BO + 2*2 + B1 + 2 + B2;
        System.out.println("Ejemplo 3: B0(2)^2 * B1(2)+ B2 = " + resultado );

    }

}

