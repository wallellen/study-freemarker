<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>
<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@l.layout title="Datetime">
<#include "_menu.ftl">
<div class="panel">
    <div class="panel-title">Datetime内置函数</div>
    <div class="panel-body">
        <ul class="demo">
            <@c.expression title="Formats" express=['datetime?string.short', 'datetime?string.medium', 'datetime?string.long', 'datetime?string.full']/>
            <@c.expression title="Parts of datetime" express=['datetime?string.yyyy', 'datetime?string.MM', 'datetime?string.dd', 'datetime?string.HH', 'datetime?string.mm', 'datetime?string.ss']/>
            <@c.expression title="Formatting" express=['datetime?string("yyyy-M-d H:m:s")']/>
            <@c.expression title="Date & time" express=['datetime?date', 'datetime?time']/>
            <@c.expression title="Local & UTC" express=['datetime?iso_local', 'datetime?iso_utc']/>
            <@c.expression title="Parse from string" express=['"2015-10-10 12:22"?datetime("yyyy-M-d H:m")']/>
        </ul>
    </div>
</div>
</@l.layout>