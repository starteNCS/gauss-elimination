package whs.fpr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Please enter the equation amount");
//        int equationCount = new Scanner(System.in).nextInt();
//	    var gauss = new GaussEliminationUserInput(equationCount);
//        gauss.getEquationFromInput();

        var gauss = new GaussEliminationUserInput(3);
        gauss.loadDebugMatrix();

        gauss.printMatrix();

        gauss.calculate();

        gauss.printMatrix();
    }
}