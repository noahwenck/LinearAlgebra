package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import com.wenck.linearalgebra.matrix.MatrixService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class)
public class RowReductionSolverTest {

    @InjectMocks
    private RowReductionSolver rowReductionSolver;
    @Mock
    private EchelonSolver echelonSolver;
    @Mock
    private MatrixService matrixService;
    @Mock
    private RowReductionService rowReductionService;

    // todo: this test seems worthless, was good to check to see if it works but idk if should keep
    @Test
    void testMoveZeroRowsBelow() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {0, 0},
                        {1, 0}
                });
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {1, 0},
                        {0, 0}
                });


        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(rowReductionService).interchange(anyInt(), anyInt(), any(Matrix.class));

        Matrix testMatrix = rowReductionSolver.moveZeroRowsBelow(matrix);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testStepOne() {
        boolean[] zeroCol = {true, false};

        int pivotColumn = rowReductionSolver.stepOne(zeroCol);
        assertEquals(1, pivotColumn);
    }

    @Test
    void testStepTwo() {
        int pivotColumn = 1;
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {0, 0},
                        {0, 1}
                });

        int pivotRow = rowReductionSolver.stepTwo(pivotColumn, matrix);
        assertEquals(0, pivotRow);
    }

    @Test
    void testStepTwoAgain() {
        int pivotColumn = 2;
        Matrix matrix = new Matrix(3,
                3,
                new double[][]{
                        {0, 0, 1},
                        {0, 0, 0},
                        {0, 0, 0}
                });

        int pivotRow = rowReductionSolver.stepTwo(pivotColumn, matrix);
        assertEquals(0, pivotRow);
    }

    @Test
    void testStepThree() {
        int pivotColumn = 0;
        Matrix matrix = new Matrix(3,
                3,
                new double[][] {
                        {1, 2, 3},
                        {2, 5, 7},
                        {3, 8, 11}
                });
        Matrix expectedMatrix = new Matrix(3,
                3,
                new double[][] {
                        {1, 2, 3},
                        {0, 1, 1},
                        {0, 2, 2}
                });

        doCallRealMethod().when(rowReductionService).replacement(anyInt(), anyInt(), anyDouble(), any(Matrix.class));

        Matrix testMatrix = rowReductionSolver.stepThree(pivotColumn, matrix);
        assertTrue(testMatrix.equals(expectedMatrix));
    }

    @Test
    void testOneIteration() {
        Matrix matrix = new Matrix(3,
                3,
                new double[][] {
                        {0, 0, 0},
                        {0, 2, 1},
                        {0, 4, 3}
                });
        Matrix expectedMatrix = new Matrix(3,
                3,
                new double[][] {
                        {0, 1, 0.5},
                        {0, 0, 1},
                        {0, 0, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(rowReductionService).interchange(anyInt(), anyInt(), any(Matrix.class));
        doCallRealMethod().when(rowReductionService).replacement(anyInt(), anyInt(), anyDouble(), any(Matrix.class));
        doCallRealMethod().when(rowReductionService).scaling(anyInt(), anyDouble(), any(Matrix.class));

        Matrix testMatrix = rowReductionSolver.rowReduction(matrix);
        assertTrue(testMatrix.equals(expectedMatrix));
    }
}
