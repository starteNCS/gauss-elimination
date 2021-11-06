package whs.fpr;

public class GaussElimination {
    protected final int _count;
    protected final double[][] _matrix;

    public GaussElimination(int n){
        _matrix = new double[n][n + 1];
        _count = n;
    }

    public void loadDebugMatrix(){
        if(_count != 3){
            throw new RuntimeException("Could not load debugger matrix for matrices other than 3x3");
        }
        _matrix[0] = new double[]{1, 2, 3, 2};
        _matrix[1] = new double[]{1, 1, 1, 2};
        _matrix[2] = new double[]{3, 3, 1, 0};

        // result should be x1 = 5, x2 = -6, x3 = 3
    }

    public void calculate(){
        for(int currentRow = 0; currentRow < _count; currentRow++){
            int bestDiagonalRow = findOptimalPivotRow(/*row == diagonalIndex*/ currentRow);
            if(bestDiagonalRow > currentRow){
                swapRow(currentRow, bestDiagonalRow);
            }

            for(int rowToEliminateUnderDiagonal = currentRow + 1; rowToEliminateUnderDiagonal < _count; rowToEliminateUnderDiagonal++){
                double multiplicator = findMultiplicator(currentRow, rowToEliminateUnderDiagonal, currentRow);
                double[] multipliedRow = multiplyRow(currentRow, multiplicator);
                addRow(rowToEliminateUnderDiagonal, multipliedRow);
            }
        }
    }

    private double findMultiplicator(int sourceIndex, int targetIndex, int diagonalIndex){
        double source = _matrix[sourceIndex][diagonalIndex];
        double target = _matrix[targetIndex][diagonalIndex];

        return target / source;
    }

    private double[] multiplyRow(int rowIndex, double by){
        double[] results = new double[_count + 1];
        int currentIndex = 0;
        for(double value : _matrix[rowIndex]){
            results[currentIndex] = value * by;
            currentIndex += 1;
        }

        return results;
    }

    private void addRow(int toAddRowIndex, double[] values){
        for(int i = 0; i < _count; i++){
            _matrix[toAddRowIndex][i] = _matrix[toAddRowIndex][i] * values[i];
        }
    }

    private int findOptimalPivotRow(int diagonalIndex){
        double currentHighestValue = _matrix[0][diagonalIndex];
        int optimalRow = 0;
        for (int i = 1; i < _count; i++){
            if(_matrix[i][diagonalIndex] > currentHighestValue){
                currentHighestValue = _matrix[i][diagonalIndex];
                optimalRow = i;
            }
        }

        return optimalRow;
    }

    private void swapRow(int sourceIndex, int targetIndex){
        double[] targetCopy = _matrix[targetIndex];
        _matrix[targetIndex] = _matrix[sourceIndex];
        _matrix[sourceIndex] = targetCopy;
    }

}
