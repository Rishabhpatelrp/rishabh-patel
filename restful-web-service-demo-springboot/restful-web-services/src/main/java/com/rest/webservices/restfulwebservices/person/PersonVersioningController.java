package com.rest.webservices.restfulwebservices.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	/**
	 * Caching is easy when using different URLs for version but there will be many
	 * uris to handle which polluting http url
	 * 
	 * @return
	 */
	@GetMapping(path = "v1/person")
	public PersonV1 personV1() {
		// TODO Auto-generated method stub
		return new PersonV1("Bob Charlee");
	}

	@GetMapping(path = "v2/person")
	public PersonV2 personV2() {
		// TODO Auto-generated method stub
		return new PersonV2(new Name("Bob", "Charlee"));
	}

	/**
	 * differentiating with url parameters but polluting http url with parameter
	 * 
	 * @return
	 */
	@GetMapping(value = "person/param", params = "version-1")
	public PersonV1 paramV1() {
		// TODO Auto-generated method stub
		return new PersonV1("Bob Charlee");
	}

	@GetMapping(value = "person/param", params = "version-2")
	public PersonV2 paramV2() {
		// TODO Auto-generated method stub
		return new PersonV2(new Name("Bob", "Charlee"));
	}

	/**
	 * differentiating with header parameters which makes caching header or not
	 * possible
	 * 
	 * @return
	 */
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		// TODO Auto-generated method stub
		return new PersonV1("Bob Charlee");
	}

	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		// TODO Auto-generated method stub
		return new PersonV2(new Name("Bob", "Charlee"));
	}

	/**
	 * differentiating with accept(produce) parameters which makes caching header or
	 * not possible
	 * 
	 * @return
	 */
	@GetMapping(value = "person/produce", produces = "application/vnd.company.app-v1+json")
	public PersonV1 produceV1() {
		// TODO Auto-generated method stub
		return new PersonV1("Bob Charlee");
	}

	@GetMapping(value = "person/produce", produces = "application/vnd.company.app-v2+json")
	public PersonV2 produceV2() {
		// TODO Auto-generated method stub
		return new PersonV2(new Name("Bob", "Charlee"));
	}
}
