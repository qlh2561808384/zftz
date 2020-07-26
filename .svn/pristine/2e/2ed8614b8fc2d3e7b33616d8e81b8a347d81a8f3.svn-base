<%@ page language="java" import="com.datanew.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sysName =request.getParameter("sysName");
String cip=request.getRemoteHost();
String dcip=cip.substring(0, 3);
String murl="";
String sessionid=request.getSession().getId();
String code=RandomUtil.randomFor6();
request.getSession().setAttribute("sid", sessionid);
request.getSession().setAttribute("dlcode", code);
String bbcode=(String)request.getSession().getAttribute(StaticData.XZQH);
//String bbcode="33060101,33060104,330624";
String bstring=sessionid+"_"+bbcode+"_"+code;
System.out.println(bbcode);
DesUtil des = new DesUtil();
String destring=des.encrypt(bstring);
System.out.println("sessionid="+sessionid+"code=="+code);
System.out.println(destring);

if(sysName.equals("editdlxx")){
		murl=ConfigureParser.getPropert("nwdlxx");
	if(dcip.equals("127")){
		murl=ConfigureParser.getPropert("nwdlxx");
	}else if(dcip.equals("10")){
		murl=ConfigureParser.getPropert("nwdlxx");
	}else if(dcip.equals("172")){
		murl=ConfigureParser.getPropert("wwdlxx");
	}
	murl+="/#/editable?vtoken="+destring+"&dxlx=01&YYBM=ZFTZ";
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
	    console.log(murl);
		$('#iframe').attr('src', murl);
	});
	</script>
  </head>
  <body style="margin:0;">
	<div class="container-row" >
		<iframe id="iframe" name="iframe" runat="server" 
			src="" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes">
		</iframe>
		</div>	
	 </div>
	</body>
</html>
