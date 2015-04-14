<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>

<@l.layout title="添加用户">
<div class="panel">
    <div class="panel-title">添加新用户</div>
    <div class="panel-body">
        <#assign method="post">
        <#include "_person_form.ftl">
    </div>
</div>
</@l.layout>