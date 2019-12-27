# 本项目为JTA分布式事务的相关学习

springBoot+jpa+atomikos实现jta分布式事务的参考博客：

1、https://blog.csdn.net/qq_36424455/article/details/89642350

2、https://blog.csdn.net/Alexshi5/article/details/88750683

3、https://blog.csdn.net/IT_hejinrong/article/details/89147894#__194

java中的事务总结：

1、https://www.cnblogs.com/lmyupupblogs/p/10248099.html

2、https://www.jianshu.com/p/9b6df32c594e

3、https://blog.csdn.net/m0_38044941/article/details/88206350（包含spring事务的简单使用）

一、事务四特性：ACID

ACID是原子性（atomicity）、一致性（consistency）、隔离性 （isolation）和持久性（durability）的缩写。

Java事务的类型有三种：

JDBC事务、 JTA（Java Transaction API）事务、 容器事务

二、三种Java事务差异?

1、JDBC事务控制的局限性在一个数据库连接内，但是其使用简单。

2、JTA事务的功能强大，事务可以跨越多个数据库或多个DAO，使用也比较复杂。

3、容器事务，主要指的是J2EE应用服务器提供的事务管理，局限于EJB应用使用。

三、事务并发处理可能引起的问题：

脏读(dirty read)：一个事务读取了另一个事务尚未提交的数据，

不可重复读(non-repeatable read) ：一个事务的操作导致另一个事务前后两次读取到不同的数据

幻读(phantom read) ：一个事务的操作导致另一个事务前后两次查询的结果数据量不同。

# JNDI了解

1、https://blog.csdn.net/wn084/article/details/80729230

JBOSS是一个基于J2EE的开放源代码的应用服务器。 JBoss代码遵循LGPL许可，可以在任何商业应用中免费使用。JBoss是一个管理EJB的容器和服务器，支持EJB 1.1、EJB 2.0和EJB3的规范。但JBoss核心服务不包括支持servlet/JSP的WEB容器，一般与Tomcat或Jetty绑定使用。

Jboss是 web服务器的一种，主要做ejb容器，和tomcat集成就可以jsp,servlet,ejb通吃了。

 EJB(enterprise javabean)，他不是javabean简单的升级，而是一些提供分布式访问的类，包括实体bean,会话bean,消息驱动bean。
 
 # JTA事务
 
1、https://blog.csdn.net/qq_19167629/article/details/80405803

2、https://www.cnblogs.com/hollowcabbage/p/10689751.html

作为java平台上事务规范JTA（Java Transaction API）也定义了对XA事务的支持，实际上，JTA是基于XA架构上建模的。在JTA 中，事务管理器抽象为javax.transaction.TransactionManager接口，并通过底层事务服务（即Java Transaction Service）实现。像很多其他的Java规范一样，JTA仅仅定义了接口，具体的实现则是由供应商(如J2EE厂商)负责提供，目前JTA的实现主要有以下几种

1.J2EE容器所提供的JTA实现(JBoss)

2.独立的JTA实现:如JOTM，Atomikos.这些实现可以应用在那些不使用J2EE应用服务器的环境里用以提供分布事事务保证。如Tomcat,Jetty以及普通的java应用。

# XA规范

1、https://www.jianshu.com/p/d9e4982384a2

2、https://blog.csdn.net/Peter_Changyb/article/details/82017638

3、https://blog.csdn.net/bluishglc/article/details/7612811

XA是X/Open(The open group)提出的分布式事务处理规范，是分布式事务处理的工业标准

XA DTP模型（分布式事务处理模型）包括应用程序（ AP ）、事务管理器（ TM ）、资源管理器（ RM ）、通信资源管理器（ CRM ）四部分。

XA规范主要定义了（全局）事务管理器（Transaction Manager）和（局部）资源管理器（Resource Manager）之间的接口。XA接口是双向的系统接口，在事务管理器（Transaction Manager）（单点）以及一个或多个资源管理器（Resource Manager）之间形成通信桥梁

主要包括2PC（两阶段提交）协议 和 3PC（三阶段提交）协议。

# 额外学习

PropertyPlaceholderConfigurer读取属性文件使用详解：

https://blog.csdn.net/wrs120/article/details/84554366#font_colorblue1_4
