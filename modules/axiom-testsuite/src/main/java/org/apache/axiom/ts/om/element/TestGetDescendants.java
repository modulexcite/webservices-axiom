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
package org.apache.axiom.ts.om.element;

import java.util.Iterator;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMMetaFactory;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.ts.AxiomTestCase;

public class TestGetDescendants extends AxiomTestCase {
    private final boolean includeSelf;
    
    public TestGetDescendants(OMMetaFactory metaFactory, boolean includeSelf) {
        super(metaFactory);
        this.includeSelf = includeSelf;
        addTestProperty("includeSelf", Boolean.toString(includeSelf));
    }

    protected void runTest() throws Throwable {
        OMElement element = AXIOMUtil.stringToOM(metaFactory.getOMFactory(),
                "<root><a><b><c><d/><e/></c></b><f/></a><g/></root>");
        // We intentionally get the descendants of <a> so that we can test containment
        // (the iterator must never return <g>, which is a sibling of <a>).
        Iterator it = element.getFirstElement().getDescendants(includeSelf);
        if (includeSelf) {
            assertEquals("a", ((OMElement)it.next()).getLocalName());
        }
        assertEquals("b", ((OMElement)it.next()).getLocalName());
        assertEquals("c", ((OMElement)it.next()).getLocalName());
        assertEquals("d", ((OMElement)it.next()).getLocalName());
        assertEquals("e", ((OMElement)it.next()).getLocalName());
        assertEquals("f", ((OMElement)it.next()).getLocalName());
        assertFalse(it.hasNext());
    }
}
