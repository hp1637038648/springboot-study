# 本项目为事务的相关学习

java中的事务总结：

1、https://www.cnblogs.com/lmyupupblogs/p/10248099.html

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
