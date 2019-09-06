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
package au.com.innodev.appianplugin.markupgen.plugin.functions;

import com.appiancorp.suiteapi.common.exceptions.AppianException;
import com.appiancorp.suiteapi.expression.annotations.Category;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;

import au.com.innodev.appianplugin.markupgen.internal.gen.HtmlMarkupFacade;
import au.com.innodev.appianplugin.markupgen.plugin.types.HtmlPart;

/**
 * Exposes an Appian function that creates an HTML element with raw HTML content.
 * 
 * @author juanal
 *
 */
@Category(MarkupGenFuncsCategory.FUNCTIONS_CATEGORY)
public class MarkupGen_html_newTextPart {

	@Function
	public HtmlPart markupGen_html_newTextPart(@Parameter String text) 
			throws Exception {
		try {
			
			return HtmlMarkupFacade.newTextHtmlPart(text);
		} catch (Exception e) {
			throw FunctionUtil.throwFunctionException(e);
		}
	}
}
