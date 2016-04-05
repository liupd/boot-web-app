<html>
<head>
    <meta charset="utf-8">
    <title>welcome</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
    <style>
        body{
            margin: 0;
            padding: 0;
            background-color: #464646;
            font-family: 微软雅黑"\5FAE\8F6F\96C5\9ED1", "微软雅黑", "Microsoft YaHei", Helvetica, Tahoma, sans-serif;
        }

        h6,p{
            text-align: center;
        }

    </style>
</head>


<body>
    <div style="text-align:center;">
        <img src="../images/head.png" />
    </div>
    <div>
         <h6>启动成功</h6><br/>
         <h6>Welcome to ${name} world ${user} </h6>
    </div>
    <div style="text-align: center">
        <a href="userlist">添加人员</a><br>
        <br/>
        <a href="dataToMq">向mq发送数据</a><br>
        <br/>
        <a href="uploadfile">上传文件</a><br>
    </div>
    <p style="text-align: center">Copyright © 2016 power by ${user}</p>

</body>
</html>