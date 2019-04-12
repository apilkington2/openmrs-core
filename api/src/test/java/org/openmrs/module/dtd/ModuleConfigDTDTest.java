package org.openmrs.module.dtd;

import org.apache.xpath.operations.Bool;
import org.junit.Before;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

import org.junit.Test;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.EmptyStackException;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

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

	// The DTD throws no errors if the XML is valid
	@Test
	public void validXMLTest() {
		isXMLError("valid.xml");
		assertTrue(!isError);
	}

	// The DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest() {
		isXMLError("removeRequired.xml");
		assertTrue(isError);
	}
	
	// The DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest() {
		isXMLError("reordered.xml");
		assertTrue(isError);
	}

	// The DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest() {
		isXMLError("syntaxError.xml");
		assertTrue(isError);
	}
}
