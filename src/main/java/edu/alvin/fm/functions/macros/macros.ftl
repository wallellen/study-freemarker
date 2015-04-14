<#macro repeater count>
    <#list 1..count as n>
        <#nested n, n_has_next>
    </#list>
</#macro>

<#macro select items selected="" key="" text="" attrs...>
<select <#if attrs??><#list attrs?keys as attr>${attr}="${attrs[attr]}"</#list></#if>>
    <#nested/>
    <#if key?has_content>
        <#list items as item>
        <option value="${item[key]}" <#if item[key] == selected>selected</#if>>${item[text]?html}</option>
        </#list>
    <#else>
        <#list items?keys as item>
        <option value="${item}" <#if item == selected>selected</#if>>${items[item]?html}</option>
        </#list>
    </#if>
</select>
</#macro>

<#macro radio name items checked="" key="" text="" attrs...>
    <#if key?has_content>
        <#list items as item>
        <input type="radio" name="${name}" value="${item[key]}"
            <#if attrs??>
                <#list attrs?keys as attr>
                ${attr}=${attrs[attr]?html}
                </#list>
            </#if>
            <#if item[key] == checked>checked</#if>><span>${item[text]?html}</span>
        </#list>
    <#else>
        <#list items?keys as item>
        <input type="radio" name="${name}" value="${item}"
            <#if attrs??>
                <#list attrs?keys as attr>
                ${attr}=${attrs[attr]?html}
                </#list>
            </#if>
            <#if item == checked>checked</#if>><span>${items[item]?html}</span>
        </#list>
    </#if>
</#macro>