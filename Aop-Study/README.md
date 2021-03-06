# 本项目旨在学习理解AOP等相关知识点

http://blog.didispace.com/springbootaoplog/

AOP理论知识理解：

1、https://www.jianshu.com/p/5837230e10d5

spring AOP 是通过动态代理技术实现的，Java JDK的动态代理(Proxy，底层通过反射实现)或者CGLIB的动态代理(底层通过继承实现)

而动态代理的技术是通过反射来实现的

动态代理技术的实现方式有两种：基于接口的JDK动态代理和基于继承的CGLib动态代理。

Spring AOP 中的代理使用逻辑如下：

如果目标对象实现了接口，默认情况下会采用 JDK 的动态代理实现 AOP；如果目标对象没有实现了接口，则采用 CGLIB 库。

Spring 会自动在 JDK 动态代理和 CGLIB 动态代理之间转换

Spring Boot实践——AOP实现：

https://www.cnblogs.com/onlymate/p/9605165.html

Spring AOP切点表达式用法总结：

https://www.cnblogs.com/zhangxufeng/p/9160869.html

spring AOP之proceedingjoinpoint和joinpoint区别:

https://www.cnblogs.com/zhjh256/p/10694165.html

# AOP核心概念：

1、横切关注点

对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点

2、切面（aspect）

类是对物体特征的抽象，切面就是对横切关注点的抽象，Aspect声明类似于Java中的类声明，在Aspect中会包含着一些 Pointcut 以及相应的 Advice。

3、连接点（joinpoint）

被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器

4、切入点（pointcut）

对连接点进行拦截的定义。表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方

5、通知（advice）

所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类

6、目标对象

代理的目标对象（织入 Advice 的目标对象.）

7、织入（weave）

将切面应用到目标对象并导致代理对象创建的过程（将 Aspect 和其他对象连接起来, 并创建 Adviced object 的过程）

8、引入（introduction）

在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

# 详解Spring面向切面编程（AOP)三种实现：

1、https://www.cnblogs.com/jiaweit/p/9986390.html

2、https://www.2cto.com/kf/201903/799053.html

3、https://www.cnblogs.com/bj-xiaodao/p/10777914.html

# 使用注解实现AOP

1、https://www.jianshu.com/p/8a22d3b9cb9a

# 使用Schema(xml)配置文件实现aop

1、https://blog.csdn.net/qq_41767337/article/details/89077073


