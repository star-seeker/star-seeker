package com.goose.center.config;

import com.goose.center.config.rules.MyRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "tiga-user-service", configuration = MyRule.class)
public class RibbonConfiguration {
}
