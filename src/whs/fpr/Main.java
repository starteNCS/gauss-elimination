package whs.fpr;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AxBMatrix matrix;
        System.out.println("Please enter the equation amount (or dx for a debug matrix):");
        String equationCount = new Scanner(System.in).nextLine();
        if(equationCount.startsWith("d")){
            matrix = AxBMatrix.getDebugMatrix(equationCount.charAt(1));
        }else {
            matrix = getMatrixFromInput(Integer.parseInt(equationCount));
        }

        double[] result = GaussElimination.solve(matrix);
        matrix.printMatrix();
        printResult(result);
    }

    public static AxBMatrix getMatrixFromInput(int count){
        var scanner = new Scanner(System.in);
        var matrix = new AxBMatrix(count);
        for (int i = 0; i < count; i++){
            System.out.println("Please enter the equation (" + i + "):");
            String[] equationValues = scanner.nextLine().split(" ");

            if(equationValues.length != count + 1){
                throw new IllegalArgumentException("Illegal amount of values. Should be " + (count + 1));
            }

            double[] values = Arrays.stream(equationValues).limit(count).mapToDouble(Double::parseDouble).toArray();

            matrix.setRow(i, values, Double.parseDouble(equationValues[equationValues.length - 1]));
        }

        return matrix;
    }

    public static void printResult(double[] result){
        System.out.println("\nResult of Gauss Algorithm:");
        for (int i = 0; i < result.length; i++){
            System.out.printf("x%d = %.2f, ", i, result[i]);
        }
        System.out.println();
    }
}