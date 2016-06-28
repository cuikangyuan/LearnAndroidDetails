package com.cky.learnandroiddetails;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by cuikangyuan on 16/6/28.
 *
 * 手动 创建 Binder
 *
 * 2.实现Stub类和Stub类中的Proxy代理类
 */
public class BookManagerImpl extends Binder implements IBookManager2 {

    /**
     * Construct the stub at attach it to the interface.
     */
    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<com.cky.learnandroiddetails.Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                com.cky.learnandroiddetails.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.cky.learnandroiddetails.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    public static IBookManager2 asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IBookManager2))) {
            return ((IBookManager2) iin);
        }
        return new BookManagerImpl.Proxy(obj);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        //TODO 待实现
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        //TODO 待实现
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    private static class Proxy implements IBookManager2 {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public java.util.List<com.cky.learnandroiddetails.Book> getBookList() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply= android.os.Parcel.obtain();
            java.util.List<com.cky.learnandroiddetails.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(com.cky.learnandroiddetails.Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(com.cky.learnandroiddetails.Book book) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }
}
