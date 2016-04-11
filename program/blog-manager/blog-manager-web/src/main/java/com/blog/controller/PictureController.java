package com.blog.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.common.utils.JsonUtils;
import com.blog.service.PictureService;

@Controller
@RequestMapping("/blog/pic/")
public class PictureController {

	@Resource
	private PictureService pictureService;
	
	@RequestMapping("upload")
	@ResponseBody
	public String uplaodPic(MultipartFile file){
		Map<String,Object> resultMap = pictureService.uploadPicture(file);
		String json = JsonUtils.objectToJson(resultMap);
		return json;
	} 
}
