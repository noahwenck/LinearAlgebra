package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for backend processes related to Row Reduction
 */

@Service
public class RowReductionService {

    public RowReductionService() {}

    /**
     * Performs the replacement operation
     *
     * @param targetRow row to be replaced
     * @param sourceRow row to be scaled, then added onto targetRow
     * @param scalar scale for sourceRow
     * @param matrix inputted matrix
     */
    public void replacement(int targetRow, int sourceRow, double scalar, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        for (int column = 0; column < matrix.getCols(); column++) {
            arrayRepresentation[targetRow][column] =
                    (arrayRepresentation[targetRow][column]
                            + arrayRepresentation[sourceRow][column] * scalar);
        }
        matrix.setMatrix(arrayRepresentation);
    }

    /**
     * Performs the interchange operation
     *
     * @param targetRow row to be swapped with sourceRow
     * @param sourceRow row to be swapped with targetRow
     * @param matrix inputted matrix
     */
    public void interchange(int targetRow, int sourceRow, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        double[] temp = arrayRepresentation[targetRow];
        arrayRepresentation[targetRow] = arrayRepresentation[sourceRow];
        arrayRepresentation[sourceRow] = temp;
        matrix.setMatrix(arrayRepresentation);
    }

    /**
     * Performs the scaling operation
     *
     * @param targetRow row to be scaled
     * @param scalar scale for targetRow
     * @param matrix inputted matrix
     */
    public void scaling(int targetRow, double scalar, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        for (int column = 0; column < matrix.getCols(); column++) {
            arrayRepresentation[targetRow][column] = arrayRepresentation[targetRow][column] * scalar;
        }
        matrix.setMatrix(arrayRepresentation);
    }
}
