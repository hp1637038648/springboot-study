该项目完成了对User对象的RESTful API的创建以及单元测试的编写。

参考博客：

http://blog.didispace.com/springbootrestfulapi/

关于@RequestParam、@PathVariable、@ModelAttribute的学习：

1.https://www.cnblogs.com/yixiu868/p/8724882.html

2.https://www.cnblogs.com/goloving/p/9241393.html

Collections类中提供了多个synchronizedXxx方法，该方法返回指定集合对象对应的同步对象，从而可以解决多线程并发访问集合时的线程安全问题。

集合框架中经常使用的三个实现类:

HashSet、ArrayList、HashMap都是线程不安全的。如果有多个线程访问它们，而且有超过一个线程试图修改它们，则可能出现并发错误。

Collections提供了多个静态方法用于创建同步集合。如下使用就直接返回了List、Set、Map的线程安全的版本eg:

//创建四个同步的集合对象

Collection c = Collections.synchronizedCollection(new ArrayList());

List list = Collections.synchronizedList(new ArrayList());

Set set = Collections.synchronizedSet(new HashSet());

Map map = Collections.synchronizedMap(new HashMap());


Collections.synchronizedMap()方法来获取一个线程安全的集合（Collections.synchronizedMap()实现原理是Collections定义了一个SynchronizedMap的内部类，这个类实现了Map接口，在调用方法时使用synchronized来保证线程同步,当然了实际上操作的还是我们传入的HashMap实例，简单的说就是Collections.synchronizedMap()方法帮我们在操作HashMap时自动添加了synchronized来实现线程同步，类似的其它Collections.synchronizedXX方法也是类似原理）'





