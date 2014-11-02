package com.cukesrepo.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class MessageSecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer
{

    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{RootConfiguration.class};
    }

}
