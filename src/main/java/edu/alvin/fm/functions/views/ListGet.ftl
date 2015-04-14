<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@l.layout title="List">
<#include "_menu.ftl">
<div class="panel">
    <div class="panel-title">List内置函数</div>
    <div class="panel-body">
        <ul class="demo">
            <@c.expression title="List" express=['list?join(", ")']/>
            <@c.expression title="Get element" express=['list[1]', 'list?first', 'list?last']/>
            <@c.expression title="Get element by range" express=['list[1..3]?join(", ")']/>
            <@c.expression title="Size of list" express=['list?size']/>
            <@c.expression title="Reverse list" express=['list?reverse?join(", ")']/>
            <@c.expression title="Sorted list" express=['list?sort?join(", ")']/>
            <@c.expression title="Find element" express=['list?seq_contains("o")?c', 'list?seq_index_of("l")', 'list?seq_index_of("l", 4)', 'list?seq_last_index_of("l")', 'list?seq_last_index_of("l", 7)']/>

            <li>
                <div class="title">Loop</div>
                <div class="content">
                    <div class="express">
                    ${r'
<#list list as item>\n
\t<#if item_index == 0>[</#if>\n
\t<span>${item}</span>\n
\t<#if !item_has_next>]</#if>\n
</#list>
                        '?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <#list list as item>
                            <#if item_index == 0>[</#if>
                            <span>${item}</span>
                            <#if !item_has_next>]</#if>
                        </#list>
                    </div>
                </div>
            </li>

            <li>
                <div class="title">Thunk</div>
                <div class="content">
                    <div class="express">
                    ${r'
<#list list?chunk(2) as items>\n
\t<span>${items?join(", ")}</span>\n
</#list>
                        '?html?replace("\\n", "<br/>")?replace("\\t", "&nbsp;&nbsp;")}
                    </div>
                    <div class="result">
                        <#list list?chunk(2) as items>
                            <span>${items?join(", ")}</span>
                        </#list>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>
</@l.layout>