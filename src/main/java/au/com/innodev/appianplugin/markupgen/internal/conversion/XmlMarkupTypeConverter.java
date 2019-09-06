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
package au.com.innodev.appianplugin.markupgen.internal.conversion;

import com.appiancorp.suiteapi.type.TypeService;

import au.com.innodev.appianplugin.markupgen.internal.gen.RawXmlPartGen;
import au.com.innodev.appianplugin.markupgen.internal.gen.StringXmlPartGen;
import au.com.innodev.appianplugin.markupgen.internal.gen.XmlPartGen;
import au.com.innodev.appianplugin.markupgen.plugin.types.TypeConstants;
import au.com.innodev.appianplugin.markupgen.plugin.types.XmlPart;

/**
 * {@link MarkupTypeConverter} subtype for XML.
 * 
 * @author juanal
 *
 */
public class XmlMarkupTypeConverter implements MarkupTypeConverter<XmlPartGen> {

	@Override
	public XmlPartGen newRawElement(String internalVal) {
		XmlPart xml = XmlPart.createWithInternalHmlVal(internalVal);
		return new RawXmlPartGen(xml.getXmlValue());
	}

	@Override
	public XmlPartGen newStringElement(String textVal) {		
		return new StringXmlPartGen(textVal);
	}

	@Override
	public String getInternalValFieldName() {		
		return TypeConstants.DATA_TYPE_INTERNAL_NAME_XML_VAL;
	}

	@Override
	public long getElementDataTypeIdInAppian(TypeService typeService) {
		return ComplexTypeConversion.getTypeIdByQName(TypeConstants.QNAME_XML, typeService);
	}
}
