/**
 *
 * 1、Spring profile:
 *      spring.profiles.default; spring.profiles.active; @ActiveProfiles
 * 2、条件化bean声明:
 *      @Confitional
 * 3、自动装配与歧义性
 *      1)、标示首选(primary)的bean;
 *      2)、使用限定符(qualifier)
 *      3)、使用自定义的限定符: 不依赖于bean的名称
 *      4)、自定义限定符注解:
 * 4、bean的作用域
 *      @Scope
 *          1) 单例(Singleton): 整个应用中只创建bean的一个实例
 *             ConfigurableBeanFactory.SCOPE_SINGLETON
 *          2) 原型(Prototype): 每次注入或通过spring上下文获取时，都会创建一个新的实例
 *             ConfigurableBeanFactory.SCOPE_PROTOTYPE
 *          3) 会话(Session): 在Web应用中为每个会话创建一个bean实例
 *             @Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)     // 接口上
 *             @Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)   // 具体类上
 *          4) 请求(Request): 在Web应用中，为每个请求创建一个bean实例
 *             @Scope (value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)     // 接口上
 *  *          @Scope (value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)   // 具体类上
 * 5、Spring表达式语言
 *      1)、表示字面值
 *      2)、引用bean、属性和方法
 *      3)、在表达式中使用类型
 *      4)、SpEL运算符
 *      5)、计算正则表达式
 *      6)、计算集合
 * @author zhangyoubao
 * @version 2021/4/8
 */
package com.book.spring.chapter3;