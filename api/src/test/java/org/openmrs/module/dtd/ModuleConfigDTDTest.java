/*
	* This Source Code Form is subject to the terms of the Mozilla Public License,
	* v. 2.0. If a copy of the MPL was not distributed with this file, You can
	* obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
	* the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
	*
	* Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
	* graphic logo is a trademark of OpenMRS Inc.
*/
package org.openmrs.module.dtd;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

import org.junit.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ModuleConfigDTDTest {
	
	private Boolean isError;
	
	private void isXMLError(String fileName) {
		try {
			DocumentBuilderFactory domFactory;
			DocumentBuilder builder;
			isError = false;
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(true);
			builder = domFactory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void error(SAXParseException e) throws SAXException {
					isError = true;
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					isError = true;
					throw e;
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					isError = false;
					throw e;
				}
			});
			builder.parse("src/test/java/org/openmrs/module/dtd/testxml/" + fileName);
		} catch (SAXException e) {
			e.printStackTrace();
			isError = true;
		} catch (IOException e) {
			e.printStackTrace();
			isError = null;
		} catch (ParserConfigurationException e) {
			isError = null;
		}
	}

	/*
	CONFIG FILE 1.5 TESTS
	 */
	
	// The DTD throws no errors if the XML is valid
	@Test
	public void validXMLTest_1_5() {
		isXMLError("valid_1_5.xml");
		assertFalse(isError);
	}

	// The 1.5 DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest_1_5() {
		isXMLError("removeRequired_1_5.xml");
		assertTrue(isError);
	}
	
	// The 1.5 DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest_1_5() {
		isXMLError("reordered_1_5.xml");
		assertTrue(isError);
	}

	// The 1.5 DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest_1_5() {
		isXMLError("syntaxError_1_5.xml");
		assertTrue(isError);
	}
	
	/*
	CONFIG FILE 1.6 TESTS
	 */

	// The 1.6 DTD throws no errors if the XML is valid
	@Test
	public void validXMLTest_1_6() {
		isXMLError("valid_1_6.xml");
		assertFalse(isError);
	}

	// The 1.6 DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest_1_6() {
		isXMLError("removeRequired_1_6.xml");
		assertTrue(isError);
	}

	// The 1.6 DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest_1_6() {
		isXMLError("reordered_1_6.xml");
		assertTrue(isError);
	}

	// The 1.6 DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest_1_6() {
		isXMLError("syntaxError_1_6.xml");
		assertTrue(isError);
	}
}
