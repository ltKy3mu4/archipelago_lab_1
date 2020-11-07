package function.impl;

import function.FunctionType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectableFunction {

    FunctionType type();

}
