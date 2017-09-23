/*----------------------------------------------------------------
 *  Copyright (C) 2017山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：
 * 文件功能描述：
 *
 * 
 * 创建标识：
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldeneyes.service.OcrService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class OcrController {    
	@Resource 
	OcrService ocrService;
	
	@RequestMapping("/getJsonFromImg")
	public void getJsonFromImg(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
			return;
		}

		if (!jsonInput.has("imgUrl")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
		} else {
			String imgUrl = "";
			try {
				imgUrl = jsonInput.getString("imgUrl");
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}

			String strOut = "";
			try {
				strOut = ocrService.getJsonFromImg(imgUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", strOut);
			// 在这里输出，手机端就拿到web返回的值了
			if(strOut.contains("error_code")){
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "9999").toString());
			} else {			
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
			}
		}
	}
}
