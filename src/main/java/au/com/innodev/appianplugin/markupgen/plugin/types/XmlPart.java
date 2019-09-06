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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import au.com.innodev.appianplugin.markupgen.internal.gen.MarkupUtil;

/**
 * Used for serialisation/deserialisation of XML contents within Appian.
 * 
 * This class is exposed as an Appian data type when the plugin is deployed.
 *  
 * @author juanal
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = TypeConstants.TYPE_XML_LOCAL_NAME, namespace = TypeConstants.TYPE_NAMESPACE)
public class XmlPart {
	private static final String PREFIX = "/XML/=";

	@XmlElement(name=TypeConstants.DATA_TYPE_INTERNAL_NAME_XML_VAL)
	private String internalXmlVal;

	public static XmlPart createWithXml(String xml) {
		String normalisedXml = MarkupUtil.normaliseNullToEmptyString(xml);
		
		String internalXmlVal = PREFIX + normalisedXml;
		return new XmlPart(internalXmlVal);
	}
	
	public static XmlPart createWithInternalHmlVal(String internalXmlVal) {
		
		return new XmlPart(internalXmlVal);
	}
	
	public XmlPart() {
	}

	private XmlPart(String internalXmlVal) {
		this.internalXmlVal = MarkupUtil.normaliseNullToEmptyString(internalXmlVal);
	}

	public String getNonNullInternalVal() {
		return MarkupUtil.normaliseNullToEmptyString(internalXmlVal);
	}

	public String getXmlValue() {
		String val = getNonNullInternalVal();
		
		if("".equals(val)) {
			return "";
		}
		else if (val.startsWith(PREFIX)) {
			return StringUtils.removeStart(val, PREFIX);
		} else {
			throw new RuntimeException("Cannot get XML content. Internal value is invalid. "
					+ "Make sure the internal value wasn't set manually and only through functions provided in the plugin.");
		}
	}

	@Override
	public String toString() {
		return PREFIX + getNonNullInternalVal();
	}
}
