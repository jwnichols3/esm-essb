package tools.struts.controller;

import java.util.Vector;

import tools.struts.controller.StrutsActionForward;

public class StrutsActionConfig
{
    private String attribute  = null;
    private String class_name = null;
    private String forward    = null;
    private String include    = null;
    private String input      = null;
    private String name       = null;
    private String path       = null;
    private String parameter  = null;
    private String roles      = null;
    private String type       = null;
    private String scope      = null;
    private String unknown    = null;
    private String output     = null;
    private boolean validate  = false;
    private Vector <StrutsActionForward> action_forwards = new Vector <StrutsActionForward> ();

    /**
     * Basic constructor that constructs the object.  No parameters are set to any default values.
     */
    public StrutsActionConfig()
    {
    }

    /**
     * Sets the &ldquo;attribute&rdquo; value of the Struts Action object
     *
     * @param new_attribute the &ldquo;attribute&rdquo; value of the Struts Action object
     */
    public void setAttribute ( String new_attribute )
    {
        attribute = new_attribute;
    }

    /**
     * Gets the &ldquo;attribute&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;attribute&rdquo; value of the Struts Action object
     */
    public String getAttribute()
    {
        return attribute;
    }

    /**
     * Sets the &ldquo;className&rdquo; value of the Struts Action object
     *
     * @param new_classname the &ldquo;className&rdquo; value of the Struts Action object
     */
    public void setClassName ( String new_classname )
    {
        class_name = new_classname;
    }

    /**
     * Gets the &ldquo;className&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;className&rdquo; value of the Struts Action object
     */
    public String getClassName()
    {
        return class_name;
    }

    /**
     * Sets the &ldquo;forward&rdquo; value of the Struts Action object
     *
     * @param new_forward the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setForward ( String new_forward )
    {
        forward = new_forward;
    }

    /**
     * Gets the &ldquo;forward&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public String getForward()
    {
        return forward;
    }

    /**
     * Sets the &ldquo;include&rdquo; value of the Struts Action object
     *
     * @param new_include the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setInclude ( String new_include )
    {
        include = new_include;
    }

    /**
     * Gets the &ldquo;include&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;include&rdquo; value of the Struts Action object
     */
    public String getInclude()
    {
        return include;
    }

    /**
     * Sets the &ldquo;input&rdquo; value of the Struts Action object
     *
     * @param new_input the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setInput ( String new_input )
    {
        input = new_input;
    }

    /**
     * Gets the &ldquo;input&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;input&rdquo; value of the Struts Action object
     */
    public String getInput()
    {
        return input;
    }

    /**
     * Sets the &ldquo;name&rdquo; value of the Struts Action object
     *
     * @param new_name the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setName ( String new_name )
    {
        name = new_name;
    }

    /**
     * Gets the &ldquo;name&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;name&rdquo; value of the Struts Action object
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the &ldquo;path&rdquo; value of the Struts Action object
     *
     * @param new_path the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setPath ( String new_path )
    {
        path = new_path;
    }

    /**
     * Gets the &ldquo;path&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;path&rdquo; value of the Struts Action object
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Sets the &ldquo;parameter&rdquo; value of the Struts Action object
     *
     * @param new_parameter the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setParameter ( String new_parameter )
    {
        parameter = new_parameter;
    }

    /**
     * Gets the &ldquo;parameter&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;parameter&rdquo; value of the Struts Action object
     */
    public String getParameter()
    {
        return parameter;
    }

    /**
     * Sets the &ldquo;roles&rdquo; value of the Struts Action object
     *
     * @param new_roles the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setRoles ( String new_roles )
    {
        roles = new_roles;
    }

    /**
     * Gets the &ldquo;roles&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;roles&rdquo; value of the Struts Action object
     */
    public String getRoles()
    {
        return roles;
    }

    /**
     * Sets the &ldquo;type&rdquo; value of the Struts Action object
     *
     * @param new_type the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setType ( String new_type )
    {
        type = new_type;
    }

    /**
     * Gets the &ldquo;type&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;type&rdquo; value of the Struts Action object
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the &ldquo;scope&rdquo; value of the Struts Action object
     *
     * @param new_scope the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setScope ( String new_scope )
    {
        scope = new_scope;
    }

    /**
     * Gets the &ldquo;scope&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;scope&rdquo; value of the Struts Action object
     */
    public String getScope ()
    {
        return scope;
    }

    /**
     * Sets the &ldquo;unknown&rdquo; value of the Struts Action object
     *
     * @param new_unknown the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setUnknown ( String new_unknown )
    {
        unknown = new_unknown;
    }

    /**
     * Gets the &ldquo;unknown&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;unknown&rdquo; value of the Struts Action object
     */
    public String getUnknown()
    {
        return unknown;
    }

    /**
     * Sets the &ldquo;validate&rdquo; value of the Struts Action object
     *
     * @param new_validate the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setValidate ( String new_validate )
    {
        validate = new_validate.equals ( "true" );
    }

    /**
     * Sets the &ldquo;validate&rdquo; value of the Struts Action object
     *
     * @param new_validate the &ldquo;forward&rdquo; value of the Struts Action object
     */
    public void setValidate ( boolean new_validate )
    {
        validate = new_validate;
    }

    /**
     * Gets the &ldquo;validate&rdquo; value of the Struts Action object
     *
     * @return the &ldquo;validate&rdquo; value of the Struts Action object
     */
    public boolean getValidate()
    {
        return validate;
    }

    /**
     * Adds an ActionForward to this action
     *
     * @param new_action_forward the new StrutsActionForward to associate with this StrutsAction object
     */
    public void addActionForward ( StrutsActionForward new_action_forward )
    {
        action_forwards.add ( new_action_forward );
    }

    /**
     * Retrieve the StrutsActionForward objects associated with this StrutsAction
     *
     * @return a Vector containing allt he StrutsActionForward objects associated with this object
     */
    public Vector getActionForwards()
    {
        return action_forwards;
    }

    /**
     * Constructs the Action string that goes into the ActionMapping part of struts-config.xml.
     *
     * @return the Action string that goes into the ActionMapping part of struts-config.xml
     */
    public String toString()
    {
        if ( null == output )
        {
        }

        return output;
    }
};
