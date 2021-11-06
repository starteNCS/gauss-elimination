package whs.fpr;

/**
 * Diese Klasse stellt eine A x = B Matrix dar. Reihen (Gleichungen) werden in double Arrays gespeichert.
 * Der letzte Wert einer Reihe stellt somit immer das Ergebnis der Gleichung dar.
 * @author Philipp Honsel
 * @version 1
 */
public class AxBMatrix {

    public final int count;
    public final int width;
    private final double[][] _matrix;

    public AxBMatrix(int n){
        count = n;
        width = count + 1;
        _matrix = new double[count][width];
    }

    /**
     * Erstellt automatisch eine AxBMatrix Instanz, welche bereits mit Gleichungen befüllt ist.
     * Es stehen mehrere Matrizen zur Auswahl
     * @param version Die Id der Matrix, welche erstellt werden soll
     * @return Instanz der Debugmatrix
     */
    public static AxBMatrix getDebugMatrix(int version){
        AxBMatrix matrix = new AxBMatrix(3);

        switch (version) {
            default:
            case 1:
                matrix.setRow(0, new double[]{1, 2, 3}, 2);
                matrix.setRow(1, new double[]{1, 1, 1}, 2);
                matrix.setRow(2, new double[]{3, 3, 1}, 0);
                break;
            case 2:
                matrix.setRow(0, new double[]{7, 3, -5}, -12);
                matrix.setRow(1, new double[]{-1, -2, 4}, 5);
                matrix.setRow(2, new double[]{-4, 1, -3}, 1);
                break;
        }

        return matrix;
    }

    /**
     * Überschreibt eine Gleichung in der Matrix.
     * @param index Spalte der Matrix, welche überschrieben werden soll
     * @param values Die Werte der A Matrix (Gleichung)
     * @param result Der Wert des B Vektor (Ergebnis)
     */
    public void setRow(int index, double[] values, double result){
        if(values.length != count){
            throw new IllegalArgumentException("Row needs to be of length " + count);
        }

        if(index >= count || index < 0){
            throw new IllegalArgumentException("Row needs to be between 0 and " + count);
        }

        double[] rowValues = new double[count + 1];
        System.arraycopy(values, 0, rowValues, 0, values.length);
        rowValues[count] = result;

        _matrix[index] = rowValues;
    }

    /**
     * Subtrahiert die übergebenen Werte von einer gewählten Spalte. Dabei werden jeweils die Werte von
     * x0 von x0, x1 von x1, ..., xn von xn subtrahiert.
     * @param index Reihe, welche von welcher subtrahiert werden soll
     * @param rowValues Werde, welche abgezogen werden sollen
     */
    public void subtractRow(int index, double[] rowValues){
        for(int i = 0; i <= count; i++){
            getRow(index)[i] = getRow(index)[i] - rowValues[i];
        }
    }

    /**
     * Vertauscht die Reihen mit den gegebenen Indizes
     * @param sourceIndex Von der Reihe
     * @param targetIndex In die Reihe
     */
    public void swapRow(int sourceIndex, int targetIndex){
        double[] targetCopy = _matrix[targetIndex];
        _matrix[targetIndex] = _matrix[sourceIndex];
        _matrix[sourceIndex] = targetCopy;
    }

    public double[] getRow(int index){
        return _matrix[index];
    }

    /**
     * Gibt die Matrix in der Konsole aus. Die Werte werden auf 2 Nachkommastellen gerundet.
     *
     */
    public void printMatrix(){
        System.out.println();
        System.out.printf("Gauss Matrix of Size: %d%n", count);
        for(double[] row : _matrix){
            for (double value : row){
                System.out.printf("%.2f ", value);
            }
            System.out.print("\n");
        }
    }
}
