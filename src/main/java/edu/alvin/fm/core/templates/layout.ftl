<#macro layout title="" header="" footer="">
    <#escape x as x?html>
    <!DOCTYPE html>
    <html>
    <head>
        <title>${title}</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="/assets/css/common.css">
        <script type="text/javascript" src="/assets/js/common.js"></script>
    <#noescape>${header}</#noescape>
    </head>
    <body>
    <div class="page-container">
        <#nested/>
    <#noescape>${footer}</#noescape>
    </div>
    </body>
    </html>
    </#escape>
</#macro>