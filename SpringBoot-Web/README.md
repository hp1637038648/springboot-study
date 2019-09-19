此项目介绍SprinBoot如何进行Web应用的开发

参考博客：

1.http://blog.didispace.com/springbootweb/

2.http://www.ityouknow.com/springboot/2016/02/03/spring-boot-web.html

Spring Boot中Web应用的统一异常处理 ：

参考博客：

1.http://blog.didispace.com/springbootexception/

2.https://www.jb51.net/article/142162.htm

3.https://blog.csdn.net/zzzgd_666/article/details/81544098

使用@ExceptionHandler(value = Exception.class)，可以处理异常, 但是仅限于当前Controller中处理异常, 

@ControllerAdvice可以配置basePackage下的所有controller. 所以结合两者使用,就可以处理全局的异常了.

对于既定义特定类型的处理器，又定义 Exception 等父类型的处理器时要特别小心，并不是所有的异常都会往上级处理，

如果我们想只减少处理器类的数量，不想为每种特定类型的处理器添加类或者方法，那么建议使用 instanceof 关键字对异常类型进行判断即可。

SpringMVC之ModelAndView的用法详解：

https://www.cnblogs.com/alsf/p/9134552.html
