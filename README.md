# Appian plugin: HTML / XML Markup Generation Functions

## Introduction
This plugin provides Appian functions that generate HTML and XML with properly-escaped characters. It can be used to produce HTML email content, XML to be fed into plugins or services, etc.

By escaping text automatically, it provides a better abstraction that minimises the effort and risk of using text concatenation and the 'toHtml' function to produce markup, whether it's HTML or XML. It also helps ensure well-formed content is produced, by automatically opening and closing tags, as well as attributes and its values.

For XML generation, 'toHtml' is not appropriate, most notably because it doesn't escape apostrophe - i.e., escaping it is required in XML but not in HTML.

## Example
For example, instead of manually escaping HTML like in this excerpt:
```javascript
"<p class=""info"">" & 
    "<b>" & "Message for " & toHtml(ri!name) & "</b>" &
     toHtml(ri!message) &
     "<a href=""" & ri!url & """>Click here to " & ri!action & "</a>" &
"</p>"
```
Even though the content is short, there's a lot of things that could go wrong, such as not properly closing a tag by simply forgetting the corresponding element. Also. notice the double quotes for attribute such as class and href. It'd be very easy to miss a start or end-of-attribute value double quote. Lastly, we're being forced to remember to call toHtml in several places.

With this plugin you may create the same output like this:
```javascript
MarkupGen_html_toText(
  MarkupGen_html_newElem("p",  {class: "info"}, 
  {
    MarkupGen_html_newElem("b", null, "Message for " & ri!name),
    ri!message,
    MarkupGen_html_newElem( "a",  { href: ri!url },
    "Click here to " & ri!action
    )    
  }
  )
)
```

Notice how the language syntax helps us create valid HTML content. Furthermore, the HTML escaping happens by default; we don't need to remember to call a specific function to escape text.

Admittedly, the lengthy function names get in the way a bit, but you may choose to wrap common tags in new expression rules within your project.

## Usage
For HTML Generation:
- `MarkupGen_html_newElem` Creates a new HTML element, such as `<p>` or `<a href="x">`.
- `MarkupGen_html_toText` Converts an element or list of elements to text.
- `MarkupGen_html_newTextPart` (Less common): Creates a new element for text.
- `MarkupGen_html_newRawPart` (Less common): Creates a new element with raw HTML.


For XML Generation:
- `MarkupGen_xml_newElem` Creates a new XML element, such as `<item>` or `<person>`.
- `MarkupGen_xml_toText` Converts an XML element to text.
- `MarkupGen_xml_newTextPart` (Less common): Creates a new element for text.
- `MarkupGen_xml_newRawPart`  (Less common): Creates a new element with raw XML.

## Installing

- For Appian cloud instances, look for the plugin in the Plugins section of the Administrative console.
- For local Appian installations, add the JAR to the plugins folder of the Appian server.

## Compiling

Use the Maven project in your IDE. In order for the project to compile, you will need to copy Appian JAR files to the internalRepoOnly folder. You will also need to edit the POM.xml file to set the right Appian compile version to properly reference the Appian files.
