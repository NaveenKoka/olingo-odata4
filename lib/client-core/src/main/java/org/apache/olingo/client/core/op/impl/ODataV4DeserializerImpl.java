/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.client.core.op.impl;

import java.io.InputStream;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.data.ServiceDocument;
import org.apache.olingo.client.api.edm.xml.v4.XMLMetadata;
import org.apache.olingo.client.api.format.ODataFormat;
import org.apache.olingo.client.api.op.ODataV4Deserializer;
import org.apache.olingo.client.core.data.v4.JSONServiceDocumentImpl;
import org.apache.olingo.client.core.data.v4.XMLServiceDocumentImpl;
import org.apache.olingo.client.core.edm.xml.v4.EdmxImpl;
import org.apache.olingo.client.core.edm.xml.v4.XMLMetadataImpl;

public class ODataV4DeserializerImpl extends AbstractODataDeserializer implements ODataV4Deserializer {

  private static final long serialVersionUID = 8593081342440470415L;

  public ODataV4DeserializerImpl(final ODataClient client) {
    super(client);
  }

  @Override
  public XMLMetadata toMetadata(final InputStream input) {
    try {
      return new XMLMetadataImpl(getXmlMapper().readValue(input, EdmxImpl.class));
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not parse as Edmx document", e);
    }
  }

  @Override
  public ServiceDocument toServiceDocument(final InputStream input, final ODataFormat format) {
    return format == ODataFormat.XML
            ? xml(input, XMLServiceDocumentImpl.class)
            : json(input, JSONServiceDocumentImpl.class);

  }
}
