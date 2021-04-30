/**
 * 面向切面的Spring
 *
 * 1、面向切面编程的基本原理
 *    横切关注点可以被模块化为特殊的类，被称为切面；
 *    通知(Advice)：切面的工作
 *        前置通知(Before)
 *        后置通知(After)
 *        返回通知(After-returning)
 *        异常通知(After-throwing)
 *        环绕通知(Around)
 *     连接点(Join point): 在应用执行过程中能够插入切面的一个点;
 *     切点(Pointcut): 匹配通知所要织入的一个或多个连接点;
 *     切面(Aspect):
 *     引入(Introduction): 引入允许我们向现有的类添加新方法或属性;
 *     织入(Weaving): 织入是把切面应用到目标对象并创建新的代理对象的过程;
 *
 *     spring对aop的支持:
 *        基于代理的经典Spring AOP;
 *        纯POJO切面;
 *        @AspectJ 注解驱动的切面;
 *        注入式AspectJ切面;
 *
 * 2、通过POJO创建切面
 * 3、使用@AspectJ注解
 *      处理通知中的参数;
 *      通过注解引入新功能;
 * 4、为AspectJ切面注入依赖
 *
 * @author zhangyoubao
 * @version 2021/4/9
 */
package com.book.spring.chapter4;