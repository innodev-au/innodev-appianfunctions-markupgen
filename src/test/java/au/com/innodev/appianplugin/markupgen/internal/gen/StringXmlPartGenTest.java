package au.com.innodev.appianplugin.markupgen.internal.gen;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringXmlPartGenTest {

	@Test
	public void test() {
		String text = "text with <angle brackets> 'apostrophe' and \"double quotes\"";
		String output = new StringXmlPartGen(text).getOutput();
		
		assertEquals("text with &lt;angle brackets&gt; &apos;apostrophe&apos; and &quot;double quotes&quot;", output);
	}

}
