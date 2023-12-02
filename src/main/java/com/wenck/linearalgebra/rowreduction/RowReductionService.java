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

    /**
     * Determines if a matrix is in Echelon Form
     *
     * @param matrix the matrix to be checked
     * @return true if matrix is in echelon form, false otherwise
     */
    public boolean isEchelon(Matrix matrix) {

        int[] zeroRow = zeroRow(matrix);
        int[] pivotCol = findPivots(matrix);

        // First: All nonzero rows are above any rows of all zeros
        for (int r = 0; r < matrix.getRows() - 1; r++) {
            if (zeroRow[r] == 1 && zeroRow[r + 1] == 0) {
                return false;
            }
        }

        // Second: Each leading entry of a row is in a column to the right
        //         of the leading entry of the row above it
        for (int r = 0; r < matrix.getRows() - 1; r++) {
            if (pivotCol[r] >= pivotCol[r + 1]) {
                return false;
            }
        }

        // Third: All entries in a column below a leading entry are zeroes
        for (int r = 0; r < matrix.getRows(); r++) {
            for (int c = 0; c < pivotCol[r]; c++) {
                if (matrix.getMatrix()[r][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determines if a matrix is in Reduced Echelon Form
     *
     * @param matrix the matrix to be checked
     * @return true if matrix is in reduced echelon form, false otherwise
     */
    public boolean isReducedEchelon(Matrix matrix) {

        if (!isEchelon(matrix)) {
            return false;
        }

        int[] pivotCol = findPivots(matrix);

        // Fourth: The leading entry in each nonzero row is 1
        for (int r = 0; r < matrix.getRows(); r++) {
            if (matrix.getMatrix()[r][pivotCol[r]] != 1) {
                return false;
            }
        }

        // Fifth: Each leading 1 is the only nonzero column in its column
        for (int r = 0; r < matrix.getRows(); r++) {
            for (int r2 = r - 1; r2 >= 0; r2--) {
                if (matrix.getMatrix()[r2][pivotCol[r]] != 0) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Determines what column a row's pivot is in
     *
     * For Example: the identity matrix would return the array -> [0, 1]
     *
     * @param matrix inputted matrix
     * @return array that indicates what column a row's pivot is in
     */
    private int[] findPivots(Matrix matrix) {
        int[] ret = new int[matrix.getRows()];
        for (int r = 0; r < matrix.getRows(); r++) {
            ret[r] = 0;
        }
        for (int r = 0; r < matrix.getRows(); r++) {
            for (int c = 0; c < matrix.getCols(); c++) {
                if (matrix.getMatrix()[r][c] != 0) {    // Determines pivot of each row
                    ret[r] = c;
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Creates an array that represents if rows of this matrix are zero rows
     *
     * @param matrix the matrix to be checked
     * @return an array that indicates which rows are zero rows
     */
    private int[] zeroRow(Matrix matrix) {
        int[] ret = new int[matrix.getRows()];
        int zeroCount = 0;

        for (int r = 0; r < matrix.getRows(); r++) {
            for (int c = 0; c < matrix.getCols(); c++) {
                if (matrix.getMatrix()[r][c] == 0) {    // Checks if each value in column is zero
                    zeroCount++;
                }
            } if (zeroCount == matrix.getCols()) {    // Marks Zero Row
                ret[r] = 1;
            } else {
                ret[r] = 0;
            }
            zeroCount = 0;                      // Revert Zero Count
        }

        return ret;
    }

    // TODO: verify use
    /**
     * Creates an array that represents if columns of this matrix are zero columns
     *
     * @param matrix the matrix to be checked
     * @return an array that indicates which columns are zero columns
     */
    private int[] zeroColumn(Matrix matrix) {
        int[] ret = new int[matrix.getCols()];
        int zeroCount = 0;

        for (int c = 0; c < matrix.getCols(); c++) {
            for (int r = 0; r < matrix.getRows(); r++) {
                if (matrix.getMatrix()[r][c] == 0) {    // Checks if each value in Row is zero
                    zeroCount++;
                }
            } if (zeroCount == matrix.getRows()) {  // Marks Zero Column
                ret[c] = 1;
            } else {
                ret[c] = 0;
            }
            zeroCount = 0;                    // Revert Zero Count
        }

        return ret;
    }
}
