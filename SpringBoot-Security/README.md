# Spring Boot中使用Spring Security进行安全控制最基础入门项目

参考：

http://blog.didispace.com/springbootsecurity/

# spring security 简介

https://blog.csdn.net/qq_22172133/article/details/86503223

# 错误解决：

1、问题：

使用springboot，权限管理使用spring security，使用内存用户验证，但无响应报错：

java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"

解决方法：

这是因为Spring boot 2.0及以上引用的security，依赖的是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：

即需要创建PasswordEncorder的实现类。或者使用Spring Security自带的PasswordEncoder的实现，比如：BCryptPasswordEncoder。

BCryptPasswordEncoder采用SHA-256 +随机盐+密钥对密码进行加密。

2、问题：

在使用Thymeleaf的时候，启动项目，出现了下面的错误：[THYMELEAF][main] Template Mode 'HTML5' is deprecated. Using Template Mode 'HTML' instead.

解决方法：

出现这个问题，是因为在application.properties中配置了下面的内容：

spring.thymeleaf.mode=HTML5 

而引入的Thymeleaf架包没有指定版本，所以需改为spring.thymeleaf.mode=HTML，若指定版本则为HTML5

