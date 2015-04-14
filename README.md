## Freemarker settings:  ##
> Copy from Freemarker Home.

*  **`locale`**: The locale (language) of the output. It can influence the presentation format of numbers, dates, etc. The value is a string which consist of a language code (lowercase two-letter `ISO-639` code) plus optional county code (uppercase two-letter `ISO-3166` code) separated from the language code with underscore, and if we have specified the country then an optional variant code (not standardized) separated from the country with underscore. Examples of valid values: `en`, `en_US`, `en_US_MAC`. FreeMarker will try to use the most specific available locale, so if you specify `en_US_MAC` but that is not known, then it will try `en_US`, and then en, and then the default locale of the computer (which is may set by the programmer).

* **`number_format`**: The number format that is used to convert numbers to strings when no explicit format is specified. Can be one of predefined values `number` (the default), `computer`, `currency`, or `percent`. Additionally, arbitrary format pattern written in ***Java decimal number format syntax*** can also be specified. More information about format patterns:***string built-in***.

* **`boolean_format`**: The comma-separated pair of strings for representing true and false values respectively that is used to convert booleans to strings when no explicit format is specified. Default value is "`true,false`". See also:***string built-in***.

* **`date_format`**, **`time_format`**, **`datetime_format`**: The format used to convert date/time/date-time values (Java `java.util.Date-s` and its subclasses) to strings when no explicit format is specified via the ***string built-in*** (or otherwise), as in the case of `${someDate}`. The `date_format` setting only effects the formatting of date values that store no time part, `time_format` only effects the formatting of times that store no date part, and `datetime_format` only effects formatting of date-time values. These settings also effects what format do ***?time, ?date, and ?datetime*** expect when it's applied on a string value.
The possible setting values are (the quotation marks aren't part of the value itself):
 * `Patterns` ***accepted by Java's SimpleDateFormat***, for example "`dd.MM.yyyy HH:mm:ss`" (where "`HH`" means 0-23 hours) or "`MM/dd/yyyy hh:mm:ss a`" (where "`a`" prints AM or PM, if the current language is English).
 
 * "`xs`" for XML Schema format, or "`iso`" for ISO 8601:2004 format. These formats allow various additional options, separated with space, like in "`iso m nz`" (or with _, like in "`iso_m_nz`"; this is useful in a case like `lastModified?string.iso_m_nz`). The options and their meanings are:

 	 * **Accuracy options:**
         * `ms`: Milliseconds, always shown with all 3 digits, even if it's all 0-s. Example: 13:45:05.800
         * `s`: Seconds (fraction seconds are dropped even if non-0), like 13:45:05
         * `m`: Minutes, like 13:45. This isn't allowed for "xs".
         * `h`: Hours, like 13. This isn't allowed for "xs".
         * Neither: Up to millisecond accuracy, but trailing millisecond 0-s  are removed, also the whole milliseconds part if it would be 0 otherwise. Example: 13:45:05.8
     * **Time zone offset visibility options**:
         * `fz`: "Force Zone", always show time zone offset (even for for java.sql.Date and java.sql.Time values). But, because ISO 8601 doesn't allow for dates (means date without time of the day) to show the zone offset, this option will have no effect in the case of "iso" with dates.
         * `nz`: "No Zone", never show time zone offset
         * Neither: Always show time zone offset, except for java.sql.Date and java.sql.Time, and for "iso" date values.
     * **Time zone options**:
         * `u`: Use UTC instead of what the `time_zone` setting suggests. However, `java.sql.Date` and `java.sql.Time` aren't affected by this (see `sql_date_and_time_time_zone` to understand why)
         * `fu`: "Force UTC", that is, use UTC instead of what the `time_zone` or the `sql_date_and_time_time_zone` setting suggests. This also effects `java.sql.Date and java.sql.Time` values
         * Neither: Use the time zone suggested by the `time_zone` or the `sql_date_and_time_time_zone` configuration setting

* `time_zone`: The name of the time zone used to format times for display. By default, the default time zone of the JVM is used. Can be any value that is accepted by ***Java TimeZone API***, or "`JVM default`" (since FreeMarker 2.3.21) to use the JVM default time zone. Examples: "`GMT`", "`GMT+2`", "`GMT-1:30`", "`CET`", "`PST`", "`America/Los_Angeles`".

* `sql_date_and_time_time_zone` (since FreeMarker 2.3.21): This handles a highly technical issue, so it should usually be set from the Java code by the programmers. For programmers: If this is set to non-null, for date-only and time-only values coming from SQL database (more precisely, for `java.sql.Date` and `java.sql.Time` objects) FreeMarker will use this time zone instead of the time zone specified by the `time_zone` FreeMarker setting. See more in the Java API documentation of `Configurable.setSQLDateAndTimeTimeZone(TimeZone)`.

* `url_escaping_charset`: The charset used for URL escaping (e.g. for `${foo?url}`) to calculate the escaped (`%XX`) parts. Usually the framework that encloses FreeMarker should set it, so you hardly ever should set this setting in templates. (**Programmers can read more about this here...**)

* `output_encoding`: Tells FreeMarker what the charset of the output is. As FreeMarker outputs a stream of UNICODE characters (it writes into a `java.io.Writer`), it's not affected by the output encoding, but some macros/functions and built-ins may want to used this information.

* `default_encoding`: Set the default charset encoding to FreeMarker, even by input and output.

* `classic_compatible`: This is for experts. Its value should be a `boolean`. See the documentation of `freemarker.template.Configurable` for more information. 

* `template_update_delay`: How many second to update the all template, 0 if in develope mode (means upload everytime) 

* `classic_compatible`: turn of or off the classic mode (classic means freemarker 1.7 version), if is `true`, the `null` value would be processed as `empty string`, `false` or `nothing`.

* `template_exception_handler`: Set a handler instance to process the exceptions thrown by FreeMarker, set `ignore` to dispose all FreeMarker exception.