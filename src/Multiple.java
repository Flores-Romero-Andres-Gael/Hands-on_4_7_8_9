// Flores Romero Andres Gael
// Multiple Linear Regression

import java.math.BigDecimal;
import java.util.ArrayList;

public class Multiple{

    public static void main(String[] args) {
        int a = 0;

        //Cambiar DataSets
        if (a == 1){
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Datos50SUMult());
            OPERATIONS(Datos, a);
        }else {
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.A());
            OPERATIONS(Datos, a);
        }

    }

    public static void OPERATIONS(ArrayList<DatosVariables> Datos, int flag){
        int Filas = Datos.size();
        int Columns;

        if(flag==1){
            Columns = 4;
        }else{
            Columns = 3;
        }

        double [][] MatrizX = new double[Filas][Columns];
        double [][] MatrizY = new double[Filas][1];

        if (Columns == 4){
            for (int i = 0; i < Filas; i++) {
                MatrizX[i][0] = Datos.get(i).x;
                MatrizX[i][1] = Datos.get(i).x1;
                MatrizX[i][2] = Datos.get(i).x2;
                MatrizX[i][3] = Datos.get(i).x3;
                MatrizY[i][0] = Datos.get(i).y;
            }

        }else{
            for (int i = 0; i < Filas; i++) {
                MatrizX[i][0] = Datos.get(i).x;
                MatrizX[i][1] = Datos.get(i).x1;
                MatrizX[i][2] = Datos.get(i).x2;
                MatrizY[i][0] = Datos.get(i).y;
            }
        }

        System.out.println("Matriz X: ");
        Ecuaciones.ImprimirMatriz(MatrizX);
        System.out.println("\nMatriz Y: ");
        Ecuaciones.ImprimirMatriz(MatrizY);

        double [][] TranspuestaX = Ecuaciones.Transpuesta(MatrizX);
        System.out.println("\nMatriz Transpuesta de X: ");
        Ecuaciones.ImprimirMatriz(TranspuestaX);

        System.out.println("\nMultiplicacion de matrices: ");
        double[][] MultTransMX = Ecuaciones.MultMatrices(TranspuestaX, MatrizX);
        Ecuaciones.ImprimirMatriz(MultTransMX);

        //Calcular la matriz Aumentada mediante Gauss Jordan
        double MatrizAumentada[][]= Ecuaciones.CalcAumentada(MultTransMX);
        System.out.println("\nMatriz Aumentada:\n");
        Ecuaciones.ImprimirMatriz(MatrizAumentada);
        Ecuaciones.GussJordan(MatrizAumentada);
        System.out.println("\nMatriz Aumentada Gauss Jordan: \n");
        Ecuaciones.ImprimirMatriz(MatrizAumentada);

        //Se pasan los valores de la Matriz Aumentada sobrante a la Matriz Inversa
        double MatrizInversa[][]= Ecuaciones.CalcMatrizInversa(MatrizAumentada,MultTransMX);
        System.out.println("\nMatriz Inversa (X*X^T)^-1: \n");
        Ecuaciones.ImprimirMatriz(MatrizInversa);

        System.out.println("\nX^T*Y: \n");
        double [][] MultTransY = Ecuaciones.MultMatrices(TranspuestaX, MatrizY);
        Ecuaciones.ImprimirMatriz(MultTransY);

        System.out.println("\n(X^T * X)^-1) X^T * Y: \n");
        double [][] MultFinal = Ecuaciones.MultMatrices(MatrizInversa, MultTransY);
        Ecuaciones.ImprimirMatriz(MultFinal);


        if (MultFinal.length == 4) {
            double B0 = MultFinal[0][0];
            double B1 = MultFinal[1][0];
            double B2 = MultFinal[2][0];
            double B3 = MultFinal[3][0];

            System.out.println("Ecuacion:\n Y = " + B0 +" + "+ B1 +" * x1 + "+ B2 + " * x2 +" + B3 + "* x3" );

            double resultado = 0;
            System.out.println("\nSimulacion:");
            resultado = B0 + B1 * 34 + B2 * 87 + B3 * 103;
            System.out.println("\nEjemplos: x1 = 34, x2 = 87, x3 = 103 \nResultado: " + resultado);
            resultado = B0 + B1 * 89 + B2 * 401 + B3 * 13;
            System.out.println("\nEjemplos: x1 = 89, x2 = 401, x3 = 13 \nResultado: " + resultado);
            resultado = B0 + B1 * 10 + B2 * 207 + B3 * 101;
            System.out.println("\nEjemplos: x1 = 10, x2 = 207, x3 = 101 \nResultado: " + resultado);

        }else{
            double B0 = MultFinal[0][0];
            double B1 = MultFinal[1][0];
            double B2 = MultFinal[2][0];

            System.out.println("\nEcuacion: " + B0 +" + "+ B1 +" * x1 + "+ B2 + " * x2");

            double resultado = 0;
            System.out.println("\nSimulacion: ");
            resultado = B0 + B1 * 34 + B2 * 87;
            System.out.println("\nEjemplos: x1 = 34, x2 = 87\nResultado: " + resultado);
            resultado = B0 + B1 * 89 + B2 * 401;
            System.out.println("\nEjemplos: x1 = 89, x2 = 401\nResultado: " + resultado);
            resultado = B0 + B1 * 10 + B2 * 207;
            System.out.println("\nEjemplos: x1 = 10, x2 = 207\nResultado: " + resultado);

        }
    }

}

