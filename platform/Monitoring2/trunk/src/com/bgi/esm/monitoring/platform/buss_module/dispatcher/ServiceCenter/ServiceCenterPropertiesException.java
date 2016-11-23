package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
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
