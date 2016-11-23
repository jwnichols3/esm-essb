package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Define legal buss module types. Treat as enumerated type.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class BussModule implements Serializable {
    
    /**
     * Alarm Point Drain Buss Module
     */
    public static final BussModule AP_DRAIN = new BussModule("AP_DRAIN");

    /**
     * DataMap Buss Module
     */
    public static final BussModule DATA_MAP = new BussModule("DATA_MAP");

    /**
     * Dispatcher Buss Module
     */
    public static final BussModule DISPATCHER = new BussModule("DISPATCHER");

    /**
     * Event Mine Buss Module
     */
    public static final BussModule EVENT_MINE = new BussModule("EVENT_MINE");

    /**
     * Modifier Buss Module
     */
    public static final BussModule MODIFIER = new BussModule("MODIFIER");

    /**
     * Notifier Buss Module
     */
    public static final BussModule NOTIFIER = new BussModule("NOTIFIER");
    
    /**
     * Openview Acknowledge Buss Module
     */
    public static final BussModule OPENVIEW_ACKNOWLEDGE = new BussModule("OPENVIEW_ACKNOWLEDGE");
    
    /**
     * Openview Acknowledge Buss Module
     */
    public static final BussModule SUPPRESSION_EMAIL = new BussModule("SUPPRESSION_EMAIL");
    
    /**
     * Service Center Drain Buss Module
     */
    public static final BussModule SC_DRAIN = new BussModule("SC_DRAIN");
    
    /**
     * Service Center Buffer Buss Module
     */
    public static final BussModule SC_BUFFER = new BussModule("SC_BUFFER");
    
    /**
     * Suppression drain used to notify creators of expiring suppressions
     */
    // public static final BussModule SUPPRESS_DRAIN = new BussModule("SUPPRESSION_DRAIN");
    
    /**
     * Storm Buss Module
     */
    public static final BussModule STORM = new BussModule("STORM");
    
    /**
     * Suppression Buss Module
     */
    public static final BussModule SUPPRESSION = new BussModule("SUPPRESSION");

    /**
     * Suppression Drain Buss Module
     */
    public static final BussModule SUPPRESSION_DRAIN = new BussModule("SUPPRESSION_DRAIN");

    /**
     * Test Buss Module
     */
    public static final BussModule TEST_TOPIC = new BussModule("TEST_TOPIC");
    
    /**
     * Throttle Buss Module
     */
    public static final BussModule THROTTLE = new BussModule("THROTTLE");

    /**
     * Unknown Buss Module (used for initialization)
     */
    public static final BussModule UNKNOWN = new BussModule("UNKNOWN");

    public static final BussModule SC_GATEWAY_CREATE = new BussModule ( "SC_GATEWAY_CREATE" );
    public static final BussModule SC_GATEWAY_UPDATE = new BussModule ( "SC_GATEWAY_UPDATE" );

    /**
     * Return a string representing the state (value) of this instance.
     * 
     * @return a string representing the state (value) of this instance.
     */
    public String toString() {
        return(_value);
    }

    /**
     * Return the type corresponding to arg, or null if not found.
     * 
     * @param arg type (key) to search for.
     * @return the type corresponding to arg, or null if not found.
     */
    public static BussModule getInstance(String arg) {
        return((BussModule) _instances.get(arg));
    }

    /**
     * Return all instances
     * 
     * @return all instances
     */
    public static Map getAll() {
        return(_instances);
    }

    /**
     * Private ctor
     * 
     * @param arg buss module name
     */
    private BussModule(String arg) {
        _value = arg;
    }

    /**
     * Return hash code for this object, based upon buss module name
     * 
     * @return hash code for this object
     */
    public int hashCode() {
        return(31 * _value.hashCode());
    }

    /**
     * Return true if objects match
     * 
     * @param arg test candidate
     * @return true if periods match
     * @throws ClassCastException if arg cannot be cast
     */
    public boolean equals(Object arg) {
        if (arg == null) {
            return(false);
        }

        BussModule temp = (BussModule) arg;
        
        return(_value.equals(temp._value));
    }

    /**
     * BussModule value.
     */
    private final String _value;

    /**
     * Map of instances, employed to look up types.
     */
    private static final Map<String, BussModule> _instances = new HashMap<String, BussModule>();

    /**
     * Serial version identifier. 
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;

    /**
     * Instantiate all Buss Module values.
     */
    static {
        _instances.put(AP_DRAIN.toString(),             AP_DRAIN             );
        _instances.put(DATA_MAP.toString(),             DATA_MAP             );
        _instances.put(DISPATCHER.toString(),           DISPATCHER           );
        _instances.put(EVENT_MINE.toString(),           EVENT_MINE           );
        _instances.put(MODIFIER.toString(),             MODIFIER             );
        _instances.put(NOTIFIER.toString(),             NOTIFIER             );
        _instances.put(SC_DRAIN.toString(),             SC_DRAIN             );
        _instances.put(OPENVIEW_ACKNOWLEDGE.toString(), OPENVIEW_ACKNOWLEDGE );
        _instances.put(SUPPRESSION_EMAIL.toString(),    SUPPRESSION_EMAIL    );
        _instances.put(SC_BUFFER.toString(),            SC_BUFFER            );
        _instances.put(STORM.toString(),                STORM                );
        _instances.put(SUPPRESSION.toString(),          SUPPRESSION          );    
        _instances.put(SUPPRESSION_DRAIN.toString(),    SUPPRESSION_DRAIN    );
        _instances.put(TEST_TOPIC.toString(),           TEST_TOPIC           );
        _instances.put(THROTTLE.toString(),             THROTTLE             );
        _instances.put(UNKNOWN.toString(),              UNKNOWN              );
        _instances.put(SC_GATEWAY_CREATE.toString(),    SC_GATEWAY_CREATE    );
        _instances.put(SC_GATEWAY_UPDATE.toString(),    SC_GATEWAY_UPDATE    );
    }
}
