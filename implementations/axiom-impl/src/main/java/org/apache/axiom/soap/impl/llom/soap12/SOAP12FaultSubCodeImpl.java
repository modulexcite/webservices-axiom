/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axiom.soap.impl.llom.soap12;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMCloneOptions;
import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLParserWrapper;
import org.apache.axiom.om.util.ElementHelper;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPFaultCode;
import org.apache.axiom.soap.SOAPFaultSubCode;
import org.apache.axiom.soap.SOAPFaultValue;
import org.apache.axiom.soap.SOAPProcessingException;
import org.apache.axiom.soap.impl.llom.SOAPElement;

public class SOAP12FaultSubCodeImpl extends SOAPElement implements SOAPFaultSubCode {
    private SOAPFaultValue value;
    private SOAPFaultSubCode subCode;
    
    //changed
    public SOAP12FaultSubCodeImpl(SOAPFaultCode parent, SOAPFactory factory)
            throws SOAPProcessingException {
        super(parent, SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, true, factory);
    }

    public SOAP12FaultSubCodeImpl(SOAPFactory factory) {
        super(SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, factory.getNamespace(), factory);
    }

    //changed
    public SOAP12FaultSubCodeImpl(SOAPFaultCode parent,
                                  OMXMLParserWrapper builder,
                                  SOAPFactory factory) {
        super(parent, SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, builder,
              factory);
    }

    public SOAP12FaultSubCodeImpl(SOAPFaultSubCode parent, SOAPFactory factory)
            throws SOAPProcessingException {
        super(parent, SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, true, factory);
    }

    public SOAP12FaultSubCodeImpl(SOAPFaultSubCode parent,
                                  OMXMLParserWrapper builder, SOAPFactory factory) {
        super(parent, SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, builder,
              factory);
    }

    protected void checkParent(OMElement parent) throws SOAPProcessingException {
        if (!((parent instanceof SOAP12FaultSubCodeImpl) ||
                (parent instanceof SOAP12FaultCodeImpl))) {
            throw new SOAPProcessingException(
                    "Expecting SOAP 1.2 implementation of SOAP FaultSubCode " +
                            "or SOAP FaultCodeValue as the parent. But received some " +
                            "other implementation");
        }
    }

    public void setSubCode(SOAPFaultSubCode subCode)
            throws SOAPProcessingException {
        if (!(subCode instanceof SOAP12FaultSubCodeImpl)) {
            throw new SOAPProcessingException(
                    "Expecting SOAP12FaultSubCodeImpl, got " + subCode.getClass());
        }
        ElementHelper.setNewElement(this, this.subCode, subCode);
    }

    public SOAPFaultSubCode getSubCode() {
        if (subCode == null) {
            subCode = (SOAPFaultSubCode)getFirstChildWithName(SOAP12Constants.QNAME_FAULT_SUBCODE);
        }
        return subCode;
    }

    public void setValue(SOAPFaultValue soapFaultSubCodeValue)
            throws SOAPProcessingException {
        if (!(soapFaultSubCodeValue instanceof SOAP12FaultValueImpl)) {
            throw new SOAPProcessingException(
                    "Expecting SOAP12FaultValueImpl, got " + soapFaultSubCodeValue.getClass());
        }
        ElementHelper.setNewElement(this, value, soapFaultSubCodeValue);
    }

    public SOAPFaultValue getValue() {
        if (value == null) {
            value = (SOAPFaultValue)getFirstChildWithName(SOAP12Constants.QNAME_FAULT_VALUE);
        }
        return value;
    }

    public void setValue(QName value) {
        SOAPFaultValue valueElement = getValue();
        if (valueElement == null) {
            valueElement = ((SOAPFactory)getOMFactory()).createSOAPFaultValue(this);
        }
        valueElement.setText(value);
    }

    public QName getValueAsQName() {
        SOAPFaultValue value = getValue();
        return value == null ? null : value.getTextAsQName();
    }

    protected OMElement createClone(OMCloneOptions options, OMContainer targetParent) {
        if (targetParent instanceof SOAPFaultSubCode) {
            return ((SOAPFactory)getOMFactory()).createSOAPFaultSubCode((SOAPFaultSubCode)targetParent);
        } else {
            return ((SOAPFactory)getOMFactory()).createSOAPFaultSubCode((SOAPFaultCode)targetParent);
        }
    }
}
