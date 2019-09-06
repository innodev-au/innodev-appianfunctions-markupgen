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

/**
 * An HTML element
 * 
 * @author juanal
 *
 */
public class HtmlElementGen implements HtmlPartGen {

	/* The main purpose of the regex is to not allow special characters such as '<', '&', etc.*/
	private static final String NAME_REGEX = "[\\p{Alnum}]+[\\p{Alnum}_-]*";
	
	/* Although currently no HTML tag has a hyphen or underscore, we don't need to prevent values from being entered.*/
	private static final String TAG_NAME_WHITELIST_REGEX = NAME_REGEX;
	private static final String ATTR_NAME_WHITELIST_REGEX = NAME_REGEX;

	private static void validateContentList(List<HtmlPartGen> contentList, TagCloseStyle tagCloseStyle) {

		if (TagCloseStyle.SELF_CLOSING_TAG.equals(tagCloseStyle) && !contentList.isEmpty()) {
			throw new RuntimeException("Unable to create an element with self-closing tag and contents");
		}

		for (HtmlPartGen htmlElementData : contentList) {
			if (htmlElementData == null) {
				throw new RuntimeException("Found null item in content element list");
			}
		}
	}

	private static void validateTagName(String tagName) {
		if (!tagName.matches(TAG_NAME_WHITELIST_REGEX)) {
			throw new RuntimeException("Invalid HTML tag name was provided [" + tagName + "]");
		}
	}

	public static Map<String, String> emptyAttributes() {
		return Collections.<String, String>emptyMap();
	}

	public static List<HtmlPartGen> emptyContent() {
		return Collections.emptyList();
	}

	private static void validateAttributes(Map<String, String> attributes, String tagName) {
		for (String attrName : attributes.keySet()) {
			validateAttributeName(attrName, tagName);
		}
	}

	private static void validateAttributeName(String attrName, String tagName) {
		if (!attrName.matches(ATTR_NAME_WHITELIST_REGEX)) {
			throw new RuntimeException(
					"Invalid HTML attribute name was provided [" + attrName + "] for tag [" + tagName + "]");
		}
	}

	/*** Fields ***/
	private String name;

	private Map<String, String> attributes;

	private List<HtmlPartGen> contentList;
	private TagCloseStyle tagCloseStyle;

	public HtmlElementGen(String name, Map<String, String> attributes, List<HtmlPartGen> contentList,
			TagCloseStyle tagCloseStyle) {
		super();

		this.name = Objects.requireNonNull(name);
		this.attributes = (attributes != null) ? attributes : emptyAttributes();
		this.contentList = (contentList != null) ? contentList : emptyContent();
		this.tagCloseStyle = tagCloseStyle;

		validateTagName(this.name);
		validateAttributes(this.attributes, this.name);
		validateContentList(this.contentList, this.tagCloseStyle);
	}

	@Override
	public String getOutput() {
		StringBuilder sb = new StringBuilder();
		this.generate(sb);

		return sb.toString();

	}

	public void generate(StringBuilder sb) {
		StringBuilder contentsBuilder = new StringBuilder();

		for (HtmlPartGen element : contentList) {
			String s = element.getOutput();
			if (s != null) {
				contentsBuilder.append(s);
			}
		}

		TagWritter.generate(sb, this.name, this.attributes, contentsBuilder.toString(), this.tagCloseStyle,
				AttributeValueEscapers.htmlAttributeValueEscaper());
	}
}
