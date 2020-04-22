package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController
{
	@RequestMapping("/")
	public String helloWorld(){
	return Math.random()>0.5?Math.random()+"":new Date().toString();
	}
}
