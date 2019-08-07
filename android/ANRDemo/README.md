

### 分类

1.KeyDispatchTimeout 按键或触摸事件在特定时间内无响应

2.BroadcastTimeout BroadcastReceiver在特定时间内无法处理完成

3.ServiceTimeout Service在特定的时间内无法处理完成


### 发生原因

1. 应用自身原因
    * IO异常，数据库异常
    * 图像操作
    * 创建大量对象，内存不足
2. 其他进程间接引起
    * 当前应用进程进行进程间通信请求其他进程，其他进程操作长时间无返回。
    * CPU占用高导致当前进程无法抢占到CPU时间片


### 排查问题

1. ANR的Log信息保存在：/data/anr/traces.txt 
2. 仔细查看ANR的成因(iowait?block?memoryleak?)


### 原因

1.InputDispatching Timeout：输入事件分发超时5s未响应完毕；

2.BroadcastQueue Timeout ：前台广播在10s内、后台广播在20秒内未执行完成；

3.Service Timeout ：前台服务在20s内、后台服务在200秒内未执行完成；

4.ContentProvider Timeout ：内容提供者,在publish过超时10s；

### 检测工具

`LeakCanary`

