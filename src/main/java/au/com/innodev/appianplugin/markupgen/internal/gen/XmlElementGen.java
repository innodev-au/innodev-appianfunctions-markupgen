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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import au.com.innodev.appianplugin.markupgen.internal.gen.escaping.AttributeValueEscapers;
import au.com.innodev.appianplugin.markupgen.internal.gen.escaping.XMLChar;

/**
 * An XML element
 * 
 * @author juanal
 *
 */
public class XmlElementGen implements XmlPartGen {

	
	private static void validateContentList(List<XmlPartGen> contentList) {
		for (XmlPartGen htmlElementData : contentList) {
			if (htmlElementData == null) {
				throw new RuntimeException("Found null item in content element list");
			}
		}
	}

	private static void validateTagName(String tagName) {
		if (!XMLChar.isValidName(tagName)) {
			throw new RuntimeException("Invalid XML tag name was provided [" + tagName + "]");
		}
	}

	public static Map<String, String> emptyAttributes() {
		return Collections.<String, String>emptyMap();
	}

	public static List<XmlPartGen> emptyContent() {
		return Collections.emptyList();
	}

	private static void validateAttributes(Map<String, String> attributes, String tagName) {
		for (String attrName : attributes.keySet()) {
			validateAttributeName(attrName, tagName);
		}
	}

	private static void validateAttributeName(String attrName, String tagName) {
		if (!XMLChar.isValidName(attrName)) {
			throw new RuntimeException(
					"Invalid XML attribute name was provided [" + attrName + "] for tag [" + tagName + "]");
		}
	}

	/*** Fields ***/
	private String name;

	private Map<String, String> attributes;

	private List<XmlPartGen> contentList;

	public XmlElementGen(String name, Map<String, String> attributes, List<XmlPartGen> contentList) {
		super();

		this.name = Objects.requireNonNull(name);
		this.attributes = (attributes != null) ? attributes : emptyAttributes();
		this.contentList = (contentList != null) ? contentList : emptyContent();

		validateTagName(this.name);
		validateAttributes(this.attributes, this.name);
		validateContentList(this.contentList);
	}

	@Override
	public String getOutput() {
		StringBuilder sb = new StringBuilder();
		this.generate(sb);

		return sb.toString();

	}

	public void generate(StringBuilder sb) {
		StringBuilder contentsBuilder = new StringBuilder();

		for (XmlPartGen element : contentList) {
			String s = element.getOutput();
			if (s != null) {
				contentsBuilder.append(s);
			}
		}

		/* XML elements always use self-closing tags */
		TagWritter.generate(sb, this.name, this.attributes, contentsBuilder.toString(), TagCloseStyle.SELF_CLOSING_TAG,
				AttributeValueEscapers.xmlAttributeValueEscaper());
	}

}
