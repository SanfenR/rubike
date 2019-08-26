## 概念

1. 内存溢出      - VM在分配内存时内存不足导致内存溢出
2. 内存泄漏      - 有对象在释放时没有完全的清除引用导致内存泄漏，内存泄漏可能导致内存溢出
3. GC            - Java内部的回收器会定时的清除没有被引用的对象
4. OOM - 内存溢出时虚拟机会抛出Out of memory的异常


## OOM的过程

1. app启动的时候会分配一个内存  initSize
2. 当程序运行中内存不足时会增加内存
3. GC回收未被引用或弱引用虚引用的对象
4. 当出现对象过多无法被GC， 需要的内存空间大于heapSize时，抛出OOM


```Java
    ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
    int heapSize = manager.getMemoryClass(); //最大内存
    int maxHeapSize = manager.getLargeMemoryClass(); //当配置了android:largeHeap="true" 才有的最大堆内存
    Log.d(TAG, "heapSize" + heapSize + "maxHeapSize" + maxHeapSize);
```

## 出现OOM的原因

1. 加载对象过大
2. 相应资源过多，来不及释放

## 如何解决

1. 在内存引用上做些处理，常用的有软引用、强化引用、弱引用
2. 在内存中加载图片时直接在内存中作处理，如边界压缩
3. 动态回收内存
4. 优化Dalvik虚拟机的堆内存分配
5. 自定义堆内存大小