package function.impl;

import function.FunctionHandler;
import function.FunctionType;

@InjectableFunction(type = FunctionType.Sinusoidal)
public class SinusoidalFunction implements FunctionHandler {
    private String strFun;

    @Override
    public double calculate(double x) {
        if (!strFun.equals("sin(x)"))
            throw new RuntimeException("sinusoidal function can be only sin(x)");

        return Math.sin(x);

    }

    @Override
    public void setFunction(String strFun) {
        this.strFun = strFun;
    }
}
