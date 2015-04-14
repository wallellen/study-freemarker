<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/common.css">
</head>
<body>
<div class="page-container">
<#if model??>
    <div class="panel">
        <div class="panel-title">
            Hello
        </div>
        <div class="panel-body">
            您好，<span class="strong">${model.name}</span><span>${(model.gender=="M")?string("先生", "女士")}</span>，
            现在时间${.now?string("yyyy年MM月dd日 HH时mm分")}
        </div>
    </div>
<#else>
    <div>暂无有效数据</div>
</#if>
</div>
</body>
</html>