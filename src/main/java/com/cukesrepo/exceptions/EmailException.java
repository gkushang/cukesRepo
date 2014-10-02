package com.cukesrepo.exceptions;


public class EmailException extends Exception
{

    public EmailException(String message, Exception e)
    {
        super(message, e);
    }
}
