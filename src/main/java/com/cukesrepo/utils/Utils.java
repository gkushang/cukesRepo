package com.cukesrepo.utils;

import java.net.UnknownHostException;


public class Utils
{

    public final static String EMAIL_ELEMENT = "email-request";

    public static String getHostName()
    {
        java.net.InetAddress address = null;
        try
        {
            address = java.net.InetAddress.getLocalHost();
        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException("Unknown Host ", e);
        }
        return address.getCanonicalHostName() + ":8080";
    }
}
