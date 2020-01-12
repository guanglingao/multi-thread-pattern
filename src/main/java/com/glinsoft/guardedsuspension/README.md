### Guarded Suspension 模式考量

- 关注点分离，Guarded Suspension模式的各个参与者各自只关注本模式索要解决问题的一个方面，
各个参与者时是高度内聚（Cohesive）的。
```
    GuardedObject: 包含受保护方法的对象。
    GuardedAction: 改变GuardedObject实例状态的方法。
    Predicate: 保护条件
    Blocker: 负责对guardedMethod（受保护方法）的线程进行挂起和唤醒，并执行GuardedAction所实现的目标操作。

```
- 可能增加JVM的回收负担
- 可能增加上下文切换

---
### ReentrantLock的正确使用方式
```
    lock.lockInterruptibly();
    try{
        // 临界区代码
    }finally{
        lock.unlock();
    }

```

