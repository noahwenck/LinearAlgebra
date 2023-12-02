package com.wenck.linearalgebra.Matrix;

import org.springframework.stereotype.Service;

/**
 * TODO: javadoc
 */
@Service
public class MatrixService {

    public MatrixService() {}

    public Matrix addition(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            throw new IllegalArgumentException();
        }

        double[][] additionArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                additionArray[row][column] = targetMatrix.getMatrix()[row][column]
                                                + sourceMatrix.getMatrix()[row][column];
            }
        }

        Matrix matrix = new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), additionArray);
        return matrix;
    }

    public Matrix subtraction(Matrix targetMatrix, Matrix sourceMatrix) {
        if (targetMatrix.getRows() != sourceMatrix.getRows()
                || targetMatrix.getCols() != sourceMatrix.getCols()) {
            throw new IllegalArgumentException();
        }

        double[][] additionArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                additionArray[row][column] = targetMatrix.getMatrix()[row][column]
                        - sourceMatrix.getMatrix()[row][column];
            }
        }

        Matrix matrix = new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), additionArray);
        return matrix;
    }

    public Matrix scale(Matrix targetMatrix, double scalar) {
        double[][] scaleArray = new double[targetMatrix.getRows()][targetMatrix.getCols()];
        for (int row = 0; row < targetMatrix.getRows(); row++) {
            for (int column = 0; column < targetMatrix.getCols(); column++) {
                scaleArray[row][column] = targetMatrix.getMatrix()[row][column] * scalar;
            }
        }

        Matrix matrix = new Matrix(targetMatrix.getRows(), targetMatrix.getCols(), scaleArray);
        return matrix;
    }

}
