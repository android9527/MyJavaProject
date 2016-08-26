package com.test.serializable;

import java.io.Serializable;

/**
 * Created by chenfeiyue on 16/1/11.
 */
public class People implements Serializable{

    private static final long serialVersionUID = 2L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + "]";
	}
    
    
}
