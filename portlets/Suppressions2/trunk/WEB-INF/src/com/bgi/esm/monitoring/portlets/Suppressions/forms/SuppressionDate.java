package com.bgi.esm.monitoring.portlets.Suppressions.forms;

public interface SuppressionDate
{
    public void setSupStartYear ( String param );

    public void setSupStartMonth ( String param );

    public void setSupStartDate ( String param );

    public void setSupStartHour ( String param );

    public void setSupStartMinute ( String param );

    public void setSupStartAmpm ( String param );

    public void setStartChoice ( String param );

    public String getSupStartYear();

    public String getSupStartMonth();
    
    public String getSupStartDate();
    
    public String getSupStartHour();

    public String getSupStartMinute();

    public String getSupStartAmpm();

    public String getStartChoice();

    public void setSupEndYear ( String param );

    public void setSupEndMonth ( String param );

    public void setSupEndDate ( String param );

    public void setSupEndHour ( String param );

    public void setSupEndMinute ( String param );

    public void setSupEndAmpm ( String param );

    public void setEndChoice ( String param );

    public String getSupEndYear();

    public String getSupEndMonth();
    
    public String getSupEndDate();
    
    public String getSupEndHour();

    public String getSupEndMinute();

    public String getSupEndAmpm();

    public String getEndChoice();

    public String getEndChoiceNum();

    public String getEndChoiceUnit();
};

