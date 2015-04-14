<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@l.layout title="Map">
<#include "_menu.ftl">

<div class="panel">
    <div class="panel-title">Map内置函数</div>
    <div class="panel-body">
        <ul class="demo">
            <li>
                <div class="title">Loop</div>
                <div class="content">
                    <div class="express">
                    ${r'
<#list map?keys as key>\n
\t[${key}=>${map[key]}]\n
\t<#if key_has_next>,</#if>\n
</#list>
                        '?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <#list map?keys as key>
                            [${key}=>${map[key]}]<#if key_has_next>,</#if>
                        </#list>
                    </div>
                </div>
            </li>

            <@c.expression title="Find element" express=['map["a"]', 'map["b"]']/>
            <@c.expression title="Size of map" express=['map?size']/>
            <@c.expression title="Keys of map" express=['map?keys?join(", ")']/>
            <@c.expression title="Values of map" express=['map?values?join(", ")']/>
        </ul>
    </div>
</div>
</@l.layout>