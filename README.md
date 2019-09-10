# Appian plugin: HTML / XML Markup Generation Functions

## Introduction
This plugin provides Appian functions that generate HTML and XML content with properly-escaped characters. It can be used to produce HTML email content, XML to be fed into plugins or services, etc.

By **escaping characters by default rather than by exception**, it provides a better abstraction that minimises the effort and risk compared to using text concatenation and selectively calling functions such as _toHtml_. It also provides a mechanism for less error-prone content generation, by automatically opening and closing tags and attributes.

## Example
As a point of comparison, consider the following code excerpt for HTML generation. It uses text concatenation and manual function calls to escape values.
```javascript
"<p class=""info"">" & 
    "<b>" & "Message for " & toHtml(ri!name) & "</b>" &
     toHtml(ri!message) &
     "<a href=""" & ri!url & """>Click here to " & ri!action & "</a>" &
"</p>"
```
Even though the content is short, there are a lot of things that could go wrong:
- We're being forced to remember to call _toHtml_ in several places. We could forget to do it, such as what happend with ri!action, and the content would not be escaped.
    - The impact is that the content could be broken and not render as expected. 
    - In more severe cases, the output may be succeptible to malicious alteration, with foreign elements being injected through user input or other means. Depending on where the generated content goes to, this may be more or less of a concern, but it's definitely something to always take into account and a very strong reason to use functionality like the one provided by this plugin. 
    - There might be places where there would never be a no need to escape parts of text. However, being forced to think where to apply escaping is problematic.
- When several HTML tags are involved, it's easy to forget closing one of them or misplacing the tag close.
- Also notice the double quotes for attribute such as _class_ and _href_. It would be very easy to miss the start or end-of-attribute value double quote.

With this plugin you may produce the same output like this:
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

## Installing

Look for ``HTML / XML Markup Generation`` in the plugin deployment page of your Appian cloud installation. For more details, refer to [installation](https://github.com/innodev-au/innodev-appianfunctions-markupgen/wiki/Installing]).

## Documentation

Refer to the [documentation](https://github.com/innodev-au/innodev-appianfunctions-markupgen/wiki) section for more information, including the [user guide](https://github.com/innodev-au/innodev-appianfunctions-markupgen/wiki/User-Guide) and [reference](https://github.com/innodev-au/innodev-appianfunctions-markupgen/wiki/Reference).


