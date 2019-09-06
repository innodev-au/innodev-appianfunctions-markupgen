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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.appiancorp.suiteapi.common.exceptions.AppianException;

public class FunctionUtil {
	private static final Logger LOGGER = Logger.getLogger(FunctionUtil.class.getCanonicalName());
	
	public static AppianException throwFunctionException(Exception e) throws AppianException {
		LOGGER.log(Level.WARNING, "En error was encountered when running function", e);
		return new AppianException(e);
	}
}
