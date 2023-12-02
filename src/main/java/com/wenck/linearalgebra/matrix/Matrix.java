package com.wenck.linearalgebra.matrix;

/**
 * TODO: add javadoc
 */
public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;

    /**
     * Creates a 2x2 Identity Matrix
     */
    public Matrix() {
        this.rows = 2;
        this.cols = 2;
        this.matrix = new double[][] {
                {1, 0},
                {0, 1}
        };
    }

    /**
     * Creates a nxm Identitiy Matrix, where n is the number of rows and m is the number of columns
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];

        int i = 1;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                this.matrix[row][column] = i;
                if (i == 1) { // lmao
                    i = 0;
                } else {
                    i = 1;
                }
            }
        }
    }

    /**
     * Creates a nxm Matrix from the provided 2d array, where n is the number of rows and m is the number of columns
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param matrix 2d array representation of the matrix
     */
    public Matrix(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = matrix; // TODO: use copy instead of access to internal matrix
    }

    /**
     * Returns the number of rows of the matrix
     *
     * @return number of rows of the matrix
     */
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Returns the number of columns of the matrix
     *
     * @return number of columns of the matrix
     */
    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Returns the 2d array representation of the matrix
     *
     * @return 2d array representation of the matrix
     */
    public double[][] getMatrix() {
        return matrix; // TODO: use copy instead of access to internal matrix
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix; // TODO: use copy instead of access to internal matrix
    }

    public boolean equals(Matrix matrix) {
        if (getRows() != matrix.getRows() || getCols() != matrix.getCols()) {
            return false;
        }
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getCols(); column++) {
                if (getMatrix()[row][column] != matrix.getMatrix()[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }
}
