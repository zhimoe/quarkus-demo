package moe.zhi.interceptor

import java.lang.annotation.Inherited
import javax.interceptor.InterceptorBinding

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/23
 */
@Inherited
@InterceptorBinding
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CLASS
)
annotation class Logit 