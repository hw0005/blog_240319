package com.blog.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	 // 입력 문자열을 SHA-256으로 암호화하여 해시 값을 반환하는 메서드
    public static String encrypt(String message) {
	    // SHA-256 알고리즘의 인스턴스 생성
    	StringBuilder builder = new StringBuilder();
    	
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 입력 문자열의 바이트 배열을 업데이트하여 해시 값을 계산
			byte[] bytes = message.getBytes();
			md.update(bytes); 
			byte[] digest = md.digest();
			
			for (byte b : bytes) {
				// 바이트 값을 16진수 문자열로 변환하여 StringBuilder에 추가
				builder.append(String.format("%02x", b)); 
			}
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return builder.toString(); 
    }
    
}
