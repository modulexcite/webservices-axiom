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
package org.apache.axiom.soap;

import org.apache.axiom.om.OMMetaFactory;

public class SOAP11EnvelopeTestBase extends SOAPEnvelopeTestBase {
    public SOAP11EnvelopeTestBase(OMMetaFactory omMetaFactory) {
        super(omMetaFactory, SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
    }

    // TODO: this should actually go to SOAPEnvelopeTestBase because it applies equally well to SOAP 1.1 and 1.2;
    //       however, this causes a test failure in axiom-parser-tests with SJSXP
    // Regression test for WSCOMMONS-235 (see r567512)
    public void testDiscardHeader() throws Exception {
        SOAPEnvelope envelope = getTestMessage(MESSAGE);
        envelope.getHeader().discard();
        envelope.getBody().toStringWithConsume();
    }
}