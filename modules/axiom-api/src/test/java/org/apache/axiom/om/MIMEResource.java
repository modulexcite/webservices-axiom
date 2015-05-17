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
package org.apache.axiom.om;

import java.io.InputStream;
import java.text.ParseException;

import org.apache.axiom.mime.ContentType;

public class MIMEResource {
    private final String name;
    private final String contentType;
    
    public MIMEResource(String name, String contentType) {
        this.name = name;
        this.contentType = contentType;
    }

    /**
     * Get the content of this message.
     * 
     * @return an input stream with the content of this message
     */
    public InputStream getInputStream() {
        return MIMEResource.class.getClassLoader().getResourceAsStream(name);
    }

    public String getContentType() {
        return contentType;
    }
    
    private String getParameter(String name) {
        try {
            return new ContentType(contentType).getParameter(name);
        } catch (ParseException ex) {
            // MIMEResource objects are only defined as constants. Therefore we
            // will never get here under normal conditions.
            throw new Error(ex);
        }
    }
    
    public String getStart() {
        String start = getParameter("start");
        if (start.startsWith("<") && start.endsWith(">")) {
            return start.substring(1, start.length()-1);
        } else {
            return start;
        }
    }
    
    public String getBoundary() {
        return getParameter("boundary");
    }
}
