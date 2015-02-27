package com.oneandone.network.snmpman.configuration.modifier;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.asn1.BERInputStream;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * A modified variable will change it's value on every value call.
 */
@Slf4j @EqualsAndHashCode(of = "variable")
public class ModifiedVariable implements Variable, Cloneable {

    /** The list of modifiers that modify the {@link #variable}. */
    private final List<VariableModifier> modifiers;

    /** The variable. */
    private Variable variable;

    /**
     * Constructs a new modified variable.
     * <p/>
     * A modified variable will dynamically change it's value on each value call.
     *
     * @param variable  the initial variable to modify
     * @param modifiers the list of modifiers that should modify this variable
     */
    public ModifiedVariable(final Variable variable, final List<VariableModifier> modifiers) {
        this.variable = variable;
        this.modifiers = modifiers;
    }

    @Override
    public int compareTo(final Variable variable) {
        return variable.compareTo(variable);
    }

    @SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException", "CloneDoesntCallSuperClone", "unchecked"})
    @Override
    public Object clone() {
        log.trace("variable {} will be cloned", variable);
        for (final VariableModifier modifier : modifiers) {
            this.variable = modifier.modify(variable);
        }
        return this.variable.clone();
    }

    @Override
    public int getSyntax() {
        log.trace("syntax of variable {} will be retrieved", variable);
        return variable.getSyntax();
    }

    @Override
    public boolean isException() {
        log.trace("asking if variable {} is an exception", variable);
        return variable.isException();
    }

    @Override
    public String toString() {
        log.trace("toString() called for variable {}", variable);
        return variable.toString();
    }

    @Override
    public int toInt() {
        log.trace("integer value of variable {} will be returned", variable);
        return variable.toInt();
    }

    @Override
    public long toLong() {
        log.trace("long value of variable {} will be returned", variable);
        return variable.toLong();
    }

    @Override
    public String getSyntaxString() {
        log.trace("syntax string of variable {} will be retrieved", variable);
        return variable.getSyntaxString();
    }

    @Override
    public OID toSubIndex(final boolean b) {
        return variable.toSubIndex(b);
    }

    @Override
    public void fromSubIndex(final OID oid, final boolean b) {
        variable.fromSubIndex(oid, b);
    }

    @Override
    public boolean isDynamic() {
        log.trace("asking if variable {} is dynamic", variable);
        return variable.isDynamic();
    }

    @Override
    public int getBERLength() {
        log.trace("BER length of variable {} will be retrieved", variable);
        return variable.getBERLength();
    }

    @Override
    public int getBERPayloadLength() {
        log.trace("BER payload length of variable {} will be retrieved", variable);
        return variable.getBERPayloadLength();
    }

    @Override
    public void decodeBER(final BERInputStream berInputStream) throws IOException {
        log.trace("BER will be decoded for variable {}", variable);
        variable.decodeBER(berInputStream);
    }

    @Override
    public void encodeBER(final OutputStream outputStream) throws IOException {
        log.trace("BER will be encoded for variable {}", variable);
        variable.encodeBER(outputStream);
    }
}