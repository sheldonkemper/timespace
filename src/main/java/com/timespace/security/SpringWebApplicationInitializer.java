package com.timespace.security;
import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@Order(1) // Filters declared at the Dispatcher initializer should be registered first
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

    public static final String CHARACTER_ENCODING = "UTF-8";


    public SpringWebApplicationInitializer() 
    {
        super();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() 
    {
        return new Class<?>[] { SpringSecurityConfig.class };
    }

    @Override
    protected String[] getServletMappings() 
    {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() 
    {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }

	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}