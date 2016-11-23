package com.bgi.esm.monitoring.platform.shared.value;

public class OpenviewAddAnnotationTextMessage
{
};

/**
 *  URL: http://htn473-f-drc.cce.hp.com/drc/resources/ovi34/OVI_UserGuide/Integrate_OVO/Examples/ExampleAddAnnotationTextRequest.jsp
 *
 *
Here is an example of a request to add annotation text to a message. The request must contain an HP Operations Manager or HP Operations Manager for Windows message UUID that exists in the HP Operations Manager or HP Operations Manager for Windows server to which the request is sent.

<?xml version="1.0" encoding="UTF-8"?>
<ovMessageModifyRequest  xmlns=
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
<ovit:name>user</ovit:name>
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
The modify action to perform on the HP Operations Manager message.
Only one action can be specified per request!
-->
<action>
<!--
Add annotation text to the HP Operations Manager message
-->
<data>
<name>annotation</name>
<value type="string">
New annotation text
</value>
</data>
</action>
</ovMessageModifyRequest>
 *
 * 
 */

