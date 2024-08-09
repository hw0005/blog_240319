package com.blog.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.blog.post.domain.PostImage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {
	public static final String FILE_UPLOAD_PATH = "D:\\윤현우\\7_PROJECT\\blog\\BLOG_WORKSPACE\\images/";
	
	public List<String> uploadFileList(List<MultipartFile> file, String userLoginId) {
		// 폴더 생성(directory)
		// 예: aaaa_24XXXX/XXX.jpg
		String directoryName = userLoginId + "_" + System.currentTimeMillis(); 
		
		// D:\\윤현우\\7_PROJECT\\blog\\BLOG_WORKSPACE\\images/aaaa_24XXXX
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 폴더 생성시 실패하면 경로를 null로 리턴
			return null;
		}
		
		
		// 파일업로드
		List<String> image = new ArrayList<>();
		for (int i = 0; i < file.size(); i++) {
			try {
				byte[] bytes = file.get(i).getBytes();
				// 한글명으로 업로드된 파일 영문자로 바꾸기
				Path path = Paths.get(filePath + file.get(i).getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			image.add("/images/" + directoryName + "/" + file.get(i).getOriginalFilename());
		}
		return image;
		
	}
	
}
