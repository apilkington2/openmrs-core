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

	// The DTD throws no errors if the XML is valid
	@Test
	public void validXMLTest() {
		try {
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(true);
			builder = domFactory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void error(SAXParseException e) throws SAXException {
					e.printStackTrace();
					fail();
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					e.printStackTrace();
					fail();
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					e.printStackTrace();
					fail();
				}
			});
			Document doc = builder.parse("src/test/java/org/openmrs/module/dtd/testxml/valid.xml");
		} catch (SAXException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParserConfigurationException e) {
			fail();
		}
	}

	// The DTD throws an error if something that is required in the XML is removed (e.g. id) 
	@Test
	public void removeRequiredXMLTest() {
		try {
			boolean hitError = false; 
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(true);
			builder = domFactory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void error(SAXParseException e) throws SAXException {
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					fail();
					throw e;
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					fail();
					throw e;
				}
			});
			Document doc = builder.parse("src/test/java/org/openmrs/module/dtd/testxml/removeRequired.xml");
			fail();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParserConfigurationException e) {
			fail();
		}
	}
	
	// The DTD throws an error if the XML file is out of order
	@Test
	public void reorderedXMLTest() {
		try {
			boolean hitError = false;
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(true);
			builder = domFactory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void error(SAXParseException e) throws SAXException {
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					fail();
					throw e;
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					fail();
					throw e;
				}
			});
			Document doc = builder.parse("src/test/java/org/openmrs/module/dtd/testxml/reordered.xml");
			fail();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParserConfigurationException e) {
			fail();
		}
	}

	// The DTD throws an error if the XML file is out of order
	@Test
	public void syntaxErrorXMLTest() {
		try {
			boolean hitError = false;
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(true);
			builder = domFactory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void error(SAXParseException e) throws SAXException {
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					throw e;
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					fail();
					throw e;
				}
			});
			Document doc = builder.parse("src/test/java/org/openmrs/module/dtd/testxml/syntaxError.xml");
			fail();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParserConfigurationException e) {
			fail();
		}
	}
}
