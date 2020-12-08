package put.io.testing.junit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class FailureOrErrorTest {

    @Test
    public void test1(){
        assertTrue(false);
    }

    @Test
    public void test2(){
        new Calculator().addPositiveNumbers(-1, 1);
    }

    @Test
    public void test3(){
        try{
            assertTrue(false);
        }
        catch (Throwable ex){
            ex.printStackTrace();
        }
    }


}
