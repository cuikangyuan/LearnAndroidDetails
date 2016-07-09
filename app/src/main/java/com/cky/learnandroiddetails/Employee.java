package com.cky.learnandroiddetails;

/**
 * Created by cuikangyuan on 16/7/9.
 */
public class Employee {

    /*
    * 尽量保证使用对象的同一个属性来生成hashCode 和 equals方法
    * 任何时候只要a.equals(b)为true 那么a.hashCode 和 b.hashCode必须相等
    * 这连个方法必须同时重写
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
