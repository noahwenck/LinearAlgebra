package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import com.wenck.linearalgebra.matrix.MatrixService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class)
public class EchelonSolverTest {

    @InjectMocks
    private EchelonSolver echelonSolver;
    @Mock
    MatrixService matrixService;

    @Test
    void testIdentityIsEchelon() {
        Matrix matrix = new Matrix();

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertTrue(echelonSolver.isEchelon(matrix));
    }

    @Test
    void testIsEchelonFirstConditionFalse() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {0, 0},
                        {1, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertFalse(echelonSolver.isEchelon(matrix));
    }

    @Test
    void testIsEchelonSecondConditionFalse() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {0, 1},
                        {1, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertFalse(echelonSolver.isEchelon(matrix));
    }

    @Test
    void testIsEchelonThirdConditionFalse() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {1, 0},
                        {1, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertFalse(echelonSolver.isEchelon(matrix));
    }

    @Test
    void testIdentityIsReducedEchelon() {
        Matrix matrix = new Matrix();

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertTrue(echelonSolver.isReducedEchelon(matrix));
    }

    @Test
    void testIsReducedEchelonFourthConditionFalse() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {2, 0},
                        {0, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertFalse(echelonSolver.isReducedEchelon(matrix));
    }

    @Test
    void testIsReducedEchelonFifthConditionFalse() {
        Matrix matrix = new Matrix(2,
                2,
                new double[][] {
                        {1, 2},
                        {0, 0}
                });

        doCallRealMethod().when(matrixService).zeroRow(any(Matrix.class));
        doCallRealMethod().when(matrixService).findPivots(any(Matrix.class));

        assertFalse(echelonSolver.isReducedEchelon(matrix));
    }
}
