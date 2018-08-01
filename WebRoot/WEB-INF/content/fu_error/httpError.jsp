<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
<title>${title}－错误页面</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
.bottom_yun{ position: absolute; bottom:0; left:0;}
.bottom_yun img{ height:250px;}
a.cuowu_fhsy{ width:129px; height:32px; height:32px; text-align:center; background:#eef8fa; border-radius:20px; display:block; margin:0 auto; display:block;}
</style>
</head>
<body style="background:#2db1e1;">
<div style="width:1000px; margin:0 auto">
    <div style="width:500px; margin:30px auto"><img src="${ctx}/qihuo_images/403_02.png" width="500" height="463"></div>
    <a href="#" class="cuowu_fhsy"><img src="${ctx}/qihuo_images/404_05.png" onclick="location.href='/'" width="130" height="36"></a>
</div>
<div class=" bottom_yun"><img src="${ctx}/qihuo_images/404_08.png"  width="1920" height="357"></div>
</body>
</html>
<script>
$(function(){
 setTimeout("location.href='/'",5000);
});
</script>