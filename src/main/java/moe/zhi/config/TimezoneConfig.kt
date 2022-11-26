package moe.zhi.config

import io.smallrye.config.ConfigMapping

/**
 * 读取配置文件，还有一种方式见listener package的 @ConfigProperty(name = "app.city")
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/4
 */
// @StaticInitSafe // add if can not init in static
@ConfigMapping(prefix = "app")
interface TimezoneConfig {
    fun timezone(): String? //如果配置文件中属性不是timezone，则使用@WithName("propNm")映射
    fun city(): List<String?>?
}