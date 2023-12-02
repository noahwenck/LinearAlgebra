package com.wenck.linearalgebra.RowReduction;

import com.wenck.linearalgebra.Matrix.Matrix;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for backend processes related to Row Reduction
 */

@Service
public class RowReductionService {

    public RowReductionService() {}

    // TODO: javadoc
    public void replacement(int targetRow, int sourceRow, double scalar, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        for (int column = 0; column < matrix.getCols(); column++) {
            arrayRepresentation[targetRow][column] =
                    (arrayRepresentation[targetRow][column]
                            + arrayRepresentation[sourceRow][column] * scalar);
        }
        matrix.setMatrix(arrayRepresentation);
    }

    // TODO: javadoc
    public void interchange(int targetRow, int sourceRow, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        double[] temp = arrayRepresentation[targetRow];
        arrayRepresentation[targetRow] = arrayRepresentation[sourceRow];
        arrayRepresentation[sourceRow] = temp;
        matrix.setMatrix(arrayRepresentation);
    }

    // TODO: javadoc
    public void scaling(int targetRow, double scalar, Matrix matrix) {
        double[][] arrayRepresentation = matrix.getMatrix();
        for (int column = 0; column < matrix.getCols(); column++) {
            arrayRepresentation[targetRow][column] = arrayRepresentation[targetRow][column] * scalar;
        }
        matrix.setMatrix(arrayRepresentation);
    }
}
