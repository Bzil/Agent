package bz.pock.agent.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.jar.asm.commons.Method;

import java.util.Arrays;


public class LoggerInterceptor {
    public void log(@This Object currentInstance, @AllArguments Object[] allArguments,
                    @Origin Method method) {
        StringBuilder logs = new StringBuilder(currentInstance.getClass().getCanonicalName())
                .append(method.getName()).append("(");
        Arrays.stream(allArguments).map(o -> logs.append(o.toString()).append(", "));
        System.out.println(logs.append(")").toString());
    }
}
