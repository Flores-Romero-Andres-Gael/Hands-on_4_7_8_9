// Flores Romero Andres Gael
// Quadratic Regression

import java.util.ArrayList;

public class Quadratic {

    public static void main(String[] args) {
        ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.DatosQuadratic());
        OPERATIONS(Datos);
    }

    public static void OPERATIONS(ArrayList<DatosVariables> Datos){
        int Num= Datos.size();
        double SumX = 0, SumY = 0, MultXY = 0, MultX2 = 0,MultX3 = 0, MultX4 = 0, MultXXY = 0;

        for (DatosVariables p : Datos) {
            double X = p.x;
            double y = p.y;

            SumX += X;
            SumY += y;
            MultXY += X * y;
            MultX2 += X * X;
            MultX3 += X * X * X;
            MultX4 += X * X * X * X;
            MultXXY += X*X * y ;

        }

        double[][] MatrizX = new double[3][4];

        //Matriz X
        MatrizX[0][0] = MultX4;
        MatrizX[0][1] = MultX3;
        MatrizX[0][2] = MultX2;
        MatrizX[1][0] = MultX3;
        MatrizX[1][1] = MultX2;
        MatrizX[1][2] = SumX;
        MatrizX[2][0] = MultX2;
        MatrizX[2][1] = SumX;
        MatrizX[2][2] = Num;
        MatrizX[0][3] = MultXXY;
        MatrizX[1][3] = MultXY;
        MatrizX[2][3] = SumY;

        System.out.println("Matriz X:");
        ImprimirMatriz(MatrizX);

        System.out.println("Matriz Resuelta por Gauss Jordan: ");
        GussJordan(MatrizX);

        double BO = MatrizX[0][3];
        double B1 = MatrizX[1][3];
        double B2 = MatrizX[2][3];

        System.out.println("\nEcuacion: Y = B0x^2 * B1x + B2");
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

    public static void ImprimirMatriz(double[][] Matriz) {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                System.out.printf("%.15f\t", Matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static void GussJordan(double[][] Matriz) {
        int Filas = Matriz.length, Columns = Matriz[0].length;

        for (int Carreado = 0; Carreado < Filas; Carreado++) {

            double Factor = Matriz[Carreado][Carreado];
            for (int j = Carreado; j < Columns; j++) {
                Matriz[Carreado][j] /= Factor;
            }

            for (int i = 0; i < Filas; i++) {
                if (i != Carreado) {
                    double factor = Matriz[i][Carreado];
                    for (int j = Carreado; j < Columns; j++) {
                        Matriz[i][j] -= factor * Matriz[Carreado][j];
                    }
                }
            }
        }

        ImprimirMatriz(Matriz);

    }

}

