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

import au.com.innodev.appianplugin.markupgen.plugin.types.HtmlPart;

/**
 * Facade for function implementation free from Appian classes.
 *   
 * @author juanal
 *
 */
public class HtmlMarkupFacade {

	public static HtmlPart newHtmlElem(String tagName, Map<String, String> attributeMap, List<HtmlPartGen> contentElements, Boolean isSelfClosingTagObj) {
				
		/* selfClosingTag defaults to false */
		boolean selfClosingTag = (isSelfClosingTagObj == null) ? false : isSelfClosingTagObj.booleanValue();
		
		TagCloseStyle tagCloseStyle = (selfClosingTag) ? TagCloseStyle.SELF_CLOSING_TAG : TagCloseStyle.ALWAYS_SHOW_CLOSING_TAG;
		HtmlElementGen htmlElement = new HtmlElementGen(tagName, attributeMap, contentElements, tagCloseStyle);

		return generateAndExternalisePart(htmlElement);
	}

	private static HtmlPart generateAndExternalisePart(HtmlPartGen partGen) {
		return HtmlPart.createWithHtml(partGen.getOutput());
	}	

	public static HtmlPart newRawHtml(String htmlPart) {
		RawHtmlPartGen partGen = new RawHtmlPartGen(htmlPart);
		// TODO validate HTML
		return generateAndExternalisePart(partGen);
	}

	public static HtmlPart newTextHtmlPart(String text) {
		StringHtmlPartGen partGen = new StringHtmlPartGen(text);
		return generateAndExternalisePart(partGen);
	}
	
	public static String toHtmlText(List<HtmlPartGen> htmlPartGenerators) {
		StringBuilder sb = new StringBuilder();
		
		Objects.requireNonNull(htmlPartGenerators, "No HTML parts/elements provided");

		for (HtmlPartGen element : htmlPartGenerators) {
			if (element != null) {
				sb.append(element.getOutput());
			}
		}
		
		return sb.toString();
		
	}

}
