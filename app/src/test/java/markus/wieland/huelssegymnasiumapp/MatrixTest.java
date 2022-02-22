package markus.wieland.huelssegymnasiumapp;

import org.junit.Test;

import markus.wieland.huelssegymnasiumapp.helper.Matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MatrixTest {

    private static final int HEIGHT = 12;
    private static final int WIDTH = 5;

    @Test
    public void testConstructor() {

        Matrix<Integer> integers = new Matrix<>(HEIGHT, WIDTH);
        assertEquals(HEIGHT, integers.getHeight());
        assertEquals(WIDTH, integers.getWidth());
    }

    @Test
    public void testGet() {

        Matrix<Integer> integers = new Matrix<>(HEIGHT, WIDTH);
        assertNull(integers.get(1, 2));
        integers.set(1,2, 3);
        assertEquals(new Integer(3), integers.get(1,2));
    }


}
