

## APP启动流程


![app launch](https://upload-images.jianshu.io/upload_images/851999-a9c2c456c9f91596.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/683/format/webp)

1. 点击桌面的图标，click事件会调用startActivity(Intent), 通过Binder IPC，最终调用到ActivityManagerService.
2. 通过PackageManager的resolveIntent()收集这个intent对象的指向信息。指向信息存储在intent对象中
3. 通过grantUriPermissionLocked()方法验证用户是否有足够的权限去调用该intent对象指向的Activity
4. New Task, 并在新的Task中启动目标activity
5. 检查这个进程的ProcessRecord是否存在，如果不存在则创建新的进程实例化Activity
6. ActivityManagerService调用startProcessLocked()方法来创建新的进程，通过socket通道传递参数给Zygote进程，Zygote孵化并调用ZygoteInit.main()方法实例化ActivityThread对象并最终返回新进程的pid。
7. Zygote进程孵化出新的应用进程后，会执行ActivityThread类的main方法.在该方法里会先准备好Looper和消息队列，然后调用attach方法将应用进程绑定到ActivityManagerService，然后进入loop循环，不断地读取消息队列里的消息，并分发消息。
8. ActivityThread的main方法执行后,应用进程接下来通知ActivityManagerService应用进程已启动，ActivityManagerService保存应用进程的一个代理对象
9. ActivityThread对象调用bindApplication()方法发送一个BIND_APPLICATION消息，handle处理调用makeApplication()方法来加载App的classes到内存中.然后ActivityManagerService通知应用进程创建入口Activity的实例，并执行它的生命周期方法

## Zygote启动流程
1. ZygoteInit.java 的 main

```java
 public static void main(String argv[]) {
        ...
        // Mark zygote start. This ensures that thread creation will throw
        // an error.
        ZygoteHooks.startZygoteNoThreadCreation(); //1. 在孵化器开始时关闭线程创建

        // Zygote goes into its own process group.
        try {
            Os.setpgid(0, 0);
        } catch (ErrnoException ex) {
            throw new RuntimeException("Failed to setpgid(0,0)", ex);
        }
        ...
        zygoteServer.registerServerSocketFromEnv(socketName); //2. 注册socket进行进程间通信
        ...
        preload(bootTimingsTraceLog); //预加载资源文件
        ...
        ZygoteHooks.stopZygoteNoThreadCreation();//startSystemServer结束，开启线程创建

        if (startSystemServer) {
            Runnable r = forkSystemServer(abiList, socketName, zygoteServer); //为系统服务器进程准备参数和分支。

            // {@code r == null} in the parent (zygote) process, and {@code r != null} in the
            // child (system_server) process.
            if (r != null) {
                r.run();
                return;
            }
        }
        ···
        caller = zygoteServer.runSelectLoop(abiList); //Zygote等待客户端的创建进程请求.
        ···
        zygoteServer.closeServerSocket();关闭socket
    }
```

在Android系统中有很多的公共资源，所有的程序都会需要。而Zygote创建应用程序进程过程其实就是复制自身进程地址空间作为应用程序进程的地址空间，因此在Zygote进程中加载的类和资源都可以共享给所有由Zygote进程孵化的应用程序。所以就可以在Zygote中对公共类与资源进行加载，当应用程序启动时只需要加载自身特有的类与资源就行了，提高了应用软件的启动速度，这也看出面向对象编程中继承关系的好处。

```Java
static void preload(TimingsTraceLog bootTimingsTraceLog) {
        Log.d(TAG, "begin preload");
        bootTimingsTraceLog.traceBegin("BeginIcuCachePinning");
        beginIcuCachePinning();
        bootTimingsTraceLog.traceEnd(); // BeginIcuCachePinning
        bootTimingsTraceLog.traceBegin("PreloadClasses");
        preloadClasses(); //预加载Classes
        bootTimingsTraceLog.traceEnd(); // PreloadClasses
        bootTimingsTraceLog.traceBegin("PreloadResources");
        preloadResources(); //预加载Resources
        bootTimingsTraceLog.traceEnd(); // PreloadResources
        Trace.traceBegin(Trace.TRACE_TAG_DALVIK, "PreloadAppProcessHALs");
        nativePreloadAppProcessHALs();
        Trace.traceEnd(Trace.TRACE_TAG_DALVIK);
        Trace.traceBegin(Trace.TRACE_TAG_DALVIK, "PreloadOpenGL");
        preloadOpenGL();  //预加载OpenGL
        Trace.traceEnd(Trace.TRACE_TAG_DALVIK);
        preloadSharedLibraries(); //预加载共享类库
        preloadTextResources(); //预加载文本资源
        // Ask the WebViewFactory to do any initialization that must run in the zygote process,
        // for memory sharing purposes.
        WebViewFactory.prepareWebViewInZygote();
        endIcuCachePinning();
        warmUpJcaProviders();
        Log.d(TAG, "end preload");
        sPreloadComplete = true;
    }

```
[链接](https://github.com/SanfenR/rubike/tree/master/android/AppStartDemo)