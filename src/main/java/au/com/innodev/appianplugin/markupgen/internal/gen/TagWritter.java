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

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import au.com.innodev.appianplugin.markupgen.internal.gen.escaping.AttributeValueEscaper;

/**
 * Writes the contents of a tag as a string. Used for both XML and HTML generation.
 *
 * For HTML compatibility, it uses self-closing tags in the following form:
 * {@code <br />}
 * 
 * @author juanal
 */
public class TagWritter {

	public static void generate(StringBuilder sb, String tagName, Map<String, String> attributes, String contents, TagCloseStyle tagCloseStyle, AttributeValueEscaper attributeValueEscaper) {
		sb.append("<");
		sb.append(tagName);
		for (Entry<String, String> attrEntry : attributes.entrySet()) {
			sb.append(" ");
			sb.append(attrEntry.getKey());
			sb.append("=\"");
			sb.append(attributeValueEscaper.escapeAttributeValue(attrEntry.getValue()));
			sb.append("\"");
		}

		if (tagCloseStyle.equals(TagCloseStyle.SELF_CLOSING_TAG) && StringUtils.isEmpty(contents)) {
			sb.append(" />");
		}
		else {
			sb.append(">");	
			if(contents != null) {
				sb.append(contents);
			}
			
			sb.append("</");
			sb.append(tagName);
			sb.append(">");	
		}
		
	}
}
