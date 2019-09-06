# Appian plugin: HTML / XML Markup Generation Functions

## Introduction
This plugin provides Appian functions that generate HTML and XML contents with properly-escaped characters. It can be used to produce HTML email content, XML to be fed into plugins or services, etc.

By escaping characters by default rather than by exception, it provides a better abstraction that minimises the effort and risk compared to using text concatenation and selectively calling functions such as _toHtml_. It also provides a mechanism for less error-prone content generation, by automatically opening and closing tags and attributes.

## Example
For example, instead of manually escaping HTML like in this excerpt:
```javascript
"<p class=""info"">" & 
    "<b>" & "Message for " & toHtml(ri!name) & "</b>" &
     toHtml(ri!message) &
     "<a href=""" & ri!url & """>Click here to " & ri!action & "</a>" &
"</p>"
```
Even though the content is short, there are a lot of things that could go wrong, such as not properly closing an element by simply forgetting the corresponding close tag. Also. notice the double quotes for attribute such as class and href. It'd be very easy to miss a start or end-of-attribute value double quote. Lastly, we're being forced to remember to call _toHtml_ in several places.

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

Notice how the language syntax helps us create valid HTML content. Also notice that the HTML text is escaped by default; we don't need to remember to call a specific function to do this.

Admittedly, the lengthy function names get in the way a bit, but you may choose to wrap common tags in new expression rules within your project.

## Usage
**For HTML Generation**:
- `MarkupGen_html_newElem` Creates a new HTML element, such as `<p>` or `<a href="x">`.
- `MarkupGen_html_toText` Converts an element or list of HTML elements to text.
- `MarkupGen_html_newTextPart` (Less common): Creates a new element for text. It's necessary only when you need to use a uniform list of the _MarkupGen_HtmlPart_ data type.
- `MarkupGen_html_newRawPart` (Less common): Creates a new element with raw HTML. Use it with a lot of caution. Make sure that the raw content is trusted and doesn't contain parts with unsanitised user input.

**For XML Generation**:
- `MarkupGen_xml_newElem` Creates a new XML element, such as `<item>` or `<person>`.
- `MarkupGen_xml_toText` Converts an XML element or list of XML elements to text.
- `MarkupGen_xml_newTextPart` (Less common): Creates a new element for text. It's necessary only when you need to use a uniform list of the _MarkupGen_XmlPart_ data type.
- `MarkupGen_xml_newRawPart`  (Less common): Creates a new element with raw XML. Use it with a lot of caution. Make sure that the raw content is trusted and doesn't contain parts with unsanitised user input.

## Installing

- For Appian cloud instances, go to the Administrative console, select _Deploy New Plugin_ and look for this plugin by name. Note that you don't need to copy anything from this repository.
- For local Appian installations, add the JAR to the plugins folder of the Appian server.

## Compiling

- Import the source code as a Maven project into your IDE. 
- In order for the project to compile, you will need to copy Appian JAR files to the _internalRepoOnly_ folder. You will need to grab them from a local Appian installation. Due to licensing restrictions, these files can't be added to this public repository.
- You will also need to edit the POM.xml file to set the right _Appian compile version_ to properly reference the Appian libraries.
