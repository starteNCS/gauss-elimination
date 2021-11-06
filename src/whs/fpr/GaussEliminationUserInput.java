package whs.fpr;

import java.util.Scanner;

public class GaussEliminationUserInput extends GaussElimination{
    private final Scanner _scanner;

    public GaussEliminationUserInput(int n) {
        super(n);
        _scanner = new Scanner(System.in);
    }

    public void getEquationFromInput(){
        for (int i = 0; i < _count; i++){
            System.out.println("Please enter the equation (" + i + "):");
            String[] equationValues = _scanner.nextLine().split(" ");

            if(equationValues.length != _count + 1){
                throw new IllegalArgumentException("Illegal amount of values. Should be " + (_count + 1));
            }

            int equationValueIndex = 0;
            for(var value : equationValues){
                _matrix[i][equationValueIndex] = Double.parseDouble(value);
                equationValueIndex += 1;
            }
        }
    }

    public void printMatrix(){
        System.out.println();
        System.out.printf("Gauss Matrix of Size: %d%n", _count);
        for(double[] row : _matrix){
            for (double value : row){
                System.out.printf("%.2f ", value);
            }
            System.out.print("\n");
        }
    }
}
