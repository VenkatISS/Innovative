package com.venkat.jobs;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class ZipExtraction {
	
	

	/*static String destDir = "C:/ilpguploads/upload_images/hpcl/inspection/source/target";
	static Path archive = Paths.get("C:/ilpguploads/upload_images/hpcl/inspection/source/archive");
	static Path zipFilePath = Paths.get("C:/ilpguploads/upload_images/hpcl/inspection/source");*/
	static String  userHome = System.getProperty("user.home");
	
	//static String destDir = "C:\\ilpguploads\\IOCL\\insp\\target";
	//static Path archive = Paths.get("C:\\ilpguploads\\IOCL\\insp\\archive");
	/*static String destDir = "C:\\ilpguploads\\upload_images\\hpcl\\inspection\\target";
	static Path archive = Paths.get("C:\\ilpguploads\\upload_images\\hpcl\\inspection\\archive");
	static Path zipFilePath = Paths.get("C:\\ilpguploads\\upload_images\\hpcl\\inspection\\source");
	*/

	@Scheduled(cron = "0 0/1 * * * ?")
	public void execute() throws IOException {
		 String  zipFilePath = userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"inspection"+File.separator+"source";
		
		 String destDir =userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"inspection"+File.separator+"target";
		File destDirpath=new File(destDir);
		destDirpath.mkdir();
		 String archive =userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"inspection"+File.separator+"archive";      
			File archiveDirpath=new File(archive);
			archiveDirpath.mkdir();

		File file = new File(zipFilePath);
		
   System.out.println("HOME:"+System.getProperty("user.home"));
   System.out.println("DIR:"+System.getProperty("user.dir"));

System.out.println("OS here is:"+System.getProperty("os.name")); 
System.out.println("OS Home here is:"+System.getenv("HOME"));

		File[] files = file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(".zip")) {
					return true;
				} else {
					return false;
				}
			} 
		});
		for (File f : files) {

			unzip(f.getPath(), destDirpath.toString());

			move(f.getPath(), archiveDirpath.toString() +File.separator);
		}
	}

	public static boolean unzip(String zipFilePath, String destDir) {
		boolean un = false;

		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration<?> enu = zipFile.entries();
			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

				String name = zipEntry.getName();
				//String name =zipEntry

				File file = new File(destDir +"/" + name);
				if (name.endsWith("/")) {
					file.mkdirs();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();
				un = true;

			}
			zipFile.close();
		} catch (IOException e) {
			un = false;
			e.printStackTrace();
		} finally {
			return un;
		}

	}

	private static void move(String zipFilePath, String archive) {

		try {

			File sourceFile = new File(zipFilePath);
			File archiveFile = new File(archive);

			InputStream inStream = new FileInputStream(sourceFile);
			OutputStream outStream = new FileOutputStream(archiveFile + "\\" + sourceFile.getName());
			System.out.println("");
			byte[] buffer = new byte[1024];
			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			sourceFile.delete();

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ZipExtraction.class);

	}*/
	
	// delivery
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void executeDelivery() throws IOException {
		 String  zipFilePath = userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"delivery"+File.separator+"source";
		
		 String destDir =userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"delivery"+File.separator+"target";
		File destDirpath=new File(destDir);
		destDirpath.mkdir();
		 String archive =userHome+File.separator+"ilpguploads"+File.separator+"upload_images" +File.separator+"hpcl"+File.separator+"delivery"+File.separator+"archive";      
			File archiveDirpath=new File(archive);
			archiveDirpath.mkdir();

		File file = new File(zipFilePath);
		
   System.out.println("HOME:"+System.getProperty("user.home"));
   System.out.println("DIR:"+System.getProperty("user.dir"));

System.out.println("OS here is:"+System.getProperty("os.name")); 
System.out.println("OS Home here is:"+System.getenv("HOME"));

		File[] files = file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(".zip")) {
					return true;
				} else {
					return false;
				}
			} 
		});
		for (File f : files) {

			unzip(f.getPath(), destDirpath.toString());

			move(f.getPath(), archiveDirpath.toString() +File.separator);
		}
	}

	public static boolean unzipDelivery(String zipFilePath, String destDir) {
		boolean un = false;

		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration<?> enu = zipFile.entries();
			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

				String name = zipEntry.getName();
				//String name =zipEntry

				File file = new File(destDir +"/" + name);
				if (name.endsWith("/")) {
					file.mkdirs();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();
				un = true;

			}
			zipFile.close();
		} catch (IOException e) {
			un = false;
			e.printStackTrace();
		} finally {
			return un;
		}

	}

	private static void moveDelivery(String zipFilePath, String archive) {

		try {

			File sourceFile = new File(zipFilePath);
			File archiveFile = new File(archive);

			InputStream inStream = new FileInputStream(sourceFile);
			OutputStream outStream = new FileOutputStream(archiveFile + "\\" + sourceFile.getName());
			System.out.println("");
			byte[] buffer = new byte[1024];
			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			sourceFile.delete();

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
