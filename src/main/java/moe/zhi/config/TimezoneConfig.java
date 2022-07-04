package moe.zhi.config;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

/**
 * 读取配置文件，还有一种方式见listener package的 @ConfigProperty(name = "app.city")
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/4
 */
// @StaticInitSafe // add if can not init in static
@ConfigMapping(prefix = "app")
public interface TimezoneConfig {
    String timezone();//如何配置文件中属性不是timezone，使用@WithName("propNm")映射

    List<String> city();
}
