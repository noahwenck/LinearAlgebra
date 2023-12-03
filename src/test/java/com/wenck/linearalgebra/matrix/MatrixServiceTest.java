package com.wenck.linearalgebra.matrix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link MatrixService}
 */
@ExtendWith(MockitoExtension.class)
public class MatrixServiceTest {

    @InjectMocks
    private MatrixService matrixService;
    
    private static final Matrix IDENTITYMATRIX = new Matrix();

    @Test
    void testIdentityAddition() {
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {2.0, 0.0},
                        {0.0, 2.0}
                }
        );

        Matrix testMatrix = matrixService.addition(IDENTITYMATRIX, IDENTITYMATRIX);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testAdditionIllegalArgument() {
        Matrix wrongSizedMatrix = new Matrix(3, 3);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    matrixService.addition(IDENTITYMATRIX, wrongSizedMatrix);
                });
    }

    @Test
    void testIdentitySubtraction() {
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {0.0, 0.0},
                        {0.0, 0.0}
                }
        );

        Matrix testMatrix = matrixService.subtraction(IDENTITYMATRIX, IDENTITYMATRIX);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testSubtractionIllegalArgument() {
        Matrix wrongSizedMatrix = new Matrix(3, 3);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    matrixService.subtraction(IDENTITYMATRIX, wrongSizedMatrix);
                });
    }

    @Test
    void testIdentityScale() {
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {5.0, 0.0},
                        {0.0, 5.0}
                }
        );

        Matrix testMatrix = matrixService.scale(IDENTITYMATRIX, 5);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testMultiplicationIllegalArgument() {
        Matrix wrongSizedMatrix = new Matrix(3, 3);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    matrixService.multiplication(IDENTITYMATRIX, wrongSizedMatrix);
                });
    }

    @Test
    void testMultiplicationSameSize() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {1, 2},
                        {3, 4}
                });
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {7, 10},
                        {15, 22}
                });

        Matrix testMatrix = matrixService.multiplication(matrix, matrix);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testMultiplicationDifferentSize() {
        Matrix matrix1 = new Matrix(2,
                2,
                new double[][] {
                        {1, 2},
                        {3, 4}
                });
        Matrix matrix2 = new Matrix(2,
                3,
                new double[][] {
                        {5, 6, 7},
                        {8, 9, 10}
                });
        Matrix expectedMatrix = new Matrix(2,
                3,
                new double[][] {
                        {21, 24, 27},
                        {47, 54, 61}
                });

        Matrix testMatrix = matrixService.multiplication(matrix1, matrix2);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testTranspose() {
        Matrix inputMatrix = new Matrix(3,
                2,
                new double[][] {
                        {1, 2},
                        {3, 4},
                        {5, 6}
                });
        Matrix expectedMatrix = new Matrix(2,
                3,
                new double[][] {
                        {1, 3, 5},
                        {2, 4, 6},
                });

        Matrix testMatrix = matrixService.transpose(inputMatrix);
        assertTrue(testMatrix.equals(expectedMatrix));
    }
}
