<#function max nums...>
    <#list nums as num>
        <#if num_index == 0>
            <#assign _max = num/>
        <#else>
            <#if (num > _max)>
                <#assign _max = num />
            </#if>
        </#if>
    </#list>
    <#return _max />
</#function>