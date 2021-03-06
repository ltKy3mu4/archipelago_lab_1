package function.impl;

import function.FunctionHandler;
import function.FunctionType;

@InjectableFunction(type = FunctionType.Linear)
public class LinearFunctionHandler implements FunctionHandler {
    private String strFun;

    public void setFunction(String strFun) {
        this.strFun = strFun;
    }

    /*  -2*x-3 */
    public double calculate(double x) {
        if (strFun == null || strFun.isEmpty())
            throw new RuntimeException("Function values is not set");
        String[] split = strFun.split("\\*x");

        if (split.length != 2)
            throw new RuntimeException("Function value is not correct");
        double k = Double.parseDouble(split[0]);
        double b = Double.parseDouble(split[1]);

        return k*x+b;
    }


}
