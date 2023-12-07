package com.wenck.linearalgebra.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// todo: maybe turn service classes into static util? remove dependency Injection?
/**
 * Service classes for backend processes relating to matrices
 */
@Service
public class MatrixService {

    private static final Logger LOG = LoggerFactory.getLogger(MatrixService.class);
    public MatrixService() {}

    /**
     * Performs an addition operation for two matrices
     *
     * @param targetMatrix
     * @param sourceMatrix
     * @return matrix sum of the inputted matrices
     */
    public Matrix addition(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            LOG.error("Matrix Addition -> Matrix Sizes don't match");
            throw new IllegalArgumentException();
        }

        double[][] additionArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                additionArray[row][column] = targetMatrix.getMatrix()[row][column]
                                                + sourceMatrix.getMatrix()[row][column];
            }
        }

        return new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), additionArray);
    }

    /**
     * Performs a subtraction operation for two matrices
     *
     * @param targetMatrix
     * @param sourceMatrix
     * @return matrix difference of the inputted matrices
     */
    public Matrix subtraction(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            LOG.error("Matrix Subtraction -> Matrix Sizes don't match");
            throw new IllegalArgumentException();
        }

        double[][] additionArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                additionArray[row][column] = targetMatrix.getMatrix()[row][column]
                                                - sourceMatrix.getMatrix()[row][column];
            }
        }

        return new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), additionArray);
    }

    /**
     * Performs a scale operation on a matrix
     *
     * @param targetMatrix
     * @param scalar
     * @return scaled matrix
     */
    public Matrix scale(Matrix targetMatrix, double scalar) {
        double[][] scaleArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                scaleArray[row][column] = targetMatrix.getMatrix()[row][column] * scalar;
            }
        }

        return new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), scaleArray);
    }

    /**
     * Performs a multiplicaiton operation for two matrices
     *
     * @param targetMatrix matrix A
     * @param sourceMatrix matrix B
     * @return matrix product of the inputted matrices
     */
    public Matrix multiplication(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getCols() != sourceMatrix.getRows()) {
            throw new IllegalArgumentException();
        }

        double[][] multiplicationArray = new double[targetMatrix.getRows()][sourceMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < sourceMatrix.getCols(); column++) {
                // This is my solution to the odd nature of matrix multiplication,
                // don't dwell on it, just accept it and move on
                for (int innerValue = 0; innerValue < sourceMatrix.getRows(); innerValue++) {
                    multiplicationArray[row][column] += targetMatrix.getMatrix()[row][innerValue]
                                                            * sourceMatrix.getMatrix()[innerValue][column];
                }
            }
        }

        return new Matrix(targetMatrix.getRows(), sourceMatrix.getCols(), multiplicationArray);
    }

    /**
     * Performs a transpose operation on a matrix
     *
     * @param targetMatrix
     * @return transpose of the inputted matrix
     */
    public Matrix transpose(Matrix targetMatrix) {
        double[][] transposeArray = new double[targetMatrix.getCols()][targetMatrix.getRows()];
        for (int column = 0; column < targetMatrix.getCols(); column++) {
            for (int row = 0; row < targetMatrix.getRows(); row++) {
                transposeArray[column][row] = targetMatrix.getMatrix()[row][column]; // oh god my head
            }
        }

        return new Matrix(targetMatrix.getCols(), targetMatrix.getRows(), transposeArray);
    }

    // todo: why column a row's pivot and not row a column's pivot?
    /**
     * Determines what column a row's pivot is in
     *
     * For Example: the identity matrix would return the array -> [0, 1]
     *
     * @param matrix inputted matrix
     * @return array that indicates what column a row's pivot is in
     */
    public int[] findPivots(Matrix matrix) {
        int[] ret = new int[matrix.getRows()];
        for (int r = 0; r < matrix.getRows(); r++) {
            ret[r] = -1;
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
    public boolean[] zeroRow(Matrix matrix) {
        boolean[] zeroRow = new boolean[matrix.getRows()];
        for (int row = 0; row < matrix.getRows(); row++) {
            zeroRow[row] = true;    // have to initialize everything now with how I've changed it
        }

        for (int row = 0; row < matrix.getRows(); row++) {
            for (int column = 0; column < matrix.getCols(); column++) {
                if (matrix.getMatrix()[row][column] != 0) {
                    zeroRow[row] = false;
                    break;
                }
            }
        }

        return zeroRow;
    }

    // TODO: verify use
    /**
     * Creates an array that represents if columns of this matrix are zero columns
     *
     * @param matrix the matrix to be checked
     * @return an array that indicates which columns are zero columns
     */
    public boolean[] zeroColumn(Matrix matrix) {
        boolean[] zeroCol = new boolean[matrix.getCols()];
        for (int column = 0; column < matrix.getRows(); column++) {
            zeroCol[column] = true;    // have to initialize everything now with how I've changed it
        }

        for (int column = 0; column < matrix.getCols(); column++) {
            for (int row = 0; row < matrix.getRows(); row++) {
                if (matrix.getMatrix()[row][column] != 0) {
                    zeroCol[column] = false;
                    break;
                }
            }
        }

        return zeroCol;
    }
}
