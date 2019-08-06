# AIDL

## 实现

1. 创建aidl文件

> 在build之后会在`build`目录生成IMyAidlInterface的实现类

```java
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int aInt, int bInt);
    int min(int aInt, int bInt);
}

```

2. 创建Service，并实现`IMyAidlInterface.Stub()`接口

```java
    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int aInt, int bInt) {
            return aInt + bInt;
        }

        @Override
        public int min(int aInt, int bInt) {
            return aInt < bInt ? aInt : bInt;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
```

3. 在Activity中bindService

```
  bindService(intent, conn, Context.BIND_AUTO_CREATE);
private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface = null;
        }
    };
```

## 解析

### 服务端

> transact [træn'zækt] 

```Java
        @Override
        public boolean onTransact(int code, 
        android.os.Parcel data, 
        android.os.Parcel reply,
        int flags) throws android.os.RemoteException {
            ...
        }
```

可以看到onTransact有四个参数 code,data,replay,flags

* code   是一个整形的唯一标识，用于区分执行哪个方法，客户端会传递此参数，告诉服务端执行哪个方法
* data   客户端传递过来的参数
* replay 服务器返回回去的值
* flags  标明是否有返回值，0为有（双向），1为没有（单向）

```
data.enforceInterface(DESCRIPTOR);
与客户端的writeInterfaceToken对用，标识远程服务的名称

```

### 客户端

> 实际上调用了`Proxy`

```Java
       public static com.sanfen.aidl.IMyAidlInterface asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.sanfen.aidl.IMyAidlInterface))) {
                return ((com.sanfen.aidl.IMyAidlInterface) iin);
            }
            return new com.sanfen.aidl.IMyAidlInterface.Stub.Proxy(obj);
        }
```

最后通过`transact`方法传递给服务端的`onTransact()`，并在`_reply`中返回值

```Java
    mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
    ==>
    onTransact(int code, android.os.Parcel data, android.os.Parcel reply,int flags)
```

[源码](https://github.com/SanfenR/rubike/tree/master/android/AidlDemo)