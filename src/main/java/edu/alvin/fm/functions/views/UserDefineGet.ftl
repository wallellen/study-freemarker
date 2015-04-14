<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>
<#import "../funcs/functions.ftl" as fn>
<#import "../macros/macros.ftl" as m>

<@l.layout title="User define functions & macros">
    <#include "_menu.ftl">

<div class="panel">
    <div class="panel-title">自定义函数和宏</div>
    <div class="panel-body">
        <ul class="demo">
            <@c.expression title="FTL functions" express=['fn.max(1, 2, 3, 4, 5)', 'min(1, 2, 3, 4, 5)']/>

            <li>
                <div class="title">FTL Macros</div>
                <div class="content">
                    <div class="express">
                    ${r'
<@m.repeater 5; num, has_next>\n
\t<b>${num}</b>${has_next?string(",", ".")}\n
</@m.repeater>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@m.repeater 5; num, has_next>
                            <b>${num}</b>${has_next?string(", ", ".")}
                        </@m.repeater>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'
<@m.select name="sel1" \n\titems={"a":"A","b":"B","c":"C"} selected="c">\n
\t<option value="">请选择</option>\n
</@m.select>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@m.select name="sel1" items={"a":"A","b":"B","c":"C"} selected="c">
                            <option value="">请选择</option>
                        </@m.select>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'
<@m.select name="sel2" items=items \n\tkey="id" text="name" selected=1>\n
\t<option value="">请选择</option>\n
</@m.select>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@m.select name="sel2" items=items key="id" text="name" selected=1>
                            <option value="">请选择</option>
                        </@m.select>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'<@m.radio name="rd1" \n\titems={"a":"A","b":"B","c":"C"} checked="b"/>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@m.radio name="rd1" items={"a":"A","b":"B","c":"C"} checked="b"/>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'<@m.radio name="rd2" items=items \n\tchecked=3 key="id" text="name"/>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@m.radio name="rd2" items=items checked=3 key="id" text="name"/>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'
<@select name="sel3" \n\titems={"a":"A","b":"B","c":"C"} selected="c">\n
\t<option value="">请选择</option>\n
</@select>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@select name="sel3" items={"a":"A","b":"B","c":"C"} selected="c">
                            <option value="">请选择</option>
                        </@select>
                    </div>
                </div>

                <div class="content">
                    <div class="express">
                    ${r'
<@select name="sel4" items=items \n\tkey="id" text="name" selected=2>\n
\t<option value="">请选择</option>\n
</@select>'
                    ?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <@select name="sel4" items=items key="id" text="name" selected=2>
                            <option value="">请选择</option>
                        </@select>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>
</@l.layout>