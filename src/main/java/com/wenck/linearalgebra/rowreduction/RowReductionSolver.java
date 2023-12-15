package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import com.wenck.linearalgebra.matrix.MatrixService;

/**
 * Solver class for the Row Reduction Algorithm
 */
public class RowReductionSolver {

    private EchelonSolver echelonSolver;
    private MatrixService matrixService;
    private RowReductionService rowReductionService;

    public RowReductionSolver(EchelonSolver echelonSolver,
                              MatrixService matrixService,
                              RowReductionService rowReductionService) {
        this.echelonSolver = echelonSolver;
        this.matrixService = matrixService;
        this.rowReductionService = rowReductionService;
    }

    public Matrix rowReduction(Matrix matrix) {

        boolean[] zeroCol = matrixService.zeroColumn(matrix);

        matrix = moveZeroRowsBelow(matrix);

        // Step 1:
        // Begin with the leftmost nonzero column. This is a pivot column. The pivot position is at the top.
        int pivotColumn = stepOne(zeroCol);

        // Step 2:
        // Select a nonzero entry in the pivot column as a pivot.
        // If necessary, interchange rows to move this entry into the pivot position.
        int pivotRow = stepTwo(pivotColumn, matrix);

        // Step 2.5:
        // Divide the top row by the pivot, making the leading entry 1.
        if (matrix.getMatrix()[pivotRow][pivotColumn] != 1) {
            rowReductionService.scaling(pivotRow, (1 / matrix.getMatrix()[pivotRow][pivotColumn]), matrix);
        }

        // Step 3:
        // Use row replacement operations to create zeros in all positions below the pivot.
        matrix = stepThree(pivotColumn, matrix);

//        if (matrix.getRows() > 1 || matrix.getCols() > 1) {
//            double[][] smallArray = new double[matrix.getRows() - 1][matrix.getCols() - 1];
//            for (int row = 1; row < matrix.getRows(); row++) {
//                for (int column = 1; column < matrix.getCols(); column++) {
//                    smallArray[row - 1][column - 1] = matrix.getMatrix()[row][column];
//                }
//            }
//            Matrix smallMatrix = new Matrix(matrix.getRows() - 1,
//                    matrix.getCols() - 1,
//                    smallArray
//            );
//
//            smallMatrix = rowReduction(smallMatrix);
//
//
//            return smallMatrix;
//        } else {
//            return matrix;
//        }
        return matrix;
    }

    public int stepOne(boolean[] zeroCol) {
        int pivotColumn = -1;

        // Finds the first nonzero column
        for (int column = 0; column < zeroCol.length; column++) {
            if (zeroCol[column] == false) {
                pivotColumn = column;
                break;
            }
        }

        return pivotColumn;
    }

    public int stepTwo(int pivotColumn, Matrix matrix) {
        int pivotRow = 0;

        for (int r = 0; r < matrix.getRows(); r++) {
            if (matrix.getMatrix()[r][pivotColumn] != 0) {
                pivotRow = r;
                break;
            }
        }

        if (pivotRow != 0) {
            matrix = rowReductionService.interchange(pivotRow, 0, matrix);
            pivotRow = 0;
        }

        return pivotRow;
    }

    public Matrix stepThree(int pivotColumn, Matrix matrix) {
        for (int r = 1; r < matrix.getRows(); r++) {
            if (matrix.getMatrix()[r][pivotColumn] != 0) {     // If value in pivot column is nonzero
                matrix = rowReductionService.replacement(r, 0, -(matrix.getMatrix()[r][pivotColumn]), matrix);
            }
        }
        return matrix;
    }

    // todo: javadoc + private?
    public Matrix moveZeroRowsBelow(Matrix matrix) {
        Matrix returnMatrix = matrix;
        boolean[] zeroRow = matrixService.zeroRow(matrix);
        // Moves all nonzero rows above zero rows;
        for (int r = 0; r < matrix.getRows() - 1; r++) {
            if (zeroRow[r] == true && zeroRow[r + 1] == false) {
                int lowZero = r;
                for (int back = r - 1; back >= 0; back--) { // Searches for topmost zero row
                    if (zeroRow[back] == true) {
                        lowZero = back;
                    }
                }
                returnMatrix = rowReductionService.interchange((r+1), lowZero, matrix);

                // Updates zeroRow
                zeroRow[r+1] = true;
                zeroRow[lowZero] = false;
            }
        }
        return returnMatrix;
    }

    public boolean isZeroMatrix(int... zeroCol) {
        for (int column = 0; column < zeroCol.length; column++) {
            if (zeroCol[column] == 0) {
                return false;
            }
        }
        return true;
    }

//    private Matrix clearAboveEntries(int row, Matrix matrix) {
//        double[][] matrixArray = matrix.getMatrix();
//        int columnToClear = -1;
//
//        // Finds column to clear
//        for (int column = 0; column < matrix.getCols(); column++) {
//            if (matrixArray[row][column] == 1) {
//                columnToClear = column;
//                break;
//            }
//        }
//
//        // If there is no pivot in this row
//        if (columnToClear == -1) {
//            return matrix;
//        }
//
//        for (int rowToClear = row; rowToClear >= 0; rowToClear++) {
//            matrixArray[rowToClear][columnToClear] = 0;
//        }
//
//        return new Matrix(matrix.getRows(), matrix.getCols(), matrixArray);
//    }
}
