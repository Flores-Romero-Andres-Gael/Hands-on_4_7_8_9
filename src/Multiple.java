// Flores Romero Andres Gael
// Multiple Linear Regression

import java.util.ArrayList;

public class Multiple{

    public static void main(String[] args) {
        int a = 1;

        //Cambiar DataSets
        if (a == 1){
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Datos50SUMult());
            OPERATIONS(Datos, a);
        }else {
            ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.Chemical());
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
        double [][] TranspuestaX = new double[MatrizX[0].length][MatrizX.length];

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
        ImprimirMatriz(MatrizX);
        System.out.println("\nMatriz Y: ");
        ImprimirMatriz(MatrizY);

        for (int i = 0; i < Filas; i++) {
            for (int j = 0; j < MatrizX[0].length; j++) {
                TranspuestaX[j][i] = MatrizX[i][j];
            }
        }

        System.out.println("\nMatriz Transpuesta de X: ");
        ImprimirMatriz(TranspuestaX);

        System.out.println("\nMultiplicacion de matrices: ");
        double[][] MultTransMX = MultMatrices(TranspuestaX, MatrizX);
        ImprimirMatriz(MultTransMX);

        //Calcular la matriz Aumentada mediante Gauss Jordan

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

        System.out.println("\nMatriz Aumentada:\n");
        ImprimirMatriz(MatrizAumentada);

        //Calcular la matriz Inversa, tomando los valores dentro de la MatrizAumentada
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

        System.out.println("\nMatriz Aumentada Gauss Jordan: \n");
        ImprimirMatriz(MatrizAumentada);

        //Se pasan los valores de la Matriz Aumentada sobrante a la Matriz Inversa
        for (int i=0; i< MatrizAumentada.length; i++){
            for (int j=0; j < MatrizAumentada.length; j++){
                MatrizInversa[i][j] = MatrizAumentada[i][j + MultTransMX.length];
            }
        }

        System.out.println("\nMatriz Inversa (X*X^T)^-1: \n");
        ImprimirMatriz(MatrizInversa);

        System.out.println("\nX^T*Y: \n");
        double [][] MultTransY = MultMatrices(TranspuestaX, MatrizY);
        ImprimirMatriz(MultTransY);

        System.out.println("\n(X^T * X)^-1) X^T * Y: \n");
        double [][] MultFinal = MultMatrices(MatrizInversa, MultTransY);
        ImprimirMatriz(MultFinal);

        if (MultFinal.length == 4) {
            double B0 = MultFinal[0][0];
            double B1 = MultFinal[1][0];
            double B2 = MultFinal[2][0];
            double B3 = MultFinal[3][0];

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