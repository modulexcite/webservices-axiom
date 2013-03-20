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
package org.apache.axiom.ts.om.sourcedelement.push;

import java.util.Collections;
import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMSourcedElement;
import org.apache.axiom.testutils.suite.MatrixTestCase;
import org.junit.Assert;

/**
 * Tests that {@link XMLStreamWriter#getNamespaceContext()} gives access to pre-existing namespace
 * bindings (defined by the ancestors of the {@link OMSourcedElement}).
 */
public class GetNamespaceContextScenario implements PushOMDataSourceScenario {
    public void addTestParameters(MatrixTestCase testCase) {
        testCase.addTestParameter("scenario", "getNamespaceContext");
    }

    public Map getNamespaceContext() {
        return Collections.singletonMap("p", "urn:test");
    }

    public void serialize(XMLStreamWriter writer, Map testContext) throws XMLStreamException {
        testContext.put("uri", writer.getNamespaceContext().getNamespaceURI("p"));
        writer.writeStartElement(null, "root", null);
        writer.writeEndElement();
    }

    public void validate(OMElement element, Map testContext) {
        // Expand element
        element.getFirstOMChild();
        Assert.assertEquals("urn:test", testContext.get("uri"));
    }
}