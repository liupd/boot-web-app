# boot-web-app


用到的框架：springboot mybatis-plus  durid  spring  mq  
文件上传等

可扩展


文件采用 web开发  端口号在代码里写死了  8099 可以自己修改

文件中包含了 use_file 文件下 有nginx代理 windows版本  双击exe启动即可 
可以代理 本地的8089端口  本工程只是代理本地目录d盘文件的
改配置文件可以扩展
application.yml 进行了主文件配置
 
用了点对点的activemq 进行发送消息 监听消息 可以扩展
有一个activemq压缩包  可以进行解压 里面在bin，目录下有32和64位的exe可以直接启动即可
在浏览器中输入：http://localhost:8161/admin/  访问

本文出自qq：1326465728












