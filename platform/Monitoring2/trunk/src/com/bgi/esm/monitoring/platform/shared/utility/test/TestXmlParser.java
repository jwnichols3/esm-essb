package com.bgi.esm.monitoring.platform.shared.utility.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.shared.utility.OviRuleSet;
import com.bgi.esm.monitoring.platform.shared.utility.OviChangedEventRuleSet;
import com.bgi.esm.monitoring.platform.shared.utility.XmlParser;
import com.bgi.esm.monitoring.platform.shared.value.ParsedOvi;
import com.bgi.esm.monitoring.platform.shared.value.XmlIf;

import org.apache.commons.digester.Digester;


public class TestXmlParser extends TestCase
{
    final private static Logger _log   = Logger.getLogger ( TestXmlParser.class );

    private static XmlParser xmlParser = null;
    private static Digester digester   = null;

    public TestXmlParser ( String param )
    {
        super ( param );

        xmlParser = new XmlParser();
    }

    public void testAlternateParsing()
    {
        _log.info ( "**************************************** TestXmlParser::testAlternateParsing()" );

        digester = new Digester();
        digester.setValidating   ( false );
        digester.addObjectCreate ( "ovMessageChangeEvent", "com.bgi.esm.monitoring.platform.shared.value.ParsedOvi" );
        digester.addCallMethod   ( "ovMessageChangeEvent/changeEventTypeList/changeEventType", "setChangeEventType", 0 );
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/messageUUID",        "setMessageId",      0 );

        try
        {
            //ByteArrayInputStream bais = new ByteArrayInputStream ( createAnnotationMessage().getBytes() );
            ByteArrayInputStream bais = new ByteArrayInputStream ( createOriginalMessage().getBytes() );

            XmlIf x = (XmlIf) digester.parse ( bais );
            ParsedOvi po = (ParsedOvi) x;

            if ( null != po )
            {
                _log.info ( "Constructor:" );
                _log.info ( "    message UUID:    " + po.getMessageId() );
                _log.info ( "    ChangeEventType: " + po.getChangeEventType() );
            }
            else
            {
                _log.info ( "Constructor: null object" );
            }
        }
        catch ( Exception exception )
        {
            _log.error ( "Exception detected in constructor", exception );
        }
    }

    private static void validateParsedOviObject ( final ParsedOvi po )
    {
        Vector <String> error_messages = new Vector <String> ();

        if ( null == po.getMessageId() )
        {
            error_messages.add ( "Could not get OVO message ID" );
        }
        if ( null == po.getTimeStamp() )
        {
            error_messages.add ( "Could not retrieve message timestamp" );
        }
        if ( null == po.getSeverity () )
        {
            error_messages.add ( "Could not retrieve event severity" );
        }
        if ( null == po.getMessageSource() )
        {
            error_messages.add ( "Could not obtain message source"  );
        }
        if ( null == po.getEventSource() )
        {
            error_messages.add ( "Could not get event source" );
        }
        if ( null == po.getState() )
        {
            error_messages.add ( "Could not get event state" );
        }

        if ( error_messages.size() > 0 )
        {
            String log_error_message = constructLogErrorMessage ( error_messages );
            _log.error ( log_error_message );

            fail ( "Errors encountered in parsing message" );
        }
    }

    private static void logParsedOviObject ( final ParsedOvi po )
    {
        if ( _log.isInfoEnabled() )
        {
           if ( null != po )
            {
                _log.info ( "Parsed OVI message:" );
                _log.info ( "    Messasge ID:  " + po.getMessageId() );
                _log.info ( "    Timestamp:    " + po.getTimeStamp() );
                _log.info ( "    Severity:     " + po.getSeverity () );
                _log.info ( "    Msg Source:   " + po.getMessageSource() );
                _log.info ( "    Event Source: " + po.getEventSource() );
                _log.info ( "    State:        " + po.getState() );
            }
            else
            {
                _log.info ( "Parsed OVI message: null" );
            }
        }
    }

    private static String constructLogErrorMessage ( Vector <String> error_messages )
    {
        StringBuilder error_message   = new StringBuilder();
        Enumeration <String> elements = error_messages.elements();
        while ( elements.hasMoreElements() )
        {
            error_message.append ( elements.nextElement() );
            error_message.append ( "\n" );
        }

        return error_message.toString();
    }

    public void testDigesterOnOriginalMessageWithDefaultRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnOriginalMessageWithDefaultRules()" );

        String message   = createOriginalMessage();

        OviRuleSet rules = new OviRuleSet();
        XmlIf xml = xmlParser.xmlParser ( message, rules );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        logParsedOviObject ( po );
        validateParsedOviObject ( po );
    }

    public void testDigesterOnOriginalMessageWithChangeEventRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnOriginalMessageWithChangeEventRules()" );

        String message   = createOriginalMessage();

        XmlIf xml  = xmlParser.xmlParser ( message, new OviChangedEventRuleSet() );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        logParsedOviObject ( po );
        validateParsedOviObject ( po );

        //assertTrue ( "Change event rules should not apply to default message format", po.getMessageId() );
    }

    public void testDigesterOnAnnotationMessageWithDefaultRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnAnnotationMessageWithDefaultRules()" );

        String message = createAnnotationMessage();
        OviRuleSet rules = new OviRuleSet();
        XmlIf xml = xmlParser.xmlParser ( message, rules );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        logParsedOviObject ( po );
        validateParsedOviObject ( po );
    }

    public void testDigesterOnAnnotationMessageWithChangeEventRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnAnnotationMessageWithChangeEventRules()" );

        String message = createAnnotationMessage();
        OviChangedEventRuleSet rules = new OviChangedEventRuleSet();
        XmlIf xml = xmlParser.xmlParser ( message, rules );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        validateParsedOviObject ( po );
    }

    public void testDigesterOnOwnMessageWithDefaultRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnOwnMessageWithDefaultRules()" );

        String message = createOwnMessage();
        OviRuleSet rules = new OviRuleSet();
        XmlIf xml = xmlParser.xmlParser ( message, rules );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        validateParsedOviObject ( po );
    }

    public void testDigesterOnDuplicateMessageWithDefaultRules()
    {
        _log.info ( "**************************************** TestXmlParser::testDigesterOnDuplicateMessageDefaultRules()" );

        String message = createDuplicateChangeMessage();
        OviRuleSet rules = new OviRuleSet();
        XmlIf xml = xmlParser.xmlParser ( message, rules );

        assertNotNull ( "Could not parse message", xml );

        _log.info ( "Resulting class: " + xml.getClass().getName() );

        ParsedOvi po = (ParsedOvi) xml;
        validateParsedOviObject ( po );
    }

    private static String createOriginalMessage()
    {
        StringBuilder message = new StringBuilder();

        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                                       );
        message.append ( "<ovMessage xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">"   );
        message.append (    "<messageEnvelope>"                                                             );
        message.append (        "<messageUUID>07480dce-c5fa-71db-0317-4534661e0000</messageUUID>"           );
        message.append (        "<timeStamp>"                                                               );
        message.append (            "<seconds>1172536359</seconds>"                                         );
        message.append (            "<dateTime>2007-02-26T16:32:39-08:00</dateTime>"                        );
        message.append (        "</timeStamp>"                                                              );
        message.append (        "<severity>Normal</severity>"                                               );
        message.append (        "<messageSource>rdcuxsrv217</messageSource>"                                );
        message.append (        "<eventSource>rdcuxsrv217.insidelive.net</eventSource>"                     );
        message.append (        "<primaryMessageRepository>rdcuxsrv217</primaryMessageRepository>"          );
        message.append (        "<messageMetadataIdentifier/>"                                              );
        message.append (        "<managedEntityReference/>"                                                 );
        message.append (     "</messageEnvelope>"                                                           );
        message.append (     "<messageBody>"                                                                );
        message.append (         "<owner/>"                                                                 );
        message.append (         "<numberOfDuplicates>0</numberOfDuplicates>"                               );
        message.append (         "<messageKey>rdcuxsrv217:test:mytest</messageKey>"                         );
        message.append (         "<messageKeyRelation>"                                                     );
        message.append (         "</messageKeyRelation>"                                                    );
        message.append (         "<state>PENDING</state>"                                                   );
        message.append (         "<creatingCondition/>"                                                     );
        message.append (         "<messageData application=\"OVO\">"                                        );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>INSTANCE</name>"                                            );
        message.append (                 "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>"         );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>application</name>"                                         );
        message.append (                 "<value type=\"string\">SSM-no-app-def</value>"                    );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>group</name>"                                               );
        message.append (                 "<value type=\"string\">test</value>"                              );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>lastReceiveTimeSeconds</name>"                              );
        message.append (                 "<value type=\"long\">0</value>"                                   );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>object</name>"                                              );
        message.append (                 "<value type=\"string\">SSM-no-obj-def-CLI</value>"                );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>originalText</name>"                                        );
        message.append (                 "<value type=\"string\">Node:          \n"                         );
        message.append (                 "Message group: test\n"                                            );
        message.append (                 "Application:   SSM-no-app-def\n"                                  );
        message.append (                 "Object:        SSM-no-obj-def-CLI\n"                              );
        message.append (                 "Severity:      Normal\n"                                          );
        message.append (                 "Text:          CLI-test_111++k++mytest\n"                         );
        message.append (                 "</value>"                                                         );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>receiveTimeSeconds</name>"                                  );
        message.append (                 "<value type=\"long\">1172536359</value>"                          );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>serviceName</name>"                                         );
        message.append (                 "<value type=\"string\">INFRA@@rdcuxsrv217.insidelive.net</value>" );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>source</name>"                                              );
        message.append (                 "<value type=\"string\">SSM - opcmsg</value>"                      );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>text</name>"                                                );
        message.append (                 "<value type=\"string\">test_111</value>"                          );
        message.append (             "</data>"                                                              );
        message.append (             "<data>"                                                               );
        message.append (                 "<name>type</name>"                                                );
        message.append (                 "<value type=\"string\">"                                          );
        message.append (             "</value>"                                                             );
        message.append (             "</data>"                                                              );
        message.append (         "</messageData>"                                                           );
        message.append (         "<actionResult/>"                                                          );
        message.append (         "<messageMetadataAnnotation/>"                                             );
        message.append (     "</messageBody>"                                                               );
        message.append ( "</ovMessage>"                                                                     );

        return message.toString();
    }

    public static String createAnnotationMessage()
    {
        StringBuilder message = new StringBuilder();

        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                                                );
        message.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        message.append (     "<changeEventTypeList>"                                                                 );
        message.append (         "<changeEventType>AnnotationChange</changeEventType>"                               );
        message.append (     "</changeEventTypeList>"                                                                );
        message.append (     "<changeEventData application=\"OVO\">"                                                 );
        message.append (         "<timeStamp>"                                                                       );
        message.append (             "<seconds>1172536360</seconds>"                                                 );
        message.append (             "<dateTime>2007-02-26T16:32:40-08:00</dateTime>"                                );
        message.append (         "</timeStamp>"                                                                      );
        message.append (         "<dataList/>"                                                                       );
        message.append (     "</changeEventData>"                                                                    );
        message.append (     "<ovMessage version=\"1.0\">"                                                           );
        message.append (         "<messageEnvelope>"                                                                 );
        message.append (             "<messageUUID>07480dce-c5fa-71db-0317-4534661e0000</messageUUID>"               );
        message.append (             "<timeStamp>"                                                                   );
        message.append (                 "<seconds>1172536359</seconds>"                                             );
        message.append (                 "<dateTime>2007-02-26T16:32:39-08:00</dateTime>"                            );
        message.append (             "</timeStamp>"                                                                  );
        message.append (             "<severity>Normal</severity>"                                                   );
        message.append (             "<messageSource>rdcuxsrv217.insidelive.net</messageSource>"                     );
        message.append (             "<eventSource>rdcuxsrv217.insidelive.net</eventSource>"                         );
        message.append (             "<primaryMessageRepository/>"                                                   );
        message.append (             "<messageMetadataIdentifier/>"                                                  );
        message.append (             "<managedEntityReference/>"                                                     );
        message.append (         "</messageEnvelope>"                                                                );
        message.append (         "<messageBody>"                                                                     );
        message.append (             "<owner/>"                                                                      );
        message.append (             "<numberOfDuplicates>0</numberOfDuplicates>"                                    );
        message.append (             "<messageKey>rdcuxsrv217:test:mytest</messageKey>"                              );
        message.append (             "<messageKeyRelation>"                                                          );
        message.append (             "</messageKeyRelation>"                                                         );
        message.append (             "<state>ACTIVE</state>"                                                         );
        message.append (             "<creatingCondition/>"                                                          );
        message.append (             "<messageData application=\"OVO\">"                                             );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>INSTANCE</name>"                                                 );
        message.append (                     "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>"              );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>application</name>"                                              );
        message.append (                     "<value type=\"string\">SSM-no-app-def</value>"                         );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>group</name>"                                                    );
        message.append (                     "<value type=\"string\">test</value>"                                   );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>lastReceiveTimeSeconds</name>"                                   );
        message.append (                     "<value type=\"long\">1172536359</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>object</name>"                                                   );
        message.append (                     "<value type=\"string\">SSM-no-obj-def-CLI</value>"                     );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>originalText</name>"                                             );
        message.append (                     "<value type=\"string\">Node:          \n"                              );
        message.append (                     "Message group: test\n"                                                 );
        message.append (                     "Application:   SSM-no-app-def\n"                                       );
        message.append (                     "Object:        SSM-no-obj-def-CLI\n"                                   );
        message.append (                     "Severity:      Normal\n"                                               );
        message.append (                     "Text:          CLI-test_111++k++mytest\n"                              );
        message.append (                     "</value>"                                                              );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>receiveTimeSeconds</name>"                                       );
        message.append (                     "<value type=\"long\">1172536359</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>serviceName</name>"                                              );
        message.append (                     "<value type=\"string\">INFRA@@rdcuxsrv217.insidelive.net</value>"      );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>source</name>"                                                   );
        message.append (                     "<value type=\"string\">SSM - opcmsg</value>"                           );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>text</name>"                                                     );
        message.append (                     "<value type=\"string\">test_111</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>type</name>"                                                     );
        message.append (                     "<value type=\"string\">"                                               );
        message.append (                 "</value>"                                                                  );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data sequenceNumber=\"1\">"                                               );
        message.append (                     "<name>annotation</name>"                                               );
        message.append (                     "<value type=\"string\">Sent to AP group: </value>"                     );
        message.append (                 "</data>"                                                                   );
        message.append (             "</messageData>"                                                                );
        message.append (             "<actionResult/>"                                                               );
        message.append (             "<messageMetadataAnnotation/>"                                                  );
        message.append (         "</messageBody>"                                                                    );
        message.append (     "</ovMessage>"                                                                          );
        message.append ( "</ovMessageChangeEvent>"                                                                   );

        return message.toString();
    }

    public static String createOwnMessage()
    {
        StringBuilder message = new StringBuilder();

        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                                                );
        message.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        message.append (     "<changeEventTypeList>"                                                                 );
        message.append (         "<changeEventType>Own</changeEventType>"                                            );
        message.append (         "</changeEventTypeList>"                                                            );
        message.append (         "<changeEventData application=\"OVO\">"                                             );
        message.append (             "<timeStamp>"                                                                   );
        message.append (                 "<seconds>1172536361</seconds>"                                             );
        message.append (                 "<dateTime>2007-02-26T16:32:41-08:00</dateTime>"                            );
        message.append (             "</timeStamp>"                                                                  );
        message.append (             "<dataList>"                                                                    );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>operator</name>"                                                 );
        message.append (                     "<value type=\"string\">alarmpoint</value>"                             );
        message.append (                 "</data>"                                                                   );
        message.append (             "</dataList>"                                                                   );
        message.append (         "</changeEventData>"                                                                );
        message.append (     "<ovMessage version=\"1.0\">"                                                           );
        message.append (     "<messageEnvelope>"                                                                     );
        message.append (         "<messageUUID>07480dce-c5fa-71db-0317-4534661e0000</messageUUID>"                   );
        message.append (         "<timeStamp>"                                                                       );
        message.append (             "<seconds>1172536359</seconds>"                                                 );
        message.append (             "<dateTime>2007-02-26T16:32:39-08:00</dateTime>"                                );
        message.append (         "</timeStamp>"                                                                      );
        message.append (         "<severity>Normal</severity>"                                                       );
        message.append (         "<messageSource>rdcuxsrv217.insidelive.net</messageSource>"                         );
        message.append (         "<eventSource>rdcuxsrv217.insidelive.net</eventSource>"                             );
        message.append (         "<primaryMessageRepository/>"                                                       );
        message.append (         "<messageMetadataIdentifier/>"                                                      );
        message.append (         "<managedEntityReference/>"                                                         );
        message.append (     "</messageEnvelope>"                                                                    );
        message.append (     "<messageBody>"                                                                         );
        message.append (         "<owner>alarmpoint</owner>"                                                         );
        message.append (         "<numberOfDuplicates>0</numberOfDuplicates>"                                        );
        message.append (         "<messageKey>rdcuxsrv217:test:mytest</messageKey>"                                  );
        message.append (         "<messageKeyRelation>"                                                              );
        message.append (         "</messageKeyRelation>"                                                             );
        message.append (         "<state>OWNED</state>"                                                              );
        message.append (         "<creatingCondition/>"                                                              );
        message.append (         "<messageData application=\"OVO\">"                                                 );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>INSTANCE</name>"                                                     );
        message.append (                 "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>"                  );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>application</name>"                                                  );
        message.append (                 "<value type=\"string\">SSM-no-app-def</value>"                             );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>group</name>"                                                        );
        message.append (                 "<value type=\"string\">test</value>"                                       );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>lastReceiveTimeSeconds</name>"                                       );
        message.append (                 "<value type=\"long\">1172536359</value>"                                   );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>object</name>"                                                       );
        message.append (                 "<value type=\"string\">SSM-no-obj-def-CLI</value>"                         );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>originalText</name>"                                                 );
        message.append (                 "<value type=\"string\">Node:          \n"                                  );
        message.append (                 "Message group: test\n"                                                     );
        message.append (                 "Application:   SSM-no-app-def\n"                                           );
        message.append (                 "Object:        SSM-no-obj-def-CLI\n"                                       );
        message.append (                 "Severity:      Normal\n"                                                   );
        message.append (                 "Text:          CLI-test_111++k++mytest\n"                                  );
        message.append (                 "</value>"                                                                  );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>receiveTimeSeconds</name>"                                           );
        message.append (                 "<value type=\"long\">1172536359</value>"                                   );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>serviceName</name>"                                                  );
        message.append (                 "<value type=\"string\">INFRA@@rdcuxsrv217.insidelive.net</value>"          );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>source</name>"                                                       );
        message.append (                 "<value type=\"string\">SSM - opcmsg</value>"                               );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>text</name>"                                                         );
        message.append (                 "<value type=\"string\">test_111</value>"                                   );
        message.append (             "</data>"                                                                       );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>type</name>"                                                         );
        message.append (                 "<value type=\"string\">"                                                   );
        message.append (                 "</value>"                                                                  );
        message.append (             "</data>"                                                                       );
        message.append (             "<data sequenceNumber=\"1\">"                                                   );
        message.append (                 "<name>annotation</name>"                                                   );
        message.append (                 "<value type=\"string\">Sent to AP group: </value>"                         );
        message.append (             "</data>"                                                                       );
        message.append (             "</messageData>"                                                                );
        message.append (         "<actionResult/>"                                                                   );
        message.append (         "<messageMetadataAnnotation/>"                                                      );
        message.append (     "</messageBody>"                                                                        );
        message.append (     "</ovMessage>"                                                                          );
        message.append ( "</ovMessageChangeEvent>"                                                                   );

        return message.toString();
    }

    public static String createDuplicateChangeMessage()
    {
        StringBuilder message = new StringBuilder();

        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                                                );
        message.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        message.append (     "<changeEventTypeList>"                                                                 );
        message.append (         "<changeEventType>Own</changeEventType>"                                            );
        message.append (     "</changeEventTypeList>"                                                                );
        message.append (     "<changeEventData application=\"OVO\">"                                                 );
        message.append (         "<timeStamp>"                                                                       );
        message.append (             "<seconds>1172536361</seconds>"                                                 );
        message.append (             "<dateTime>2007-02-26T16:32:41-08:00</dateTime>"                                );
        message.append (         "</timeStamp>"                                                                      );
        message.append (         "<dataList>"                                                                        );
        message.append (             "<data>"                                                                        );
        message.append (                 "<name>operator</name>"                                                     );
        message.append (                 "<value type=\"string\">alarmpoint</value>"                                 );
        message.append (             "</data>"                                                                       );
        message.append (         "</dataList>"                                                                       );
        message.append (     "</changeEventData>"                                                                    );
        message.append (     "<ovMessage version=\"1.0\">"                                                           );
        message.append (         "<messageEnvelope>"                                                                 );
        message.append (             "<messageUUID>07480dce-c5fa-71db-0317-4534661e0000</messageUUID>"               );
        message.append (             "<timeStamp>"                                                                   );
        message.append (             "<seconds>1172536359</seconds>"                                                 );
        message.append (             "<dateTime>2007-02-26T16:32:39-08:00</dateTime>"                                );
        message.append (             "</timeStamp>"                                                                  );
        message.append (             "<severity>Normal</severity>"                                                   );
        message.append (             "<messageSource>rdcuxsrv217.insidelive.net</messageSource>"                     );
        message.append (             "<eventSource>rdcuxsrv217.insidelive.net</eventSource>"                         );
        message.append (             "<primaryMessageRepository/>"                                                   );
        message.append (             "<messageMetadataIdentifier/>"                                                  );
        message.append (             "<managedEntityReference/>"                                                     );
        message.append (         "</messageEnvelope>"                                                                );
        message.append (         "<messageBody>"                                                                     );
        message.append (             "<owner>alarmpoint</owner>"                                                     );
        message.append (             "<numberOfDuplicates>0</numberOfDuplicates>"                                    );
        message.append (             "<messageKey>rdcuxsrv217:test:mytest</messageKey>"                              );
        message.append (             "<messageKeyRelation>"                                                          );
        message.append (             "</messageKeyRelation>"                                                         );
        message.append (             "<state>OWNED</state>"                                                          );
        message.append (             "<creatingCondition/>"                                                          );
        message.append (             "<messageData application=\"OVO\">"                                             );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>INSTANCE</name>"                                                 );
        message.append (                     "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>"              );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>application</name>"                                              );
        message.append (                     "<value type=\"string\">SSM-no-app-def</value>"                         );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>group</name>"                                                    );
        message.append (                     "<value type=\"string\">test</value>"                                   );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>lastReceiveTimeSeconds</name>"                                   );
        message.append (                     "<value type=\"long\">1172536359</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>object</name>"                                                   );
        message.append (                     "<value type=\"string\">SSM-no-obj-def-CLI</value>"                     );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>originalText</name>"                                             );
        message.append (                     "<value type=\"string\">Node:          \n"                              );
        message.append (                     "Message group: test\n"                                                 );
        message.append (                     "Application:   SSM-no-app-def\n"                                       );
        message.append (                     "Object:        SSM-no-obj-def-CLI\n"                                   );
        message.append (                     "Severity:      Normal\n"                                               );
        message.append (                     "Text:          CLI-test_111++k++mytest\n"                              );
        message.append (                     "</value>"                                                              );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>receiveTimeSeconds</name>"                                       );
        message.append (                     "<value type=\"long\">1172536359</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>serviceName</name>"                                              );
        message.append (                     "<value type=\"string\">INFRA@@rdcuxsrv217.insidelive.net</value>"      );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>source</name>"                                                   );
        message.append (                     "<value type=\"string\">SSM - opcmsg</value>"                           );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>text</name>"                                                     );
        message.append (                     "<value type=\"string\">test_111</value>"                               );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data>"                                                                    );
        message.append (                     "<name>type</name>"                                                     );
        message.append (                     "<value type=\"string\">"                                               );
        message.append (                 "</value>"                                                                  );
        message.append (                 "</data>"                                                                   );
        message.append (                 "<data sequenceNumber=\"1\">"                                               );
        message.append (                     "<name>annotation</name>"                                               );
        message.append (                     "<value type=\"string\">Sent to AP group: </value>"                     );
        message.append (                 "</data>"                                                                   );
        message.append (             "</messageData>"                                                                );
        message.append (             "<actionResult/>"                                                               );
        message.append (             "<messageMetadataAnnotation/>"                                                  );
        message.append (             "</messageBody>"                                                                );
        message.append (         "</ovMessage>"                                                                      );
        message.append ( "</ovMessageChangeEvent>"                                                                   );

        return message.toString();
    }
};
