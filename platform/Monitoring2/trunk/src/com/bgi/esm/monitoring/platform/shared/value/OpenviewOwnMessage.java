package com.bgi.esm.monitoring.platform.shared.value;

public class OpenviewOwnMessage extends AbstractMessage
{
    protected String MessageId = null;
    protected String Owner     = null;
    protected String Password  = null;

    private OpenviewOwnMessage()
    {
    }

    public OpenviewOwnMessage ( String username, String password )
    {
        Owner    = username;
        Password = password;
    }

    public void setOwner ( String owner )
    {
        Owner = owner;
    }

    public String getOwner()
    {
        return Owner;
    }

    public void setPassword ( String password )
    {
        Password = password;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setMessageId ( String message_id )
    {
        MessageId = message_id;
    }

    public String getMessageId()
    {
        return MessageId;
    }

    public String toString()
    {
        throw new RuntimeException ( "Needs to be implemented" );
    }

    public String toXml()
    {
        StringBuilder message = new StringBuilder();
        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
		message.append ( LINE_SEPARATOR );
		message.append ( "<ovMessageModifyRequest xmlns=\"http://openview.hp.com/xmlns/ico/message\" xmlns:ovit=\"http://openview.hp.com/xmlns/ico/types\" version=\"1.0\">" );
		message.append ( LINE_SEPARATOR );

        //*
        message.append ( "  <ovit:auth>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "    <ovit:data>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "      <ovit:name>user</ovit:name>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "      <ovit:value type=\"string\">" );
        message.append ( Owner );
        message.append ( "</ovit:value>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "    </ovit:data>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "    <ovit:data>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "      <ovit:name>password</ovit:name>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "      <ovit:value type=\"string\">" );
        message.append ( Password );
        message.append ( "</ovit:value>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "    </ovit:data>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "  </ovit:auth>" );
		message.append ( LINE_SEPARATOR );
        //*/


		message.append ( "  <messageUUID>" );
		message.append ( MessageId );
		message.append ( "</messageUUID>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "  <action>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "    <modifyState>Own</modifyState>" );
		message.append ( LINE_SEPARATOR );
        message.append ( "  </action>" );
		message.append ( LINE_SEPARATOR );
		message.append ( "</ovMessageModifyRequest>" );

        return message.toString();
    }
};

/**
 *
 *

Here is an example of a message own request. The request must contain an HP Operations Manager or HP Operations Manager for Windows message UUID that exists in the HP Operations Manager or HP Operations Manager for Windows server to which the request is sent.

<?xml version="1.0" encoding="UTF-8"?>
<ovMessageModifyRequest
xmlns=
"http://h20229.www2.hp.com/xmlns/ico/message"
xmlns:ovit="http://h20229.www2.hp.com/xmlns/ico/types"
version="1.0">
<!--
The HP Operations Manager for Windows user and password 
can optionally be specified within this request. If these
credentials are specified here, they will override
any user and password credentials specified in the
pluglet configuration file (for this request
only).  When specifying the HP Operations Manager for 
Windows user and password in this request, be sure to 
use a secure transport such as HTTPS to ensure that this
sensitive information is as secure as possible.
<ovit:auth>
<ovit:data>
<ovit:name>
user
</ovit:name>
<ovit:value type="string">
user
</ovit:value>
</ovit:data>
<ovit:data>
<ovit:name>
password
</ovit:name>
<ovit:value type="string">
password
</ovit:value>
</ovit:data>
</ovit:auth>
-->
<!--
The UUID of the HP Operations Manager message to modify
-->
You may want to modify this.
<messageUUID>
75ba8cf4-9a9b-71d6-1350-0f0278270000
</messageUUID>
<!--
The modify action to perform on the HP Operations Manager 
message. Only one action can be specified per request!
-->
<action>
<modifyState>Own</modifyState>
</action>
</ovMessageModifyRequest >
 *
 * 
 */

