package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import com.wenck.linearalgebra.matrix.MatrixService;
import org.springframework.stereotype.Service;

/**
 * Solver class responsible for processes related to solving for Echelon and Reduced Echelon Form
 */
@Service
public class EchelonSolver {

    private MatrixService matrixService;
    public EchelonSolver(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    /**
     * Determines if a matrix is in Echelon Form
     *
     * @param matrix the matrix to be checked
     * @return true if matrix is in echelon form, false otherwise
     */
    public boolean isEchelon(Matrix matrix) {

        boolean[] zeroRow = matrixService.zeroRow(matrix);
        int[] pivotCol = matrixService.findPivots(matrix);

        // First: All nonzero rows are above any rows of all zeros
        for (int r = 0; r < matrix.getRows() - 1; r++) {
            if (zeroRow[r] == true && zeroRow[r + 1] == false) {
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

        int[] pivotCol = matrixService.findPivots(matrix);

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
}
