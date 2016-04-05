<html lang="en">
<head>
    <meta charset="utf-8">
    <title>userlist</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script type="text/javascript" src="../commons/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../commons/common.js"></script>
    <script type="text/javascript" src="../commons/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/uploadfile.js"></script>
</head>
<body style="">
<div style="text-align: center">
    hello ${name},${user};
    <div style="text-align: center">
        <a href="index">主页</a><br>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="form-group">
            <form id= "uploadForm">
                <p>指定文件名： <input type="text" name="filename" value= ""/></p>
                <p>上传文件： <input type="file" name="file"/></p>
                <input type="button" value="上传" onclick="doUpload()" />
            </form>
        </div>
    </div>
    <div class="row"><img id="imgShow" src=''/></div>
</div>


</body>


</html>