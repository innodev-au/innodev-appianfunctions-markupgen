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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.appiancorp.ps.plugins.typetransformer.AppianElement;
import com.appiancorp.ps.plugins.typetransformer.AppianObject;
import com.appiancorp.ps.plugins.typetransformer.AppianTypeFactory;
import com.appiancorp.suiteapi.type.AppianType;
import com.appiancorp.suiteapi.type.TypeService;
import com.appiancorp.suiteapi.type.TypedValue;

import au.com.innodev.appianplugin.markupgen.internal.gen.HtmlPartGen;
import au.com.innodev.appianplugin.markupgen.internal.gen.XmlPartGen;
import innodev.appianplugin.util.type.AppianTypeConverter;
import innodev.appianplugin.util.type.AppianTypeConverter.MandatorySpec;
import innodev.appianplugin.util.type.TypeUtils;

/**
 * Converts Appian function parameters to internal values.
 * 
 * @author juanal
 *
 */
public class AppianParamConverter {

	private final AppianTypeConverter typeConverter;
	private final TypeService typeService;

	public AppianParamConverter(TypeService typeService) {
		super();
		this.typeService = typeService;
		AppianTypeFactory typeFactory = AppianTypeFactory.newInstance(typeService);
		this.typeConverter = new AppianTypeConverter(typeFactory, typeService);
	}

	public <T> List<T> markupContentsToList(MarkupTypeConverter<T> markupTypeConverter, TypedValue contents,
			MandatorySpec mandSpec) {
		List<AppianElement> list = typeConverter.toListFromListOrSingleItem(contents, mandSpec, "'contents' value");
		List<T> resultList;

		Collection<Long> primitiveTypeIds = Arrays
				.asList(new Long[] { (long) AppianType.STRING, (long) AppianType.INTEGER, (long) AppianType.DOUBLE });

		if (list != null) {
			resultList = new ArrayList<>(list.size());
			for (int i = 0; i < list.size(); i++) {
				AppianElement element = list.get(i);

				T markupElement;

				if (element != null) {
					long typeId = element.getTypeId();
					String context = "Value for 'contents', element of 0-based index " + i;

					String markupTypeFiledName = markupTypeConverter.getInternalValFieldName();
					
					if (element instanceof AppianObject && ((AppianObject)element).containsKey(markupTypeFiledName)) {
						AppianObject complexElem = (AppianObject) element;
						
						String internalHtmlVal = complexElem.getValue(markupTypeConverter.getInternalValFieldName(),
								String.class);
						if (internalHtmlVal != null) {
							markupElement = markupTypeConverter.newRawElement(internalHtmlVal);
						}
						else {
							markupElement = null;
						}
					} else if (primitiveTypeIds.contains(typeId)) {
						String textVal = typeConverter.toStringFromTextOrNumber(element, MandatorySpec.MANDATORY,
								context);
						if (textVal != null) {
							markupElement = markupTypeConverter.newStringElement(textVal);
						} else {
							markupElement = null;
						}
					} else {
						String typeName = TypeUtils.getSafeTypeDisplayName(typeId, typeService);

						throw new RuntimeException(context + " had an unsupported type [" + typeName + "]");
					}
				} else {
					markupElement = null;
				}

				// skip nulls
				if (markupElement != null) {
					resultList.add(markupElement);
				}
			}
		} else {
			resultList = Collections.emptyList();
		}

		return resultList;
	}

	public List<HtmlPartGen> htmlContentsToList(TypedValue contents, MandatorySpec mandSpec) {
		HtmlMarkupTypeConverter markupTypeConverter = new HtmlMarkupTypeConverter();

		return markupContentsToList(markupTypeConverter, contents, mandSpec);
	}

	public List<XmlPartGen> xmlContentsToList(TypedValue contents, MandatorySpec mandSpec) {
		XmlMarkupTypeConverter markupTypeConverter = new XmlMarkupTypeConverter();

		return markupContentsToList(markupTypeConverter, contents, mandSpec);
	}

	public Map<String, String> attributesToDictionary(TypedValue attributes) {
		Map<String, AppianElement> attrs = typeConverter.toDictionary(attributes, MandatorySpec.OPTIONAL,
				"'attributes' value");
		Map<String, String> stringAttrsMap = new LinkedHashMap<>();

		if (attrs != null) {
			for (Entry<String, AppianElement> entry : attrs.entrySet()) {
				String entryKey = entry.getKey();
				AppianElement entryValue = entry.getValue();
				String entryValueAsText = typeConverter.toStringFromTextOrNumber(entryValue, MandatorySpec.MANDATORY,
						"Attribute '" + entryKey + "'");
				stringAttrsMap.put(entryKey, entryValueAsText);
			}
		}

		return stringAttrsMap;
	}
}
