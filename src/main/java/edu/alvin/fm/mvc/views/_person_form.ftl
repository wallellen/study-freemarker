<#import "/edu/alvin/fm/core/templates/components.ftl" as c>

<@c.form action="/persons" method="${method}">
<input type="hidden" name="id" value="${(person.id)!0}">
<div class="row control-group">
    <label class="col-em-5">姓名：</label>
    <input type="text" name="name" class="form-control" value="${(person.name)!}">
    <@c.error for="name"/>
</div>
<div class="row control-group">
    <label class="col-em-5">性别：</label>
    <@c.dictSelect name="gender" class="form-control" dict={"M":"男", "F":"女"} selected=(person.gender)!"M" />
    <@c.error for="gender"/>
</div>
<div class="row control-group">
    <label class="col-em-5">生日：</label>
    <@c.birthdaySelect name="birthday" selected=(person.birthday)!.now class="form-control" />
    <@c.error for="birthday"/>
</div>
<div class="row control-group">
    <label class="col-em-5">电话：</label>
    <input type="text" name="telephone" class="form-control" value="${(person.telephone)!}">
    <@c.error for="telephone"/>
</div>
<div class="row control-group">
    <label class="col-em-5">电子邮件：</label>
    <input type="text" name="email" class="form-control" value="${(person.email)!}">
    <@c.error for="email"/>
</div>
<div class="row control-group center-text">
    <button type="submit">提交</button>
    <button type="button" onclick="location.href='/persons'">取消</button>
</div>
</@c.form>
