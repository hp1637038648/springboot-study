# 本项目为事务的相关学习

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

常见的JTA实现有以下几种：

1.J2EE容器所提供的JTA实现(JBoss)

2.独立的JTA实现:如JOTM，Atomikos.这些实现可以应用在那些不使用J2EE应用服务器的环境里用以提供分布事事务保证。如Tomcat,Jetty以及普通的java应用。



