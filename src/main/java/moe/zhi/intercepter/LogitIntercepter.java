package moe.zhi.intercepter;

import lombok.extern.slf4j.Slf4j;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/23
 */
@Logit
@Interceptor
@Slf4j
public class LogitIntercepter {

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        log.info("#### current method is " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
