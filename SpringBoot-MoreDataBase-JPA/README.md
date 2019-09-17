本项目实现了SpringBoot利用JPA完成了对多数据源配置与使用 

参考博客：

1.http://blog.didispace.com/springbootmultidatasource/

2.https://www.jianshu.com/p/9f812e651319

@EnableTransactionManagement（事务管理器）注解详解：

启用注解事务管理，等同于xml配置方式的 <tx:annotation-driven />

关于事务管理器，不管是JPA还是JDBC等都实现自接口 PlatformTransactionManager 

如果你添加的是 spring-boot-starter-jdbc 依赖，框架会默认注入 DataSourceTransactionManager 实例。

如果你添加的是 spring-boot-starter-data-jpa 依赖，框架会默认注入 JpaTransactionManager 实例。

https://blog.csdn.net/u010963948/article/details/79208328

@EnableJpaRepositories注解详解：

该注解用于Srping JPA的代码配置，用于取代xml形式的配置文件。

1.https://blog.csdn.net/u013473691/article/details/52351830





