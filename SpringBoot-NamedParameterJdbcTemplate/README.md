创建了SpringBoot整合NamedParamterJdbcTemplate实现增删改查项目。

参考博客：

https://blog.csdn.net/qq_34464926/article/details/90720099

NamedParameterJdbcTemplate说明介绍：

1.https://www.iteye.com/blog/zmx-373736

2.https://www.iteye.com/blog/itommy-2354746

3.https://segmentfault.com/a/1190000008896457

4.https://blog.csdn.net/u014534808/article/details/80374929

Immutable(不可变)集合说明使用：

不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。

1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象

2.线程安全的：immutable对象在多线程下安全，没有竞态条件

3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)

4.可以被使用为一个常量，并且期望在未来也是保持不变的

https://www.cnblogs.com/peida/p/Guava_ImmutableCollections.html
