package com.liuxu.online.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yuliang-ds1
 * @Date 2018/12/8 20:53
 * @Description
 */
@Configuration
public class CookieConfig {

    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    ((TomcatEmbeddedServletContainerFactory) container)
                            .addContextCustomizers(new TomcatContextCustomizer() {

                                @Override
                                public void customize(Context context) {
                                    context.setCookieProcessor(new LegacyCookieProcessor());
                                }

                            });
                }
            }

        };
    }
}
