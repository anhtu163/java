package com.demo.validation;

import javax.validation.constraints.*;

public class PersonForm {
    @NotNull(message = "Không được bỏ trống")
    @Size(min=2, max=30, message = "Tên phải chứa từ 2 đến 30 ký tự")
    private String name;

    @NotNull(message = "Không được bỏ trống")
    @Min(value = 18, message = "Phải từ 18 tuổi trở lên")
    private Integer age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}
