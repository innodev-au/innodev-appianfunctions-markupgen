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
package au.com.innodev.appianplugin.markupgen.internal.gen;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import au.com.innodev.appianplugin.markupgen.plugin.types.XmlPart;

/**
 * Facade for function implementation free from Appian classes.
 *   
 * @author juanal
 *
 */
public class XmlMarkupFacade {

	public static XmlPart newXmlElem(String tagName, Map<String, String> attributeMap, List<XmlPartGen> contentElements) {
				
		XmlElementGen partGen = new XmlElementGen(tagName, attributeMap, contentElements);

		return generateAndExternalisePart(partGen);
	}

	private static XmlPart generateAndExternalisePart(XmlPartGen partGen) {
		return XmlPart.createWithXml(partGen.getOutput());
	}	

	public static XmlPart newRawXml(String xmlPart) {
		RawXmlPartGen partGen = new RawXmlPartGen(xmlPart);
		// FIXME validate XML
		return generateAndExternalisePart(partGen);
	}

	public static XmlPart newTextXmlPart(String text) {
		StringXmlPartGen partGen = new StringXmlPartGen(text);
		return generateAndExternalisePart(partGen);
	}
	
	public static String toXmlText(List<XmlPartGen> xmlPartGenerators) {
		StringBuilder sb = new StringBuilder();
		
		Objects.requireNonNull(xmlPartGenerators, "No XML parts/elements provided");
		
		for (XmlPartGen element : xmlPartGenerators) {
			if (element != null) {
				sb.append(element.getOutput());
			}
		}
		
		return sb.toString();
		
	}

}
