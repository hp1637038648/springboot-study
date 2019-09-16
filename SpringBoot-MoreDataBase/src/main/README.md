本项目完成了SpringBoot利用JDBCTemplate实现对多数据源配置与使用

参考博客：

1.http://blog.didispace.com/springbootmultidatasource/

@Bean注解详解：

@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。添加的bean的id为方法名

https://www.cnblogs.com/feiyu127/p/7700090.html

@Configuration注解详解：

@Configuration把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。 

https://blog.csdn.net/yangguosb/article/details/80871971

@Qualifier注解详解：

@Qualifier("bean的名字") 按名称装配Bean

@Autowired 默认是根据类型Type来自动注入的

@Qualifier("bean的名字") 一般作为@Autowired()的修饰用

@Resource 默认按名称装配，当找不到与名称匹配的bean才会按类型装配。

https://www.cnblogs.com/fengli9998/p/7472247.html

@ConfigurationProperties注解详解：

将配置文件application.properties中配置的每一个属性值映射到当前类的属性中；

@ConfigurationProperties：告诉springboot将本类中所有属性和配置文件中相关的配置进行绑定；

prefix="person"：指出将配置文件中person下的所有属性进行一一映射；

注意：只有当前这个类是容器中的组件时，才可以用容器提供的@ConfigurationProperties功能

https://blog.csdn.net/XU906722/article/details/81517429#%40ConfigurationProperties%E7%BB%99%E5%B1%9E%E6%80%A7%E6%98%A0%E5%B0%84%E5%80%BC

@Primary注解详解：

@Primary 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常

https://blog.csdn.net/qq_16055765/article/details/78833260
