// IBookManager.aidl
package com.cky.learnandroiddetails;

// Declare any non-default types here with import statements

import com.cky.learnandroiddetails.Book;
interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
