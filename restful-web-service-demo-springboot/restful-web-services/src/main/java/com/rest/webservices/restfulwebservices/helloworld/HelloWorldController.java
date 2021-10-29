package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//let know spring that this is a controller
@RestController
public class HelloWorldController {
	// autowired message source to get messages from messages property files
	@Autowired
	private MessageSource messageSource;
	// This is a general mapping annotation where we can define type of request and
	// its path
	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")

	// This annotation is more specific to type of request(GET)

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorlBean helloWorldBean() {
		return new HelloWorlBean("Hello World");
	}

	// Path variable will be replaced by value provided in URL
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorlBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorlBean(String.format("Hello World, %s", name));
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
	/** @RequestHeader(name = "Accept-Language", required = false) Locale locale */
	) {

		// Here we need to locale to fetch language from header which will get very
		// difficult to add in each and every method
		// return messageSource.getMessage("good.morning.message", null, "Default
		// message", locale);

		// we can use LocaleContextHolder instead of fetching language from header
		return messageSource.getMessage("good.morning.message", null, "Default message",
				LocaleContextHolder.getLocale());
	}

}
