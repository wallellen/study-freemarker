<#import "/edu/alvin/fm/core/templates/layout.ftl" as l>

<#assign footer>
<script type="text/javascript">
    $.ready(function () {
        $.each(document.getElementsByClassName('btn-delete'), function () {
            this.on('click', function (e) {
                e = e || window.event;
                e.cancelDefault();

                var src = e.srcElement || e.target;
                var id = src.getAttribute("data-id");

                var form = document.createElement('form');
                form.setAttribute('action', '/persons?_method=delete&id=' + id);
                form.setAttribute('method', 'post');
                form.submit();
            });
        });
    });
</script>
</#assign>

<@l.layout title="用户列表" footer=footer>
<div class="panel">
    <div class="panel-title">
        <span class="title">用户列表</span>
    </div>
    <div class="panel-body">
        <div class="btn-bar">
            <a href="/persons/new" class="pull-right">添加</a>
        </div>
        <#if persons?has_content>
            <table class="table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>电话</th>
                    <th>电子邮件</th>
                    <th style="width: 100px">操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list persons as person>
                    <tr>
                        <td>${(person.id)!}</td>
                        <td>${(person.name)!}</td>
                        <td>${(person.age)!}岁</td>
                        <td>${(person.telephone)!}</td>
                        <td>${(person.email)!}</td>
                        <td>
                            <a href="/persons/edit?id=${(person.id)!0}" class="btn-edit">编辑</a> |
                            <a href="#" data-id="${(person.id)!0}" class="btn-delete">删除</a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <div>暂无数据</div>
        </#if>
    </div>
</div>
</@l.layout>