package com.blog.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	/**
	 * 上传文件
	 * @param host 主机地址
	 * @param port 端口
	 * @param username	登录用户名
	 * @param password	密码
	 * @param basePath	基础目录
	 * @param filePath  上传目录
	 * @param fileName  上传文件名
	 * @param inputStream 文件流
	 * @return
	 */
	public static boolean uploadFile(String FTP_ADDRESS,int FTP_PORT,String FTP_USERNAME,String FTP_PASSWORD ,String FTP_BASE_PATH,
			String filePath,String fileName,InputStream inputStream){
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(FTP_ADDRESS,FTP_PORT); //连接服务器
			ftp.login(FTP_USERNAME, FTP_PASSWORD); //登录服务器
			//测试是否连接成功
			int relay = ftp.getReplyCode(); 
			if(!FTPReply.isPositiveCompletion(relay)){
				ftp.disconnect();
				System.out.println("one");
				return false;
			}
			//判断文件夹是否存在，如果存在，切换ftp目录，如果不存在，创建文件夹
			if(!ftp.changeWorkingDirectory(FTP_BASE_PATH + filePath)){
				String tempPath = FTP_BASE_PATH;
				String dirs[] = filePath.split("/");
				for(int i = 0;i < dirs.length;i++){
					if(dirs[i] == null || "".equals(dirs[i])) continue;
					tempPath += "/" + dirs[i];
					if(!ftp.changeWorkingDirectory(tempPath)){
						if(!ftp.makeDirectory(tempPath)){
							System.out.println("two");
							return false;
						}else{
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置为二进制流，否则会出现图片混乱
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			if(!ftp.storeFile(fileName, inputStream)){
				return false;
			}
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(ftp.isConnected()){
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = new FileInputStream(new File("F:/2a.jpg"));
		boolean flag = uploadFile("192.168.56.130", 21, "ftpuser", "lixu", "/home/ftpuser", "/image", "2a.jpg", in);
		System.out.println(flag);
	}
}
