package com.wenck.linearalgebra.rowreduction;

import com.wenck.linearalgebra.matrix.Matrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link RowReductionService}
 */
@ExtendWith(MockitoExtension.class)
public class RowReductionServiceTest {

    @InjectMocks
    private RowReductionService rowReductionService;

    @Test
    void testIdentityReplacement() {
        Matrix matrix = new Matrix();
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {1.0, -1.0},
                        {0.0, 1.0}
                }
        );

        rowReductionService.replacement(0, 1, -1, matrix);
        assertTrue(matrix.equals(expectedMatrix));
    }

    @Test
    void testIdentityInterchange() {
        Matrix matrix = new Matrix();
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {0.0, 1.0},
                        {1.0, 0.0}
                }
        ); // Flipped 2x2 Identity Matrix

        rowReductionService.interchange(0, 1, matrix);
        assertTrue(matrix.equals(expectedMatrix));
    }

    @Test
    void testIdentityScaling() {
        Matrix matrix = new Matrix();
        Matrix expectedMatrix = new Matrix(2,
                2,
                new double[][] {
                        {2.0, 0.0},
                        {0.0, -2.0}
                }
        );

        rowReductionService.scaling(0, 2, matrix);
        rowReductionService.scaling(1, -2, matrix);
        assertTrue(matrix.equals(expectedMatrix));
    }
}
