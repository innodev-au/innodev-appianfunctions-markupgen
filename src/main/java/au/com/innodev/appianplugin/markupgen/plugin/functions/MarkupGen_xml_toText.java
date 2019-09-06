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

import java.util.List;

import com.appiancorp.suiteapi.common.exceptions.AppianException;
import com.appiancorp.suiteapi.expression.annotations.Category;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;
import com.appiancorp.suiteapi.type.TypeService;
import com.appiancorp.suiteapi.type.TypedValue;

import au.com.innodev.appianplugin.markupgen.internal.conversion.AppianParamConverter;
import au.com.innodev.appianplugin.markupgen.internal.gen.XmlMarkupFacade;
import au.com.innodev.appianplugin.markupgen.internal.gen.XmlPartGen;
import innodev.appianplugin.util.type.AppianTypeConverter.MandatorySpec;

/**
 * Exposes an Appian function that converts XML elements to text.
 *  
 * @author juanal
 *
 */
@Category(MarkupGenFuncsCategory.FUNCTIONS_CATEGORY)
public class MarkupGen_xml_toText {

	@Function
	public String markupGen_xml_toText(TypeService typeService, @Parameter TypedValue xmlParts) throws AppianException {
		try {
			AppianParamConverter paramConverter = new AppianParamConverter(typeService);
			
			List<XmlPartGen> elements = paramConverter.xmlContentsToList(xmlParts, MandatorySpec.MANDATORY);
			
			return XmlMarkupFacade.toXmlText(elements);
		} catch (Exception e) {
			throw FunctionUtil.throwFunctionException(e);
		}
	}

}
