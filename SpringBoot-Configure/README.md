本项目主要是对Spring Boot属性配置文件application.properties的详解。

参考博客：

1.http://blog.didispace.com/springbootproperties/

2.https://www.cnblogs.com/shamo89/p/8178109.html

主要知识点：

1.自定义属性

2.参数间引用

3.使用自定义配置文件。

若使用SpringBoot1.5以前的版本，那么可以通过locations指定properties文件的位置，这样：

@ConfigurationProperties(prefix = "com.md",locations="classpath:test.properties")

但1.5版本后就没有这个属性了，需要添加@ConfigurationProperties和@PropertySource("classpath:test.properties")后才可以读取

4.随机值配置。（配置文件中${random} 可以用来生成各种不同类型的随机值）

5.外部配置-命令行参数配置（命令行中连续的两个减号--就是对application.properties中的属性值进行赋值的标识）

6.配置文件的优先级

如果你在相同优先级位置同时有application.properties和application.yml，那么application.yml里面的属性就会覆盖application.properties里的属性。

7.Profile-多环境配置

想要使用对应的环境，需要在application.properties中使用spring.profiles.active属性来设置
