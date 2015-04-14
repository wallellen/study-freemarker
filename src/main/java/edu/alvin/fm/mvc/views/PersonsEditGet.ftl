<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>

<@l.layout title="编辑用户">
<div class="panel">
    <div class="panel-title">编辑用户</div>
    <div class="panel-body">
        <#assign method="put">
        <#include "_person_form.ftl">
    </div>
</div>
</@l.layout>