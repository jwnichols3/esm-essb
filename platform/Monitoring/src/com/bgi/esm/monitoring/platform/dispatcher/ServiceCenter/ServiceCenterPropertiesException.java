package com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter;

public class ServiceCenterPropertiesException extends RuntimeException
{
    public ServiceCenterPropertiesException()
    {
        super();
    }

    public ServiceCenterPropertiesException(String message)
    {
        super(message);
    }

    public ServiceCenterPropertiesException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ServiceCenterPropertiesException(Throwable cause)
    {
        super(cause);
    }
}
