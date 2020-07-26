function formatd(str){
	try 
	{ 
		if(str==null){
			return 0;
		}else{
			return parseFloat((""+str).replace(/,/g, ""));
		}
		
	} 
	catch (e) 
	{ 
		return 0;
	}
	
}
/**  
 * 将数值四舍五入(保留2位小数)后格式化成金额形式  
 *  
 * @param num 数值(Number或者String)  
 * @return 金额格式的字符串,如'1,234,567.45'  
 * @type String  
 */    
function formatCurrency(num) {
	if(num==null)return "";
    num = num.toString().replace(/\$|\,/g,'');    
    if(isNaN(num))    
    num = "0";    
    sign = (num == (num = Math.abs(num)));    
    num = Math.floor(num*100+0.50000000001);    
    cents = num%100;    
    num = Math.floor(num/100).toString();    
    if(cents<10)    
    cents = "0" + cents;    
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)    
    num = num.substring(0,num.length-(4*i+3))+','+    
    num.substring(num.length-(4*i+3));    
    return (((sign)?'':'-') + num + '.' + cents);    
}    


function lowerKey(arr){
	if(arr!=null){
		for(var i = 0, len = arr.length; i < len; i++) {
		    var o = arr[i];
		    for(var key in o){
		    	 if (key.toLowerCase()!=key) { // 判断是否需要转小写
		             o[key.toLowerCase()] = o[key];
		             delete(o[key]);
		         }
		    }
		}
	}
	return arr;
}
//url中取值     url 传值的方式为   aa=1&bb=2     如果有中文的话需要对参数值进行encode编码
function getUrlString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]).replace(/</g, '&lt;').replace(/>/g, '&gt;');
	return null;
}