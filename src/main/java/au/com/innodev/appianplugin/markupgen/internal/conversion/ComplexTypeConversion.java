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

import javax.xml.namespace.QName;

import com.appiancorp.suiteapi.type.Datatype;
import com.appiancorp.suiteapi.type.TypeService;

/**
 * Utility methods for conversion of Appian complex types.
 * 
 * @author juanal
 *
 */
public class ComplexTypeConversion {	
		
	public static long getTypeIdByQName(QName qName, TypeService typeService) {
		Datatype type = typeService.getTypeByQualifiedName(qName);
		
		if (type == null) {
			throw new RuntimeException("Unable to find data type [" + qName.toString() + "] in Appian environment.");
		}
		
		return type.getId();
	}
		
}
