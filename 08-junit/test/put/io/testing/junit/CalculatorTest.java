package put.io.testing.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testMultiply(){
        assertEquals(
                calculator.multiply(2,4), 8
        );
    }

    @Test
    public void testAdd(){
        assertEquals(
                calculator.add(1, 2), 3
        );
    }

    @Test
    public void testErrorOnAddPositive(){
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.addPositiveNumbers(-1, 10)
        );
    }
}