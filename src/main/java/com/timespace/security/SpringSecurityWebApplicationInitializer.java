package com.timespace.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


@Order(2) // Filters declared at the Dispatcher initializer should be registered first
public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer 
{

    public SpringSecurityWebApplicationInitializer() 
    {
        super();
    }

}