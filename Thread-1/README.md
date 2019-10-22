# 该项目展示了JAVA线程的一些基础知识点及注意事项

java线程Thread和Runnable区别和联系：

1.https://www.iteye.com/blog/zx-code-2220866

2.https://www.jianshu.com/p/333ce4b3d5b8

3.https://www.jb51.net/article/158337.htm

4.https://blog.csdn.net/zhaojianting/article/details/97664370

Runnable和Callable的区别：

1.https://blog.csdn.net/const_/article/details/88105721

Java线程创建形式Thread构造详解：

1.https://www.cnblogs.com/noteless/p/10354753.html#10

java8多线程异步调用 CompletableFuture 详解（未看）（有时间必看）：

1.https://blog.csdn.net/mrxiky/article/details/78962614

2.https://www.jianshu.com/p/6bac52527ca4

3.https://www.cnblogs.com/cjsblog/p/9267163.html

4.https://blog.csdn.net/m0_37664223/article/details/97933674

java8 Lambda表达式简介:

1.https://www.runoob.com/java/java8-lambda-expressions.html

2.https://blog.csdn.net/qq_28410283/article/details/80961022

使用ExecutorCompletionService提交任务后会将执行结果放到阻塞队列中，使用take方法会得到结果，

哪个任务先执行完成就先获取到这个任务的执行结果：

ExecutorService pool = Executors.newFixedThreadPool(taskSize);

ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(pool);
