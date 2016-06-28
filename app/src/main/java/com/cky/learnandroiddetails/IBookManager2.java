package com.cky.learnandroiddetails;

import android.os.IInterface;

/**
 * Created by cuikangyuan on 16/6/28.
 *
 * 手动 创建 Binder
 *
 * 1.声明一个AIDL性质的接口
 */
public interface IBookManager2 extends IInterface {

    static final String DESCRIPTOR = "com.cky.learnandroiddetails.IBookManager2";

    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public java.util.List<com.cky.learnandroiddetails.Book> getBookList() throws android.os.RemoteException;

    public void addBook(com.cky.learnandroiddetails.Book book) throws android.os.RemoteException;
}
