# Log4j详解

参考博客：

1、http://blog.didispace.com/springbootlog4j/

2、https://www.cnblogs.com/wangzhuxing/p/7753420.html（很全面）

3、https://blog.csdn.net/king_kgh/article/details/80430002

4、https://my.oschina.net/u/3908739/blog/1942418/

5、https://www.jianshu.com/p/c61922cfbfce

Log4j有三个主要的组件：Loggers(记录器)，Appenders (输出源)和Layouts(布局)。

这里可简单理解为日志类别，日志要输出的地方和日志以何种形式输出

slf4j 是一个为 Java 程序提供日志输出的统一接口，并不是一个日志实现方案，类似于JDNC一样，只是规则而已。

MDC日志跟踪详解：

1、https://www.jianshu.com/p/8f6c74381dc3

MDC提供了一个叫getCopyOfContextMap的方法，该方法就是把当前线程TreadLocal绑定的Map获取出来

slf4j（简单日志门面）介绍：

https://www.cnblogs.com/qlqwjy/p/9275415.html（全面入门）

日志记录级别的设置优先级可以总结为:Threshold  > 具体包的设置  >  rootLogger的全局配置

# Spring Boot中对log4j进行多环境不同日志级别的控制 

http://blog.didispace.com/springbootlog4jmuilt/
