package au.com.innodev.appianplugin.markupgen.internal.gen;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.com.innodev.appianplugin.markupgen.internal.gen.StringHtmlPartGen;

public class StringHtmlPartGenTest {

	@Test
	public void test() {
		String text = "text with <angle brackets> 'apostrophe' and \"double quotes\"";
		String output = new StringHtmlPartGen(text).getOutput();
		
		assertEquals("text with &lt;angle brackets&gt; 'apostrophe' and &quot;double quotes&quot;", output);
	}

}
