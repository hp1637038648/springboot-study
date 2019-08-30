本项目实现了通过SpringBoot结合Swagger2构建RESTFul API风格的接口文档

参考博客：

http://blog.didispace.com/springbootswagger2/

Swagger2相关博客参考：

1.https://blog.csdn.net/zhongzk69/article/details/94987568

2.https://www.cnblogs.com/softidea/p/6251249.html

3.https://www.cnblogs.com/zhaojiankai/p/8318359.html

相应知识点：

1.首先创建Swagger2配置类Swagger2.java。Docket 类提供了 apis() 和 paths()两 个方法来帮助我们在不同级别上过滤接口。

apis()：这种方式我们可以通过指定包名的方式，让 Swagger 只去某些包下面扫描。

paths()：这种方式可以通过筛选 API 的 url 来进行过滤。

2.通过在控制器类(Controller)上增加@Api 注解，可以给控制器增加描述和标签信息。

3.常用注解如下：

@Api()用于类名

@ApiOperation()用于方法名

@ApiParam()用于参数说明

@ApiModel()用于实体类

@ApiModelProperty用于实体类属性

Swagger2的官方文档：

http://springfox.github.io/springfox/docs/current/

大部分注解如下：

@Api：修饰整个类，描述Controller的作用

@ApiOperation：描述一个类的一个方法，或者说一个接口

@ApiParam：单个参数描述

@ApiModel：用对象来接收参数

@ApiProperty：用对象接收参数时，描述对象的一个字段

@ApiResponse：HTTP响应其中1个描述

@ApiResponses：HTTP响应整体描述

@ApiIgnore：使用该注解忽略这个API

@ApiError ：发生错误返回的信息

@ApiImplicitParam：一个请求参数

@ApiImplicitParams：多个请求参数
