package com.wenck.linearalgebra.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
