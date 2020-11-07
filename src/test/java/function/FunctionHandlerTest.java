package function;

import function.impl.LinearFunctionHandler;
import function.impl.QuadraticFunctionHandler;
import function.impl.SinusoidalFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionHandlerTest {

    @Test
    public void linearFunctionTest(){
        LinearFunctionHandler l = new LinearFunctionHandler();
        l.setFunction("2*x+2");
        double res = l.calculate(2);
        Assertions.assertEquals(6, res);

    }


    @Test
    public void qudroFunctionTest(){
        QuadraticFunctionHandler l = new QuadraticFunctionHandler();
        l.setFunction("-1*x*x+5");
        double res = l.calculate(2);
        Assertions.assertEquals(1, res);

    }

    @Test
    public void sinFunctionTest(){
        SinusoidalFunction l = new SinusoidalFunction();
        l.setFunction("sin(x)");
        double res = l.calculate(1);
        Assertions.assertTrue(res>0.8);
        Assertions.assertTrue(res<0.9);
        System.out.println(res);

    }

}