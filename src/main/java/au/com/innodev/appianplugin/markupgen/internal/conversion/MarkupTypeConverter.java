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

import au.com.innodev.appianplugin.markupgen.plugin.types.HtmlPart;
import au.com.innodev.appianplugin.markupgen.plugin.types.XmlPart;

/**
 * Used to convert {@link XmlPart} or {@link HtmlPart} from Appian
 * to objects used in this library.
 * 
 * @author juanal
 *
 */
public interface MarkupTypeConverter<T> {

	T newRawElement(String internalVal);

	T newStringElement(String textVal);

	String getInternalValFieldName();

	long getElementDataTypeIdInAppian(TypeService typeService);
}
