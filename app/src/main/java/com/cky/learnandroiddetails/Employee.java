package com.cky.learnandroiddetails;

/**
 * Created by cuikangyuan on 16/7/9.
 */
public class Employee {

    /*
    * 尽量保证使用对象的同一个属性来生成hashCode 和 equals方法
    * 任何时候只要a.equals(b)为true 那么a.hashCode 和 b.hashCode必须相等
    * 这连个方法必须同时重写
    * http://blog.sina.com.cn/s/blog_6ac4c6cb010149m6.html
    * http://java-min.iteye.com/blog/1416727
    *
    * http://blog.csdn.net/zhaoqianjava/article/details/6854213
    *
    * hashCode和equals都是用来判断2个对象是否相等.但他们区别比较大.
       一般来说,equals这个方法是给用户调用的,如果你想判断2个对象是否相等,你可以重写equals方法,然后在代码中调用哪个,就可以判断他们是否相等了,简单的来讲,equals方法主要是用来判断从表面上看或者从内容上看,2个对象是否相等.如有个学生类,属性只有姓名和性别,我们可以认为只要姓名和性别相等,那么就说着2个对象是相等的.
      Hashcode方法,一般用户不会去调用,比如在hashmap中,由于ket是不可以重复的,他在判断ket是不是重复的时候就使用了hashcode这个方法,而且也用到了equals方法,这里不可以重复是说equals和hashcode只要有一个不等就可以了!所以简单来说,hashcode相当于是一个对象的编码,就好象文件中的md5,他和equals不同就在于他返回的是int型的.比较起来不直观.我们一般在覆盖equals的同时,也要覆盖hashcode,让他们的逻辑一致.如上述例子,如果姓名和性别相等,那么hashcode的方法也要返回姓名和性别的hashcode值,这样从逻辑上,他们就一致了.
    * */
    private Integer id;
    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return false;
        }
        Employee e = (Employee) o;
        return (this.getId() == ((Employee) o).getId());

    }

    @Override
    public int hashCode() {
        int result = getId();
        return result;
    }
}
