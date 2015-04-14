<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@l.layout title="Number">
<#include "_menu.ftl">

<div class="panel">
    <div class="panel-title">Number内置函数</div>
    <div class="panel-body">
        <ul class="demo">
            <@c.expression title="Output number" express=['num']/>
            <@c.expression title="Calculate" express=['((num + 20) / 10) % 5']/>
            <@c.expression title="To string" express=['num?c + "..."']/>
            <@c.expression title="Parse from string" express=['"100.200"?number + 100']/>
            <@c.expression title="To integer" express=['num?int']/>
            <@c.expression title="To float" express=['num?double']/>
            <@c.expression title="Formatting" express=['num?string("#.##")', 'num?string("0.00")']/>
            <@c.expression title="To currency" express=['num?string("currency")']/>
            <@c.expression title="To percent" express=['num?string("percent")']/>
        </ul>
    </div>
</div>
</@l.layout>