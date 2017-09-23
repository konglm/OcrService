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

package com.goldeneyes.service.impl;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.baidu.aip.ocr.AipOcr;
import com.goldeneyes.service.OcrService;


/**
 * @author Administrator
 *
 */
@Service("ocrService")
public class OcrServiceImpl implements OcrService {
	//设置APPID/AK/SK
    public static final String APP_ID = "10175182";
    public static final String API_KEY = "BqBTNGh7NFK8KUFLhzVi3FN7";
    public static final String SECRET_KEY = "YXOuo1zcrNSjFe58hawLlpuBYsuKqsSj";

	/**
	 *  @author Administrator
	 */
	@Override
	public String getJsonFromImg(String imgUrl) throws Exception {
		// TODO Auto-generated method stub
		 // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        JSONObject response3 = client.basicGeneralUrl(imgUrl, new HashMap<String, String>());
		return response3.toString();
	}

}
