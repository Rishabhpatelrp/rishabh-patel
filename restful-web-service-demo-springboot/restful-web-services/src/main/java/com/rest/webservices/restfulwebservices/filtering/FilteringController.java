package com.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * filterig example
 * 
 * @author risha
 *
 */
@RestController
public class FilteringController {

	@GetMapping(path = "filter")
	public FilteringBean retriveStaticFilteringBean() {
		// TODO Auto-generated method stub
		return new FilteringBean("v1", "v2", "v3");
	}

	@GetMapping(path = "filter-list")
	public List<FilteringBean> retriveStaticFilteringListBean() {
		// TODO Auto-generated method stub
		return Arrays.asList(new FilteringBean("v1", "v2", "v3"), new FilteringBean("v12", "v22", "v32"));
	}

	@GetMapping(path = "filter-dynamic")
	public MappingJacksonValue retriveDynamicFilteringBean() {
		FilteringBean filteringBean = new FilteringBean("v1", "v2", "v3");
		return getMapping(filteringBean, "BeanFilter", new HashSet<>(Arrays.asList("value1", "value2")));
	}

	@GetMapping(path = "filter-list-dynamic")
	public MappingJacksonValue retriveDynamicFilteringListBean() {
		// TODO Auto-generated method stub
		List<FilteringBean> list = Arrays.asList(new FilteringBean("v1", "v2", "v3"),
				new FilteringBean("v12", "v22", "v32"));
		return getMapping(list, "BeanFilter", new HashSet<>(Arrays.asList("value1", "value2")));
	}

	/**
	 * dynamic bean filtering
	 * 
	 * @param object
	 * @param filterName
	 * @param fieldsToDisplay
	 * @return
	 */
	private MappingJacksonValue getMapping(Object object, String filterName, Set<String> fieldsToDisplay) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToDisplay);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, filter);
		MappingJacksonValue mapping = new MappingJacksonValue(object);
		mapping.setFilters(filterProvider);
		return mapping;
	}
}
