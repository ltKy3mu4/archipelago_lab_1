package function.impl;

import function.FunctionHandler;
import function.FunctionType;

@InjectableFunction(type = FunctionType.Quadratic)
public class QuadraticFunctionHandler implements FunctionHandler {
    private String strFun;

    /* -1*x*x+5 */
    @Override
    public double calculate(double x) {
        if (strFun == null || strFun.isEmpty())
            throw new RuntimeException("Function values is not set");
        String[] split = strFun.split("\\*x\\*x");

        if (split.length != 2)
            throw new RuntimeException("Function value is not correct : " +strFun );

        double k = Double.parseDouble(split[0]);
        double b = Double.parseDouble(split[1]);
        return k*x*x+b;
    }

    public void setFunction(String strFun) {
        this.strFun = strFun;
    }

}
