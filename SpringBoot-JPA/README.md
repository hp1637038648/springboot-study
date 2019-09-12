参考博客：

http://blog.didispace.com/springbootdata2/

JPA更详细用法参考博客：

http://www.ityouknow.com/springboot/2016/08/20/spring-boot-jpa.html

JPA之@Entity、@Table、@Column、@Id详解：

1.https://www.cnblogs.com/xuwenjin/p/8830850.html

2.https://www.cnblogs.com/powerwu/articles/8258015.html


jpa（一）----spring-data-jpa 复杂查询，包括多表关联，分页，排序等:

1.https://blog.csdn.net/u010775025/article/details/80497986

2.https://www.cnblogs.com/summary-2017/p/8018574.html


@GeneratedValue与@GenericGenerator区别：

https://blog.csdn.net/u011781521/article/details/72210980

@GeneratorValue注解----JPA通用策略生成器

JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO。

TABLE：使用一个特定的数据库表格来保存主键。 

SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。

IDENTITY：主键由数据库自动生成（主要是自动增长型） 

AUTO：主键由程序控制。

@GenericGenerator注解----自定义主键生成策略

注意:

Jpa 是一套规范，不是一套产品，那么像 Hibernate,TopLink,JDO 他们是一套产品；

如果说这些产品实现了这个 Jpa 规范，那么我们就可以叫他们为 Jpa 的实现产品。
