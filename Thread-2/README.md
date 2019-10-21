# synchronized用法详解

参考博客：

https://www.cnblogs.com/lwx521/p/9031752.html

修饰对象：

修饰this，当前对象，这里的this指的是执行这段代码的对象（任务对象），synchronized得到的锁就是this这个对象的锁

总结：修饰this和修饰非静态方法一样。线程A访问对象A的带有同步代码块的方法A时，其他线程可以访问该对象的非同步方法和其他对象的所有方法

修饰方法：

1.修饰非静态方法

修饰非静态方法时，线程A访问对象A的非静态同步方法A时，其他线程可以访问该对象的非同步方法以及其他对象的任何方法.

2.修饰静态方法

修饰静态方法时，作用于类，同时作用于该类的所有对象。所以，线程A访问静态同步方法时，其他线程可以访问非静态同步方法和非同步方法，不可以访问静态同步方法

修饰类:

synchronized不可以直接修饰类，但是可以通过synchronized（类名.class）作用于类。修饰类和修饰静态方法一样，也是作用于该类的所有对象。

如果一个方法内带有synchronized (类名.class)这样的代码块，这个方法就相当于静态同步方法。

线程A访问该类的静态同步方法时，其他线程不可以访问静态同步方法，但是可以访问非静态同步方法和非同步方法。

# JAVA线程通信相关知识点

Java中wait、notify、notifyAll使用详解：

1.https://segmentfault.com/a/1190000019104391

2.https://www.cnblogs.com/moongeek/p/7631447.html

3.https://www.cnblogs.com/noteless/p/10468834.html#0

java并发之线程间通信协作简单使用：

https://www.cnblogs.com/xdyixia/p/9386133.html
