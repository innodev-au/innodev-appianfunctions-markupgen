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
package au.com.innodev.appianplugin.markupgen.internal.gen.escaping;

/**
 * Provides instances of value escapers.
 * 
 * @author juanal
 */
public class AttributeValueEscapers {

	private static final AttributeValueEscaper HTML_ATTR_VALUE_ESCAPER = new AttributeValueEscaper() {		
		@Override
		public String escapeAttributeValue(String unescapedValue) {		
			return EscapeUtils.escapeHtml(unescapedValue);
		}
	};
	
	private static final AttributeValueEscaper XML_ATTR_VALUE_ESCAPER = new AttributeValueEscaper() {		
		@Override
		public String escapeAttributeValue(String unescapedValue) {		
			return EscapeUtils.escapeXml(unescapedValue);
		}
	};
	
	public static final AttributeValueEscaper xmlAttributeValueEscaper() {
		return XML_ATTR_VALUE_ESCAPER;
	}
	
	public static final AttributeValueEscaper htmlAttributeValueEscaper() {
		return HTML_ATTR_VALUE_ESCAPER;
	}
}
