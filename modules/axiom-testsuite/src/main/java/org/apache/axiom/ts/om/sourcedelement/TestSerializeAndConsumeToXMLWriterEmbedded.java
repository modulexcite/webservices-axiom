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
package org.apache.axiom.ts.om.sourcedelement;

import java.io.StringWriter;

import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMMetaFactory;
import org.apache.axiom.om.util.StAXUtils;

/**
 * Tests OMSourcedElement serialization when the root (parent) is serialized.
 */
public class TestSerializeAndConsumeToXMLWriterEmbedded extends OMSourcedElementTest {
    public TestSerializeAndConsumeToXMLWriterEmbedded(OMMetaFactory metaFactory) {
        super(metaFactory);
    }

    protected void runTest() throws Throwable {
        StringWriter writer = new StringWriter();
        XMLStreamWriter xmlwriter = StAXUtils.createXMLStreamWriter(writer);
        root.serializeAndConsume(writer);
        xmlwriter.flush();
        String result = writer.toString();

        // We can't test for equivalence because the underlying OMSourceElement is 
        // streamed as it is serialized.  So I am testing for an internal value.
        assertTrue("Serialized text error" + result, result.indexOf("1930110111") > 0);
        assertFalse("Element expansion when serializing", element.isExpanded());
    }
}