package moe.zhi.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.annotation.Priority
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/23
 */
@Logit
@Interceptor
@Priority(1)
open class LogitInterceptor {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @AroundInvoke
    @Throws(Exception::class)
    fun invoke(ctx: InvocationContext): Any? {
        log.info("#### current method is " + ctx.method.name)
        return ctx.proceed()
    }
}