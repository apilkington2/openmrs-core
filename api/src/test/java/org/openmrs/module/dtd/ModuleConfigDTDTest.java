package org.openmrs.module.dtd;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

import org.junit.Test;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class ModuleConfigDTDTest {

	DocumentBuilderFactory domFactory;
	DocumentBuilder builder;
	Boolean isError;
	
	public void isXMLError(String fileName) {
		try {
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
			Document doc = builder.parse("src/test/java/org/openmrs/module/dtd/testxml/" + fileName);
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
		assertTrue(!isError);
	}

	// The DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest_1_5() {
		isXMLError("removeRequired_1_5.xml");
		assertTrue(isError);
	}
	
	// The DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest_1_5() {
		isXMLError("reordered_1_5.xml");
		assertTrue(isError);
	}

	// The DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest_1_5() {
		isXMLError("syntaxError_1_5.xml");
		assertTrue(isError);
	}
	
	/*
	CONFIG FILE 1.6 TESTS
	 */

	// The DTD throws no errors if the XML is valid
	@Test
	public void validXMLTest_1_6() {
		isXMLError("valid_1_6.xml");
		assertTrue(!isError);
	}

	// The DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest_1_6() {
		isXMLError("removeRequired_1_6.xml");
		assertTrue(isError);
	}

	// The DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest_1_6() {
		isXMLError("reordered_1_6.xml");
		assertTrue(isError);
	}

	// The DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest_1_6() {
		isXMLError("syntaxError_1_6.xml");
		assertTrue(isError);
	}
}
