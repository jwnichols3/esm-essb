package com.bgi.esm.monitoring.platform.shared.value;

public class OpenviewInventoryMessage
{
};

/**
 * Taken from: http://htn473-f-drc.cce.hp.com/drc/resources/ovi34/OVI_UserGuide/Integrate_OVO/Pluglets/ovoinventorypluglet.jsp
 * 
 *
<?xml version="1.0" encoding="UTF-8"?>
<ovInventoryRequest xmlns="http://h20229.www2.hp.com/xmlns/ico/entity"
xmlns:ovit="http://h20229.www2.hp.com/xmlns/ico/types" version="1.0">
<requestDetails>
<!-- A timeStamp value must be included, but currently
it is NOT USED in request processing -->
<timeStamp>2003-09-30T13:30:47-05:00</timeStamp>
<action>create</action>
<entityType>Node</entityType>
</requestDetails>
<entityDetails>
<inventoryItem>
<ovit:data>
<!—- Node name can be specified as a hostname or
IP address. -->
<ovit:name>name</ovit:name>
<ovit:value type="string">systemA.company.com</ovit:value>
</ovit:data>
<ovit:data>
<!—- Node label must be 32 characters or less. -->
<ovit:name>label</ovit:name>
<ovit:value type="string">systemA</ovit:value>
</ovit:data>
<ovit:data>
<ovit:name>nodeGroup</ovit:name>
<ovit:value type="string">net_devices</ovit:value>
</ovit:data>
<ovit:structuredData>
<ovit:id>Node</ovit:id>
<ovit:type>machineConfig</ovit:type>
<!-- Allowable values are:
agentType machType  osName                osVersion
========  ========  ======                =========
RPC       PA-RISC   HPUX                  10, 11, 11.11, 11.20, or 11.22
HTTPS     PA-RISC   HPUX                  11, 11.11, or 11.23
RPC       PowerPC   AIX
HTTPS     PowerPC   AIX
RPC       Intel     Linux                 2.2 or 2.4
HTTPS     Intel     Linux                 2.4
RPC       Intel     Netware
RPC       Intel     Windows NT            4.0
HTTPS     Intel     Windows NT            4.0
RPC       Intel     Windows 2000          5.0
HTTPS     Intel     Windows 2000          5.0
RPC       Intel     Windows XP            5.1
HTTPS     Intel     Windows XP            5.1
RPC       Intel     Windows Server 2003   5.2
HTTPS     Intel     Windows Server 2003   5.2
RPC       SPARC     Solaris
HTTPS     SPARC     Solaris
HTTPS     Alpha     Tru64
Where osVersions are not specified, any version is allowed.
For combinations other than the ones specified, the node will
be added to HP Operations Manager as an Unknown machine type.
If agentType is not specified, RPC will be used. The HTTPS agentType
is only supported with HP Operations Manager 8.0 and above.
-->
<ovit:data>
<ovit:name>agentType</ovit:name>
<ovit:value type="string">RPC</ovit:value>
</ovit:data>
<ovit:data>
<ovit:name>machType</ovit:name>
<ovit:value type="string">Intel</ovit:value>
</ovit:data>
<ovit:data>
<ovit:name>osName</ovit:name>
<ovit:value type="string">Windows 2000</ovit:value>
</ovit:data>
<ovit:data>
<ovit:name>osVersion</ovit:name>
<ovit:value type="string">5.0</ovit:value>
</ovit:data>
</ovit:structuredData>
</inventoryItem>
</entityDetails>
</ovInventoryRequest>

The example inventory request message above adds the specified node with the hostname systemA.company.com to HP Operations Manager. In HP Operations Manager, the node will have the label systemA and be part of the net_devices node group; it will be displayed with a Windows 2000 icon. If there are no problems executing the preceding create request, the OvoInventoryPluglet returns a response message similar to this:

<?xml version="1.0" encoding="UTF-8"?>
<ovInventoryResponse xmlns="http://h20229.www2.hp.com/xmlns/ico/entity"
xmlns:ovit="http://h20229.www2.hp.com/xmlns/ico/types" version="1.0">
<entityDetails>
<inventoryItem>
<ovit:data>
<ovit:name>name</ovit:name>
<ovit:value type="string">systemA.company.com</ovit:value>
</ovit:data>
</inventoryItem>
</entityDetails>
</ovInventoryResponse>

Here is an example of a node delete request.

<?xml version="1.0" encoding="UTF-8"?>
<ovInventoryRequest xmlns="http://h20229.www2.hp.com/xmlns/ico/entity"
xmlns:ovit="http://h20229.www2.hp.com/xmlns/ico/types" version="1.0">
<requestDetails>
<!-- A timeStamp value must be included, but currently
it is NOT USED in request processing -->
<timeStamp>2003-09-30T13:30:47-05:00</timeStamp>
<action>delete</action>
<entityType>Node</entityType>
</requestDetails>
<entityDetails>
<inventoryItem>
<ovit:data>
<!-- Node name can be specified as a hostname
or IP address. -->
<ovit:name>name</ovit:name>
<ovit:value type="string">systemA.company.com</ovit:value>
</ovit:data>
</inventoryItem>
</entityDetails>
</ovInventoryRequest>

The example inventory request message above deletes the specified node with the hostname systemA.company.com from HP Operations Manager. If there are no problems executing the delete request, the OvoInventoryPluglet returns a response message similar to this:

<?xml version="1.0" encoding="UTF-8"?>
<ovInventoryResponse xmlns="http://h20229.www2.hp.com/xmlns/ico/entity"
xmlns:ovit="http://h20229.www2.hp.com/xmlns/ico/types" version="1.0">
<entityDetails>
<inventoryItem>
<ovit:data>
<ovit:name>name</ovit:name>
<ovit:value type="string">systemA.company.com</ovit:value>
</ovit:data>
</inventoryItem>
</entityDetails>
</ovInventoryResponse>
 **/
