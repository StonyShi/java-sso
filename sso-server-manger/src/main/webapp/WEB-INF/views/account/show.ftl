<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Show user info</title>
    <style type="text/css">
        .clear{
            clear:both;
            height: 20px;
        }
        body{
            overflow-x : hidden;
        }
        tr{
            text-align: center
        }
        td{
            text-align: center;
            white-space: nowrap;
        }
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: center;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
        .even {
            background-color: #DFDFDF;
            background: #DFDFDF;
        }
        .odd {
            background-color: #fff;
            background: #fff;
        }
        .over{
            background-color:#b6c6d7;
        }
    </style>
</head>
<body>

<table style="width: 85%;" class="gridtable">
<#if account?exists>
    <tr>
        <td>${account.name!}</td>
        <td>${account.phone!}</td>
        <td>${account.gender!}</td>
        <td>${account.city_id!}</td>
    </tr>
</#if>
</table>
</body>
</html>