// Flores Romero Andres Gael
// Polynomial Regression

import java.util.ArrayList;

public class Polynomial {

    public static void main(String[] args) {
        ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Polynomial());
        OPERATIONS(Datos);
    }

    public static void OPERATIONS (ArrayList<DatosVariables> Datos) {
        double SumX = 0, SumY = 0, MultXY = 0, MultX2 = 0,MultX3 = 0, MultX4 = 0, MultXXY = 0;
        int Filas = Datos.size(), Polynomios = 2, Num = Datos.size();

        double[][] MatrizDatos = new double[Filas][3];
        double[][] MatrizX = new double[3][3];
        double[][] MatrizY = new double[3][1];
        
        for (DatosVariables p : Datos) {
            double X = p.x1;
            double y = p.y;

            SumX += X;
            SumY += y;
            MultXY += X * y;
            MultX2 += X * X;
            MultX3 += X * X * X;
            MultX4 += X * X * X * X;
            MultXXY += X * X * y ;

        }

        for (int i = 0; i < Filas; i++) {
            MatrizDatos[i][0] = Datos.get(i).x;
            MatrizDatos[i][1] = Datos.get(i).x1;
            MatrizDatos[i][2] = Datos.get(i).y;
        }

        //ImprimirMatriz(MatrizDatos);

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

        //System.out.println("\nMatriz X: \n");
        //ImprimirMatriz(MatrizX);

        //Matriz Y
        MatrizY[0][0] = MultXXY;
        MatrizY[1][0] = MultXY;
        MatrizY[2][0] = SumY;

        //System.out.println("\nMatriz Y: \n");
        //ImprimirMatriz(MatrizY);


        double [][] TranspuestaX = new double[MatrizX[0].length][MatrizX.length];

        for (int i = 0; i < MatrizX.length; i++) {
            for (int j = 0; j < MatrizX[0].length; j++) {
                TranspuestaX[j][i] = MatrizX[i][j];
            }
        }

        //System.out.println("\nMatriz Transpuesta de X: ");
        //ImprimirMatriz(TranspuestaX);

        double[][] MultTransMX = MultMatrices(TranspuestaX, MatrizX);
        //System.out.println("\nMultiplicacion de matrices: ");
        //ImprimirMatriz(MultTransMX);

        double MatrizAumentada[][]= new double[MultTransMX.length][MultTransMX.length*2];
        double[][] MatrizExtra = new double[MultTransMX.length][MultTransMX.length];

        for (int i = 0; i < MultTransMX.length; i++) {
            MatrizExtra[i][i] = 1.0;
        }

        for (int i = 0; i < MultTransMX.length; i++) {
            for (int j = 0; j < MultTransMX.length; j++) {
                MatrizAumentada[i][j] = MultTransMX[i][j];
                MatrizAumentada[i][j + MultTransMX.length] = MatrizExtra[i][j];
            }
        }

        //System.out.println("\nMatriz Aumentada:\n");
        //ImprimirMatriz(MatrizAumentada);

        double MatrizInversa[][]=new double[MultTransMX.length][MultTransMX.length];

        for(int i=0; i < MatrizAumentada.length; i++){
            double Carreado = MatrizAumentada[i][i];

            for(int j=0; j < MatrizAumentada.length*2; j++){
                MatrizAumentada[i][j] /= Carreado;
            }

            for(int k=0; k < MatrizAumentada.length; k++){
                if(k!=i){
                    double FactorSoltante = MatrizAumentada[k][i];
                    for (int j=0; j < MatrizAumentada.length*2; j++){
                        MatrizAumentada[k][j] -= FactorSoltante * MatrizAumentada[i][j];
                    }

                }
            }
        }

        //System.out.println("\nMatriz Aumentada Gauss Jordan: \n");
        //ImprimirMatriz(MatrizAumentada);

        for (int i=0; i< MatrizAumentada.length; i++){
            for (int j=0; j < MatrizAumentada.length; j++){
                MatrizInversa[i][j] = MatrizAumentada[i][j + MultTransMX.length];
            }
        }

        //System.out.println("\nMatriz Inversa (X*X^T)^-1: \n");
        //ImprimirMatriz(MatrizInversa);

        //System.out.println("\nX^T*Y: \n");
        double [][] MultTransY = MultMatrices(TranspuestaX, MatrizY);
        //ImprimirMatriz(MultTransY);

        //System.out.println("\n(X^T * X)^-1) X^T * Y: \n");
        double [][] MultFinal = MultMatrices(MatrizInversa, MultTransY);
        //ImprimirMatriz(MultFinal);

        double B0 = MultFinal[0][0];
        double B1 = MultFinal[1][0];
        double B2 = MultFinal[2][0];
        double resultado = 0, x = 0;


        System.out.println("\nEcuacion: " + B0 + "X^2 + " + B1 + "X + " + B2);
        System.out.println("\nSimulacion: \n");
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


    public static void ImprimirMatriz(double[][] Matriz) {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                System.out.printf("%.15f\t", Matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static double[][] MultMatrices(double[][] PrMatriz, double[][] ScMatriz) {
        int FilasPrM = PrMatriz.length, ColumnsPrM = PrMatriz[0].length, FilasScM = ScMatriz.length, ColumnsScM = ScMatriz[0].length;

        if (ColumnsPrM != FilasScM) {
            throw new IllegalArgumentException("ERROR. DATOS NO SON VALIDOS.");
        }

        double[][] MultMatrizXTX = new double[FilasPrM][ColumnsScM];

        for (int i = 0; i < FilasPrM; i++) {
            for (int j = 0; j < ColumnsScM; j++) {
                double SumaTotal = 0;
                for (int k = 0; k < ColumnsPrM; k++) {
                    SumaTotal += PrMatriz[i][k] * ScMatriz[k][j];
                }

                MultMatrizXTX[i][j] = SumaTotal;
            }
        }
        return MultMatrizXTX;
    }
    
}
