<html>
  <head>
      <meta charset="utf-8">
      <title>userlist</title>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <script type="text/javascript" src="../commons/jquery-1.9.1.js"></script>
      <script type="text/javascript" src="../commons/common.js"></script>
      <style type="text/css">
          table.zyhovertable {
              font-family:
                  verdana,arial,sans-serif;
              font-size:11px;
              color:#333333;
              border-width: 1px;
              border-color: #999999;
              border-collapse:
                  collapse;
          }

          table.zyhovertable th {
              background-color:#C3DDE0;
              border-width: 1px;
              padding: 8px;
              border-style: solid;
              border-color: #999999;
          }

          table.zyhovertable tr {
              background-color:#DCDCDC;
          }

          table.zyhovertable td {
              border-width: 1px;
              padding: 8px;
              border-style: solid;
              border-color: #999999;
          }
      </style>

  </head>
  <body style="background-color: #464646;">
      <div style="text-align: center">
           hello ${name},${user};
          <div style="text-align: center">
              <a href="index">主页</a><br>
          </div>
      </div>
      <div>人员信息</div>
      <div>
          <table class="zyhovertable">
              <tr>
                  <td>name:</td>
                  <td><input type="text" width="5%" id="name" name="name"></td>
              </tr>
              <tr>
                  <td>pass:</td>
                  <td><input type="password" width="5%" id="password" name="password"></td>
              </tr>
              <tr>
                  <td> email:</td>
                  <td><input type="text" width="5%" id="email" name="email"></td>
              </tr>
              <tr>
                  <td>code:</td>
                  <td><input type="text" width="5%" id="code" name="code"></td>
              </tr>
              <tr>
                  <td>operation：</td>
                  <td><input type="button" onclick="dataToMq()" value="发送"/></td>
              </tr>
          </table>
          <h1 id="getMsg"></h1>
      </div>
      <script type="text/javascript">
          function dataToMq(){
              var postData={
                  name:$("#name").val(),
                  code:$("#code").val(),
                  password:$("#password").val(),
                  email:$("#email").val()
              };
              postData=JSON.stringify(postData);
              $.ajax({
                  type: "POST",
                  url: '/mq/dateToMq',
                  dataType: 'json',
                  contentType: "application/json",
                  data:postData,
                  success: function (data) {
                      if (data.status == "OK") {
                          alert("发送成功");
                          removefullscreenLoading();
                      }
                  },
                  error: function () {
                      removefullscreenLoading();
                      console.log("发送失败！")
                  }
              });
          }
      </script>
  </body>


</html>