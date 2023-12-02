package com.wenck.linearalgebra.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TODO: javadoc
 */
@Service
public class MatrixService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixService.class);
    public MatrixService() {}

    public Matrix addition(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            LOGGER.error("Matrix Addition -> Matrix Sizes don't match");
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

    public Matrix subtraction(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            LOGGER.error("Matrix Subtraction -> Matrix Sizes don't match");
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

    public Matrix scale(Matrix targetMatrix, double scalar) {
        double[][] scaleArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                scaleArray[row][column] = targetMatrix.getMatrix()[row][column] * scalar;
            }
        }

        return new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), scaleArray);
    }

    // todo: cry
//    public Matrix multiplication(Matrix targetMatrix, Matrix sourceMatrix) {
//        if (targetMatrix.getRows() != sourceMatrix.getCols()
//                || targetMatrix.getCols() != sourceMatrix.getRows()) {
//            throw new IllegalArgumentException();
//        }
//
//        double[][] multiplicationArray = new double[targetMatrix.getRows()][sourceMatrix.getCols()];
//        for (int row = 0; row < targetMatrix.getRows(); row++) {
//            for (int column = 0; column < sourceMatrix.getCols(); column++) {
//                for (int sourceRow = 0; sourceRow < sourceMatrix.getRows(); sourceRow++) {
//                    multiplicationArray[row][column] = targetMatrix.getMatrix()[row][column]
//                }
//            }
//        }
//    }

    public Matrix transpose(Matrix targetMatrix) {
        Matrix matrix = new Matrix(targetMatrix.getCols(), targetMatrix.getRows());
        double[][] transposeArray = new double[targetMatrix.getCols()][targetMatrix.getRows()];
        for (int column = 0; column < targetMatrix.getCols(); column++) {
            for (int row = 0; row < targetMatrix.getRows(); row++) {
                transposeArray[column][row] = targetMatrix.getMatrix()[row][column]; // oh god my head
            }
        }

        matrix.setMatrix(transposeArray);
        return matrix;
    }
}
