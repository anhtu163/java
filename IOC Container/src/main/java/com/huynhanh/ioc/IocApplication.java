package com.huynhanh.ioc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class Phone{
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void calling() {
		System.out.println(" calling....");
	}
}

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Phone s = context.getBean("sim", Phone.class);
		System.out.print(s.getName());
		s.calling();
	}

}
