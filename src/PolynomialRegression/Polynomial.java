package PolynomialRegression;// Flores Romero Andres Gael
// PolynomialRegression.Polynomial Regression

import DataSets.DatosVariables;
import EcuacionesALL.Ecuaciones;

import java.util.ArrayList;

public class Polynomial {

    public static void main(String[] args) {
        ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Polynomial());
        OPERATIONS(Datos);
    }

    public static void OPERATIONS (ArrayList<DatosVariables> Datos) {

        double[][] MatrizX = Ecuaciones.OperacionesBasicas(Datos, 3,3);
        double[][] MatrizY = Ecuaciones.OperacionesBasicasY(Datos,3,1);

        //Matriz X
        System.out.println("\nMatriz X:");
        Ecuaciones.ImprimirMatriz(MatrizX);

        //Matriz Y
        System.out.println("\nMatriz Y:");
        Ecuaciones.ImprimirMatriz(MatrizY);

        //Calcular la Transpuesta X
        double [][] TranspuestaX = Ecuaciones.Transpuesta(MatrizX);
        System.out.println("\nMatriz Transpuesta de X: ");
        Ecuaciones.ImprimirMatriz(TranspuestaX);

        //Multiplicar Transpuesta X por MatrizX
        double[][] MultTransMX = Ecuaciones.MultMatrices(TranspuestaX, MatrizX);
        System.out.println("\nMultiplicacion de matrices: ");
        Ecuaciones.ImprimirMatriz(MultTransMX);

        //Calcular la matriz aumentada y realizar GaussJordan
        double MatrizAumentada[][]= Ecuaciones.CalcAumentada(MultTransMX);
        System.out.println("\nMatriz Aumentada:\n");
        Ecuaciones.ImprimirMatriz(MatrizAumentada);
        Ecuaciones.GussJordan(MatrizAumentada);

        //Calcular Inversa
        double MatrizInversa[][] = Ecuaciones.CalcMatrizInversa(MatrizAumentada, MultTransMX);
        System.out.println("\nMatriz Inversa (X*X^T)^-1: \n");
        Ecuaciones.ImprimirMatriz(MatrizInversa);

        //Calcular TranspuestaX por MatrizY
        System.out.println("\nX^T*Y: \n");
        double [][] MultTransY = Ecuaciones.MultMatrices(TranspuestaX, MatrizY);
        Ecuaciones.ImprimirMatriz(MultTransY);

        //Caluclar
        System.out.println("\n(X^T * X)^-1) X^T * Y: \n");
        double [][] MultFinal = Ecuaciones.MultMatrices(MatrizInversa, MultTransY);
        Ecuaciones.ImprimirMatriz(MultFinal);

        double B0 = MultFinal[0][0];
        double B1 = MultFinal[1][0];
        double B2 = MultFinal[2][0];
        double resultado = 0, x = 0;


        System.out.println("Ecuacion:\nY = " + B0 + "X^2 + " + B1 + "X + " + B2);

        System.out.println("\nSimulacion:");
        x = 15;
        resultado = B0 * x * x + B1 * x + B2;
        System.out.println("\nEjemplo x = 15 \nResultado: " + resultado);
        x= 110;
        resultado = B0 * (x * x) + B1 * x + B2;
        System.out.println("\nEjemplos x = 110 \nResultado: " + resultado);
        x= 76;
        resultado = B0 * (x * x) + B1 * x + B2;
        System.out.println("\nEjemplo x = 76 \nResultado: " + resultado);

    }

}
