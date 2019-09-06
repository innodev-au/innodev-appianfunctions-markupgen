package au.com.innodev.appianplugin.markupgen.internal.gen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class XmlElementGenTest {

	@Test
	public void element_WithText() {
		
		String name = "book";
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("price", "15.1").build();
		XmlPartGen element1 = new StringXmlPartGen("The \"Story\"");		
		List<XmlPartGen> contents = Lists.newArrayList(element1);

		XmlElementGen tagElement = new XmlElementGen(name, attrs, contents);
		StringBuilder sb = new StringBuilder();
		
		tagElement.generate(sb);
		
		assertEquals("<book price=\"15.1\">The &quot;Story&quot;</book>", sb.toString());
	}

	@Test
	public void element_WithNamespace() {
		
		String name = "w:book";
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("w:price", "15.1").build();
		XmlPartGen element1 = new StringXmlPartGen("The \"Story\"");		
		List<XmlPartGen> contents = Lists.newArrayList(element1);

		XmlElementGen tagElement = new XmlElementGen(name, attrs, contents);
		StringBuilder sb = new StringBuilder();
		
		tagElement.generate(sb);
		
		assertEquals("<w:book w:price=\"15.1\">The &quot;Story&quot;</w:book>", sb.toString());
	}
	
	@Test
	public void noAttrsOrContents_selfClosingTag() {
		
		XmlElementGen tagElement = new XmlElementGen("continue", XmlElementGen.emptyAttributes(), XmlElementGen.emptyContent());
		
		StringBuilder sb = new StringBuilder();
		tagElement.generate(sb);
		
		assertEquals("<continue />", sb.toString());
	}	
	
	@Test
	public void noContents_selfClosingTag() {
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("price", "15.1").build();
		XmlElementGen tagElement = new XmlElementGen("book", attrs, XmlElementGen.emptyContent());
		
		StringBuilder sb = new StringBuilder();
		tagElement.generate(sb);
		
		assertEquals("<book price=\"15.1\" />", sb.toString());
	}	
	
	
	
	@Test
	public void validateAttributeName_InvalidWithAngleBrackets() {
		try {
			new XmlElementGen("myTag<withAngle", XmlElementGen.emptyAttributes(), XmlElementGen.emptyContent());
			fail();
		}
		catch(Exception e) {
			assertTrue(e.getMessage().contains("Invalid"));
			assertTrue(e.getMessage().contains("myTag<withAngle"));			
		}
	}
	
	@Test
	public void attrAndContentEscaping() {
		
		String name = "book";
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("desc", "someLocation\"with'special&chars<>").build();
		XmlPartGen element1 = new StringXmlPartGen("Book with special chars:\"<>&'aeoiu");
		List<XmlPartGen> contents = Lists.newArrayList(element1);
		XmlElementGen tagElement = new XmlElementGen(name, attrs, contents);
		StringBuilder sb = new StringBuilder();
		
		tagElement.generate(sb);
		
		String expected = "<book desc=\"someLocation&quot;with&apos;special&amp;chars&lt;&gt;\">Book with special chars:&quot;&lt;&gt;&amp;&apos;aeoiu</book>";
		assertEquals(expected, sb.toString());
	}

}
