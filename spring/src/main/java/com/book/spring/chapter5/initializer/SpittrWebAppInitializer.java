package com.book.spring.chapter5.initializer;

import com.book.spring.chapter5.config.RootConfig;
import com.book.spring.chapter5.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author zhangyoubao
 * @version 2021/4/9
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };    // 指定配置类
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };    // 将DispatcherServlet映射到"/"
    }
}
