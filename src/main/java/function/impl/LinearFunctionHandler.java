package function.impl;

import function.FunctionHandler;
import function.FunctionType;

@InjectableFunction(type = FunctionType.Linear)
public class LinearFunctionHandler implements FunctionHandler {
    private String strFun;

    public void setFunction(String strFun) {
        this.strFun = strFun;
    }

    public double calculate(double x) {
        return x*2;
    }


}
