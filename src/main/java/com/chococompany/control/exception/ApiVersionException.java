package com.chococompany.control.exception;

import java.io.Serializable;

public class ApiVersionException extends Exception implements Serializable
{
    private static final long serialVersionUID = 1809878123794L;

    public ApiVersionException() {
        super();
    }
    public ApiVersionException(String msg) {
        super(msg);
    }
    public ApiVersionException(String msg, Exception e)  {
        super(msg, e);
    }
}
