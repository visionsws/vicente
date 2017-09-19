package com.fusio.tag.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fusio.tag.commons.busi.PrimaryKeyGenerator;
import com.fusio.tag.commons.prop.ConfigProperties;
import com.fusio.tag.commons.utils.DateUtil;
import com.fusio.tag.commons.utils.UploadHelper;
import com.fusio.tag.service.ImportByUploadServiceI;

@Service
@Transactional
public class ImportByUploadService extends BaseService<ImportByUploadService>implements ImportByUploadServiceI {

	@Override
	public void importByUpload(MultipartFile file) {
		// 检查参数
		if (file == null || file.getSize() == 0) {
			this.validParamUtil.throwInvalidParamEx("未上传文件");
		}
		
		String filename = file.getOriginalFilename();
		String extension = UploadHelper.getFileExtLowercaseNoDot(filename);// 扩展名,小写
		if (!"txt".equals(extension)) {
			this.validParamUtil.throwInvalidParamEx("上传文件必须是txt");
		}

		int fileSize = 0;
		try {
			fileSize = file.getBytes().length;
		} catch (IOException e) {
			fileSize = 0;
		}
		
		String tmpFilePath = ConfigProperties.get("upload_file_path");
		File path = new File(tmpFilePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		
		//String fullFilename = tmpFilePath + "/" + filename + 
		
		
//		String
		
//		file.transferTo(new File(filenameWithPath));
		
		
		
	}

}