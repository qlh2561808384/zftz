<%@ page language="java" import="com.datanew.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileName =request.getParameter("fileName");
String targetVolume =request.getParameter("targetVolume");
String xzqh=(String)request.getSession().getAttribute(StaticData.XZQH);
String cip=request.getRemoteHost();
String dcip=cip.substring(0, 3);
String murl=ConfigureParser.getPropert("nwbbdz");
if(dcip.equals("127")){
	murl=ConfigureParser.getPropert("nwbbdz");
}else if(dcip.equals("10")){
	murl=ConfigureParser.getPropert("nwbbdz");
}else if(dcip.equals("172")){
	murl=ConfigureParser.getPropert("wwbbdz");
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'report_frame.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/common.css"/>
	<script src="./bootstrap2/js/jquery.js"></script>
	<script src="./bootstrap2/js/bootstrap.datanew.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(function(){
	    var murl="<%=murl%>";
	    var targetVolume="<%=targetVolume%>";
	    var fileName="<%=fileName%>";
	    var url=murl+"/HappyServer/hrServlet?fileName="+fileName+"&targetVolume="+targetVolume+"&authId=anonymous_admin";
		url+="&variants=yhxzqh="+"<%=xzqh%>";
		console.log(url);//yhxzqh
		$('#iframe').attr('src', url);
	});
	</script>
  </head>
  <body style="margin:0">
	<div class="container-row">
		<iframe id="iframe" name="iframe" runat="server" 
			src="" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes">
		</iframe>
		</div>	
	 </div>
	</body>
</html>
