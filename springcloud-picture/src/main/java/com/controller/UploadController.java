package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.util.AliyunOSSUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("upload")
public class UploadController {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "uploadOss", method = RequestMethod.POST)
	@ResponseBody
	public String uploadBlog(@RequestParam MultipartFile[] file, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("============>文件上传");
		JSONObject result = new JSONObject();

		try {

			StringBuffer sb = new StringBuffer();
			if (null != file && file.length > 0) {
				for (int i = 0; i < file.length; i++) {

					MultipartFile f = file[i];
					String filename = f.getOriginalFilename();

					if (!"".equals(filename.trim())) {
						File newFile = new File(filename);
						FileOutputStream os = new FileOutputStream(newFile);
						os.write(f.getBytes());
						os.close();
						f.transferTo(newFile);
						// 上传到OSS
						String uploadUrl = AliyunOSSUtil.upload(newFile);
						sb.append(uploadUrl).append(",");
						// if (newFile.exists() && newFile.isFile()) {
						// newFile.delete();
						// }
					}
				}

				//String[] result1 = sb.substring(0, sb.length() - 1).split(",");

				result.put("code", 1);
				result.put("message", "成功");
				result.put("data", sb.substring(0, sb.length() - 1).split(","));

				// return result.toString();
				return result.toString();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		result.put("code", -1);
		result.put("message", "失败");
		result.put("data", "");
		return result.toString();
	}

}
