package com.fusio.tag.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportByUploadServiceI {
	void importByUpload(MultipartFile file);
}