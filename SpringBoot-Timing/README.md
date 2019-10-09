本项目利用SpringBoot创建了一个最简单的定时任务项目。

参考博客：

1.http://blog.didispace.com/springbootscheduled/

2.http://www.ityouknow.com/springboot/2016/12/02/spring-boot-scheduler.html

在Spring Boot的主类中加入@EnableScheduling注解，启用定时任务的配置

通过@Scheduled来创建定时任务的实现类。

@scheduled使用的时候，必须对代码进行异常捕获，不然出现异常会导致定时任务停止，后续都不再执行的。

@Scheduled详解：

@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行

@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行

@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次

@Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则
