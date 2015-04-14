<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@l.layout title="String">
    <#include "_menu.ftl">

<div class="panel">
    <div class="panel-title">String内置函数</div>
    <div class="panel-body">
        <ul class="demo">
            <@c.expression title="Output string" express=['str']/>
            <@c.expression title="Length of string" express=['str?length']/>
            <@c.expression title="Connect strings" express=['str + ", Welcome"']/>
            <@c.expression title="Substring" express=['str?substring(5)', 'str?substring(0, 5)']/>
            <@c.expression title="Index of string" express=['str?index_of("o")', 'str?index_of("o", 5)']/>
            <@c.expression title="Index of string(from last)" express=['str?last_index_of("o")', 'str?last_index_of("o", 6)']/>
            <@c.expression title="Find substring" express=['str?contains("llo")?c']/>
            <@c.expression title="Replace substring" express=['str?replace("o", "*")']/>
            <@c.expression title="Find string at beginning or ending" express=['str?starts_with("World")?c', 'str?ends_with("World")?c']/>
            <@c.expression title="Upper case or lower case" express=['str?upper_case', 'str?lower_case', 'str?lower_case?cap_first', 'str?lower_case?capitalize', 'str?uncap_first']/>
            <@c.expression title="Trim whitespace" express=['("  " + str + "  ")?trim']/>
            <@c.expression title="Pad string" express=['str?left_pad(20, "*")', 'str?right_pad(20, "*")']/>
            <@c.expression title="URI encoding" express=['"t.com?s=你好"?url("utf-8")']/>
            <@c.expression title="Html source" express=['"<i>Hello</i>"?html']/>
            <@c.expression title="Split string" express=['str?split(" ")?join("!")']/>
        </ul>
    </div>
</div>
</@l.layout>