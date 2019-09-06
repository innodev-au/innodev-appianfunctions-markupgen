/**
 * Copyright 2019 Innodev
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package au.com.innodev.appianplugin.markupgen.plugin.types;

import javax.xml.namespace.QName;

/**
 * Values used in exposed Appian complex types.
 * 
 * @author juanal
 *
 */
public class TypeConstants {

	public static final String TYPE_NAMESPACE = "urn:au.com.innodev.appianplugin.markupgen.plugin.types";
	
	public static final String TYPE_HTML_LOCAL_NAME = "MarkupGen_HtmlPart";
	public static final String TYPE_XML_LOCAL_NAME = "MarkupGen_XmlPart";
	
	public static final QName QNAME_HTML = new QName(TYPE_NAMESPACE, TYPE_HTML_LOCAL_NAME);
	public static final QName QNAME_XML = new QName(TYPE_NAMESPACE, TYPE_XML_LOCAL_NAME);
	
	public static final String DATA_TYPE_INTERNAL_NAME_HTML_VAL = "internalHtmlVal";
	public static final String DATA_TYPE_INTERNAL_NAME_XML_VAL = "internalXmlVal";
}
