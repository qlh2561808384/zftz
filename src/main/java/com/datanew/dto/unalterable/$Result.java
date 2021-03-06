package com.datanew.dto.unalterable;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
public class $Result {

	private boolean success ;// 是否成功
	private String msg ;// 提示信息
	private Object obj ;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
	public static $Result success(){
		$Result result=new $Result();
		result.setSuccess(true);
		result.setMsg("操作成功");
		return result;
	}
	public static $Result fail(){
		$Result result=new $Result();
		result.setSuccess(false);
		result.setMsg("操作失败");
		return result;
	}
	public void setContent(Object msg) {
    		this.obj=msg;
    	}
}
