package au.com.innodev.appianplugin.markupgen.internal.gen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class HtmlElementGenTest {

	@Test
	public void element_pWithText() {
		
		String name = "p";
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("class", "myClass").build();
		HtmlPartGen element1 = new StringHtmlPartGen("Please note the <angle> brackets");		
		List<HtmlPartGen> contents = Lists.newArrayList(element1);

		HtmlElementGen tagElement = new HtmlElementGen(name, attrs, contents, TagCloseStyle.ALWAYS_SHOW_CLOSING_TAG);
		StringBuilder sb = new StringBuilder();
		
		tagElement.generate(sb);
		
		assertEquals("<p class=\"myClass\">Please note the &lt;angle&gt; brackets</p>", sb.toString());
	}

	@Test
	public void element_br_selfClosingTag() {
		
		HtmlElementGen tagElement = new HtmlElementGen("br", HtmlElementGen.emptyAttributes(), HtmlElementGen.emptyContent(), TagCloseStyle.SELF_CLOSING_TAG);
		
		StringBuilder sb = new StringBuilder();
		tagElement.generate(sb);
		
		assertEquals("<br />", sb.toString());
	}
	
	@Test
	public void element_span_notSelfClosingTag() {
		
		HtmlElementGen tagElement = new HtmlElementGen("span", HtmlElementGen.emptyAttributes(), HtmlElementGen.emptyContent(), TagCloseStyle.ALWAYS_SHOW_CLOSING_TAG);
		
		StringBuilder sb = new StringBuilder();
		tagElement.generate(sb);
		
		assertEquals("<span></span>", sb.toString());
	}
	
	@Test
	public void element_br_selfClosingTagWithContents_Invalid() {
		List<HtmlPartGen> contents = Arrays.<HtmlPartGen>asList(new StringHtmlPartGen("dummy text"));
		
		try {
			new HtmlElementGen("br", HtmlElementGen.emptyAttributes(), contents, TagCloseStyle.SELF_CLOSING_TAG);
			fail();
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("self-closing"));
			assertTrue(e.getMessage().contains("contents"));
		}
		
	}
	
	@Test
	public void validateAttributeName_InvalidWithAngleBrackets() {
		try {
			new HtmlElementGen("myTag<withAngle", HtmlElementGen.emptyAttributes(), HtmlElementGen.emptyContent(), TagCloseStyle.ALWAYS_SHOW_CLOSING_TAG);
			fail();
		}
		catch(Exception e) {
			assertTrue(e.getMessage().contains("Invalid"));
			assertTrue(e.getMessage().contains("myTag<withAngle"));			
		}
	}
	
	@Test
	public void attrAndContentEscaping() {
		
		String name = "a";
		Map<String, String> attrs = ImmutableMap.<String, String>builder().put("href", "someLocation\"with'special&chars<>").put("data-myData1", "d&1").build();
		HtmlPartGen element1 = new StringHtmlPartGen("Content with special chars:\"<>&'aeoiu");
		List<HtmlPartGen> contents = Lists.newArrayList(element1);
		HtmlElementGen tagElement = new HtmlElementGen(name, attrs, contents, TagCloseStyle.ALWAYS_SHOW_CLOSING_TAG);
		StringBuilder sb = new StringBuilder();
		
		tagElement.generate(sb);
		
		String expected = "<a href=\"someLocation&quot;with'special&amp;chars&lt;&gt;\" data-myData1=\"d&amp;1\">Content with special chars:&quot;&lt;&gt;&amp;'aeoiu</a>";
		assertEquals(expected, sb.toString());
	}
	


}
