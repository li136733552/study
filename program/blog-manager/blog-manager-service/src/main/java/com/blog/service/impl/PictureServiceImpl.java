package com.blog.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.common.utils.FtpUtil;
import com.blog.common.utils.IDUtil;
import com.blog.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map uploadPicture(MultipartFile file) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			//得到原来的名字
			String oldName = file.getOriginalFilename();
			//工具生成新的名字
			String newName = IDUtil.getImageName();
			//取原来文件名的后缀
			String ext = oldName.substring(oldName.lastIndexOf("."));
			newName = newName + ext;
			String filePath = new DateTime().toString("yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASE_PATH, filePath, newName, file.getInputStream());
			if(result){
				resultMap.put("error",0);
				resultMap.put("url",IMAGE_BASE_URL + filePath + "/" + newName );
				return resultMap;
			}
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			e.printStackTrace();
		}
		return resultMap;
	}

}
