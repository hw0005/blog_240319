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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {
	//학원 컴퓨터 이미지 저장 경로
	//public static final String FILE_UPLOAD_PATH = "D:\\윤현우\\7_PROJECT\\blog\\BLOG_WORKSPACE\\images/";
	
	//내 노트북 이미지 저장 경로
	public static final String FILE_UPLOAD_PATH ="D:\\윤현우\\7_PROJECT\\BLOG\\workspace\\image/";
	//public static final String FILE_UPLOAD_PATH ="/home/ec2-user/images/"; // 리눅스 경로
	
	// 글 업로드
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
		
		
		// 이미지업로드
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
	
	// 글 삭제
	public void deleteFile(String imageUrl) {
		
			Path path = Paths.get(FILE_UPLOAD_PATH + imageUrl.replace("/images/", ""));
			
			// 이미지 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.info("[FileManagerService 파일삭제] 삭제실패. path:{}", path.toString());
				return;
			}
			
			// 폴더(디렉토리) 삭제
			path = path.getParent();
			if (Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.info("[FileManagerService 파일삭제] 디렉토리 삭제 실패. path:{}", path.toString());
				}
			}
			
		}
	
}
