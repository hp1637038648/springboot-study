# Spring Boot Security 详解

https://www.cnblogs.com/huanchupkblog/p/10570962.html

# Security 系列教程（未学习，粗略浏览）

http://www.spring4all.com/article/428

# spring security四种实现方式（没怎么看）

https://blog.csdn.net/bao19901210/article/details/52574340

# Apache Shiro系列教程（一点没看，先收藏后期看）

https://www.iteye.com/blog/jinnianshilongnian-2018398

# 理解OAuth 2.0

http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html

本项目配置过程：

1、User（用户） 类要实现 UserDetails（用户详细信息）接口，该接口是实现Spring Security 认证信息的核心接口，其中getAuthorities()方法返回的是该用户设置的权限信息，权限信息也可以是用户的其他信息，不一定是角色信息。

2、Role（权限）类要实现 GrantedAuthority（授权）接口，并重写getAuthority()方法，权限点可以为任何字符串，不一定非要用角色名。
所有的Authentication（身份验证）实现类都保存了一个GrantedAuthority列表，其表示用户所具有的权限。GrantedAuthority是通过AuthenticationManager设置Authentication对象中的，然后AccessDecisionManager（访问决策管理器）将从Authentication中获取用户所具有的GrantedAuthority来鉴定用户是否具有访问对应资源的权限。

3、Service 层需要实现 UserDetailsService 接口，该接口是根据用户名获取该用户的所有信息， 包括用户信息和权限点。

4、实现FilterInvocationSecurityMetadataSource接口，FilterInvocationSecurityMetadataSource 的作用是用来储存请求与权限的对应关系。

FilterInvocationSecurityMetadataSource接口有3个方法：

(1)boolean supports(Class<?> clazz)：指示该类是否能够为指定的方法调用或Web请求提供ConfigAttributes。

(2)Collection<ConfigAttribute> getAllConfigAttributes()：Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里。
  
(3)Collection<ConfigAttribute> getAttributes(Object object)：当接收到一个http请求时, filterSecurityInterceptor会调用的方法. 参数object是一个包含url信息的HttpServletRequest实例. 这个方法要返回请求该url所需要的所有权限集合。

5、实现AccessDecisionManager（访问决策管理器）接口，AccessDecisionManager是由AbstractSecurityInterceptor调用的，它负责鉴定用户是否有访问对应资源（方法或URL）的权限。

6、继承AbstractSecurityInterceptor类，并实现Filter接口。
每种受支持的安全对象类型（方法调用或Web请求）都有自己的拦截器类，它是AbstractSecurityInterceptor的子类，AbstractSecurityInterceptor是一个实现了对受保护对象的访问进行拦截的抽象类。

AbstractSecurityInterceptor的机制可以分为几个步骤：

        查找与当前请求关联的“配置属性（简单的理解就是权限）”
        将 安全对象（方法调用或Web请求）、当前身份验证、配置属性 提交给决策器（AccessDecisionManager）
        （可选）更改调用所根据的身份验证
        允许继续进行安全对象调用(假设授予了访问权)
        在调用返回之后，如果配置了AfterInvocationManager。如果调用引发异常，则不会调用AfterInvocationManager。

AbstractSecurityInterceptor中的方法说明：

    beforeInvocation()方法实现了对访问受保护对象的权限校验，内部用到了AccessDecisionManager和AuthenticationManager；
    finallyInvocation()方法用于实现受保护对象请求完毕后的一些清理工作，主要是如果在beforeInvocation()中改变了SecurityContext，则在finallyInvocation()中需要将其恢复为原来的SecurityContext，该方法的调用应当包含在子类请求受保护资源时的finally语句块中。
    afterInvocation()方法实现了对返回结果的处理，在注入了AfterInvocationManager的情况下默认会调用AccessDecisionManager的decide()方法。

7、@EnableWebSecurity注解以及WebSecurityConfigurerAdapter一起配合提供基于web的security。自定义类继承了WebSecurityConfigurerAdapter来重写了一些方法来指定一些特定的Web安全设置。
configureGlobal(AuthenticationManagerBuilder auth)方法配置用户相关信息。
configure(HttpSecurity http)方法配置请求安全信息。



