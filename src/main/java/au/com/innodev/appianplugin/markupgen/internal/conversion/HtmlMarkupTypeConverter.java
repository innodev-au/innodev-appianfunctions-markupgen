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

import au.com.innodev.appianplugin.markupgen.internal.gen.HtmlPartGen;
import au.com.innodev.appianplugin.markupgen.internal.gen.RawHtmlPartGen;
import au.com.innodev.appianplugin.markupgen.internal.gen.StringHtmlPartGen;
import au.com.innodev.appianplugin.markupgen.plugin.types.HtmlPart;
import au.com.innodev.appianplugin.markupgen.plugin.types.TypeConstants;

/**
 * {@link MarkupTypeConverter} subtype for HTML.
 * 
 * @author juanal
 *
 */
public class HtmlMarkupTypeConverter implements MarkupTypeConverter<HtmlPartGen> {

	@Override
	public HtmlPartGen newRawElement(String internalVal) {
		HtmlPart html = HtmlPart.createWithInternalHmlVal(internalVal);
		return new RawHtmlPartGen(html.getHtmlValue());
	}

	@Override
	public HtmlPartGen newStringElement(String textVal) {		
		return new StringHtmlPartGen(textVal);
	}

	@Override
	public String getInternalValFieldName() {		
		return TypeConstants.DATA_TYPE_INTERNAL_NAME_HTML_VAL;
	}
	
	@Override
	public long getElementDataTypeIdInAppian(TypeService typeService) {
		return ComplexTypeConversion.getTypeIdByQName(TypeConstants.QNAME_HTML, typeService);
	}
	
}
