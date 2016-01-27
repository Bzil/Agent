package bz.pock.agent.config;

import bz.pock.agent.interceptor.LoggerInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class LogAgent {

    private static final String LOG_ANNOTATION = "bz.pock.annotation.Log";

    public static void premain(String agentArguments, Instrumentation instrumentation) {
        System.out.println("Hello World from Agent");

        new AgentBuilder.Default().withListener(new AgentListener()).type(any())
                .transform((builder, typeDescription) -> builder
                        .invokable(isAnnotatedWith(named(LOG_ANNOTATION)))
                        .intercept(MethodDelegation.to(LoggerInterceptor.class).andThen(SuperMethodCall.INSTANCE))
                ).installOn(instrumentation);
    }
}

class AgentListener implements AgentBuilder.Listener {

    @Override
    public void onComplete(String arg0) {
    }

    @Override
    public void onError(String typeName, Throwable throwable) {
         System.err.println("Error for " + typeName);
         throwable.printStackTrace();
    }

    @Override
    public void onIgnored(TypeDescription arg0) {
    }

    @Override
    public void onTransformation(TypeDescription arg0, DynamicType arg1) {
    }
}
