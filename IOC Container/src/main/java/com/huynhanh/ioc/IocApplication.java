package com.huynhanh.ioc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

interface Sim {
	void calling();
}

class Phone1 implements Sim {
	@Override
	public void calling() {
		System.out.println("1 Calling....");
	}
}

class Phone2 implements Sim {
	@Override
	public void calling() {
		System.out.println("2 Calling....");
	}
}

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Sim s = context.getBean("sim", Sim.class);
		s.calling();
	}

}
