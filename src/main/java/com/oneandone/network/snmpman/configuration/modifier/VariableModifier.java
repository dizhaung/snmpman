package com.oneandone.network.snmpman.configuration.modifier;

import com.oneandone.network.snmpman.configuration.type.ModifierProperties;
import org.snmp4j.smi.Variable;

import java.util.Map;
import java.util.Properties;

/**
 * TODO
 */
public interface VariableModifier<T extends Variable> {
    public T modify(final T variable);
    public default void init(final ModifierProperties properties) { }
}