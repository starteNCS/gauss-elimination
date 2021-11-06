package whs.fpr;


/**
 * Diese Klasse stellt alle Methoden bereit, welche benötigt werden, um eine AxBMatrix zu lösen.
 * @author Philipp Honsel
 * @version 1
 */
public class GaussElimination {

    /**
     * Berechnet aus einer beliebigen Matrix die Echelon Form. Diese Form hat in einer Ecke der Matrix nur Nullen.
     * Die Ecke ist beliebig, wird in dieser Implementierung allerdings unten links sein.
     * @param matrix Matrix, für welche die Echelon Form errechnet werden soll
     * @return Matrix in Echelon Form
     */
    public static AxBMatrix toEchelonForm(AxBMatrix matrix){
        for(int currentRow = 0; currentRow < matrix.count - 1; currentRow++){
            int bestDiagonalRow = findOptimalPivotRow(matrix, currentRow + 1, /*row == diagonalIndex*/ currentRow);
            if(bestDiagonalRow > currentRow){
                matrix.swapRow(currentRow, bestDiagonalRow);
            }

            for(int rowToEliminateUnderDiagonal = currentRow + 1; rowToEliminateUnderDiagonal < matrix.count; rowToEliminateUnderDiagonal++){
                double multiplicator = findMultiplicator(matrix, currentRow, rowToEliminateUnderDiagonal, currentRow);
                double[] multipliedRow = multiplyRow(matrix, currentRow, multiplicator);
                matrix.subtractRow(rowToEliminateUnderDiagonal, multipliedRow);
            }
        }

        return matrix;
    }

    /**
     * Errechnet aus einer Matrix in Echelon Form die Lösung der Matrix
      * @param matrix Matrix in Echelon Form
     * @return Array mit der Lösung der Matrix. Dabei ist Index 0 die Lösung für x0 etc.
     */
    public static double[] solveFromEchelonForm(AxBMatrix matrix){
        if(!isEchelonForm(matrix)){
            throw new RuntimeException("Cannot calculate from echelon form, if the matrix is not in echelon form");
        }

        double[] results = new double[matrix.count];
        for(int currentRow = matrix.count - 1; currentRow >= 0; currentRow--){
            double currentValue = matrix.getRow(currentRow)[currentRow];
            double currentResult = matrix.getRow(currentRow)[matrix.count];

            for(int i = 0; i < matrix.count - currentRow - 1; i++){
                int currentResultIndex = matrix.count - 1 -i;
                currentResult -= results[currentResultIndex] * matrix.getRow(currentRow)[currentResultIndex];
            }

            results[currentRow] = currentResult / currentValue;
        }

        return results;
    }

    /**
     * Löst eine Gleichung in Matrix Form. Dabei ist es egal ob diese bereits in Echelon Form vorliegt.
     * @param matrix Matrix welche gelöst werden soll
     * @return Array mit der Lösung der Matrix. Dabei ist Index 0 die Lösung für x0 etc.
     */
    public static double[] solve(AxBMatrix matrix) {
        if(isEchelonForm(matrix)){
            return solveFromEchelonForm(matrix);
        }

        return solveFromEchelonForm(toEchelonForm(matrix));
    }

    /**
     * Überprüft ob eine Matrx in Echelon Form vorliegt. Diese Methode erkennt nur die untere linke Ecke.
     * @param matrix Matrix welche überprüft werden soll
     * @return true wenn Matrix in Echelon Form vorliegt.
     */
    public static boolean isEchelonForm(AxBMatrix matrix){
        for(int i = 0; i < matrix.count; i++){
            double[] row = matrix.getRow(i);
            for(int o = 0; o < i; o++){
                if(row[o] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    private static double findMultiplicator(AxBMatrix matrix, int sourceIndex, int targetIndex, int diagonalIndex){
        double source = matrix.getRow(sourceIndex)[diagonalIndex];
        double target = matrix.getRow(targetIndex)[diagonalIndex];

        return target / source;
    }

    private static double[] multiplyRow(AxBMatrix matrix, int index, double by){
        double[] results = new double[matrix.count + 1];
        int currentIndex = 0;
        for(double value : matrix.getRow(index)){
            results[currentIndex] = value * by;
            currentIndex += 1;
        }

        return results;
    }

    private static int findOptimalPivotRow(AxBMatrix matrix, int startRowIndex, int diagonalIndex){
        double currentHighestValue = matrix.getRow(startRowIndex)[diagonalIndex];
        int optimalRow = startRowIndex;
        for (int i = startRowIndex; i < matrix.count; i++){
            if(matrix.getRow(i)[diagonalIndex] > currentHighestValue){
                currentHighestValue = matrix.getRow(i)[diagonalIndex];
                optimalRow = i;
            }
        }

        return optimalRow;
    }

}
