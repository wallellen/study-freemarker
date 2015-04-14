<!--suppress ALL -->

<#macro form method="post" attrs...>
    <#if method?lower_case == "get">
        <#assign formMethod = "get">
    <#else>
        <#assign formMethod = "post">
    </#if>
<form method="${formMethod}" <#list attrs?keys as key>${key}=${attrs[key]}</#list>>
<input type="hidden" name="_method" value="${method}">
    <#nested/>
</form>
</#macro>

<#macro dictSelect id="" name="" selected="M" dict={} attrs...>
<select id="${id}" name=${name} <#list attrs?keys as key>${key}=${attrs[key]}</#list>>
    <#list dict?keys as key>
        <option value="${key}" ${(key==selected)?string("selected", "")}>${dict[key]}</option>
    </#list>
</select>
</#macro>

<#macro rangeSelect id="" name="" begin=0 end=0 selected=0 attrs={}>
<select <#list attrs?keys as key>${key}=${attrs[key]}</#list>>
    <#list (begin)..(end) as val>
    <option value="${val}" ${(val == selected)?string("selected", "")}>${val}</option>
    </#list>
</select>
</#macro>

<#macro birthdaySelect id="" name="" selected=.now attrs...>
<span id="${id}">
    <@rangeSelect selected=selected?string.yyyy?number
    begin=.now?string.yyyy?number - 80
    end=.now?string.yyyy?number - 10 attrs=attrs /> 年

    <@rangeSelect selected=selected?string.MM?number
    begin=1 end=12 attrs=attrs /> 月

    <@rangeSelect attrs=attrs /> 日

    <input type="hidden" name="${name}">
    <#if name?has_content>
        <script type="text/javascript">
            (function () {
                var selHidden = document.getElementsByName('${name}')[0];
                if (selHidden && selHidden.tagName === 'INPUT') {
                    var sels = selHidden.parentNode.getElementsByTagName('select');
                    var dateChanged = function (e) {
                        selHidden.value = new Date(sels[0].value, sels[1].value - 1, sels[2].value).toISOString().replace(".000Z", "Z");
                    };
                    var yearMonthChanged = function (e) {
                        var date = new Date(sels[0].value, sels[1].value, 1);
                        date.setTime(date.getTime() - 86400000);
                        sels[2].options.length = 0;
                        for (var n = 1; n <= date.getDate(); n++) {
                            sels[2].add(new Option(n, n));
                        }
                        dateChanged(e);
                    };
                    for (var i = 0; i < 2; i++) {
                        sels[i].on('change', yearMonthChanged);
                    }
                    sels[2].on('change', dateChanged);
                    yearMonthChanged(null);
                }
            })();
        </script>
    </#if>
</span>
</#macro>

<#macro error for="">
    <#if (for?has_content && ERROR_MESSAGE??)>
    <div class="error">${ERROR_MESSAGE.getMessage(for)}</div>
    </#if>
</#macro>

<#macro expression title="" express=[]>
    <#if express?has_content>
    <li>
        <div class="title">${title}</div>
        <#list express as exp>
            <div class="content">
                <div class="express">${(r"${" + exp + "}")?html}:</div>
                <div class="result">${exp?eval}</div>
            </div>
        </#list>
    </li>
    </#if>
</#macro>