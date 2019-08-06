// IMyAidlInterface.aidl
package com.sanfen.aidl;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int aInt, int bInt);
    int min(int aInt, int bInt);
}
