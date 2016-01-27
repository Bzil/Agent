package bz.pock.agent.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;
import java.util.StringJoiner;

public class LoggerInterceptor {
    @RuntimeType
    public static void log(@This Object currentInstance, @AllArguments Object[] allArguments, @Origin Method method) {
        StringBuilder logs = new StringBuilder(currentInstance.getClass().getCanonicalName()).append(".").append(method.getName()).append("(");
        StringJoiner joiner = new StringJoiner(",");
        for (Object o : allArguments) {
            joiner.add(o.toString());
        }
        System.out.println(logs.append(joiner.toString()).append(")").toString());
    }
}
