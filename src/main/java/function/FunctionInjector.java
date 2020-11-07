package function;

import function.impl.InjectableFunction;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionInjector {
    private static final Reflections reflection = new Reflections(FunctionHandler.class);
    private static final Map<FunctionType, FunctionHandler> handlers = new HashMap<>();

    public static synchronized FunctionHandler getHandler(FunctionType type) {
        return handlers.get(type);
    }

   static {
        try {
            Set<Class<?>> typesAnnotatedWith = reflection.getTypesAnnotatedWith(InjectableFunction.class);
            for (Class<?> clazz : typesAnnotatedWith) {
                InjectableFunction annotation = clazz.getAnnotation(InjectableFunction.class);
                FunctionHandler fh = (FunctionHandler) clazz.newInstance();
                handlers.put(annotation.type(), fh);
            }
        }catch (Exception e){
            throw new RuntimeException("can not inject function handlers: "+e.getMessage());
        }

    }

}
