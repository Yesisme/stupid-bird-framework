package com.lym.spring.framework.core.io.support;

import com.lym.spring.framework.core.io.FileSystemResource;
import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.utils.ClassUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class PackageSourceLoader {

	private static final Log logger = LogFactory.getLog(PackageSourceLoader.class);

	private final ClassLoader classloder;
	
	public PackageSourceLoader() {
		this.classloder = ClassUtil.getDefaultClassLoader();
	}

	public ClassLoader getClassloder() {
		return this.classloder;
	}

	public Resource[] getResource(String path) {
		String basePackage = ClassUtil.convertToPackageReource(path);

		ClassLoader classLoader = getClassloder();

		URL url = classLoader.getResource(basePackage);

		File fileDir = new File(url.getFile());

		Set<File> matchFiles = retrieveMatchFile(fileDir);

		Resource[] result = new Resource[matchFiles.size()];

		int i =0;
		for (File file:matchFiles) {
			result[i] = new FileSystemResource(file);
			i++;
		}
		return result;
	}


	public Set<File> retrieveMatchFile(File rootFile){

		Set<File> setFile = new LinkedHashSet<>();

		doRetrieveMatchingFiles(rootFile,setFile);

		return setFile;
	}

	public void doRetrieveMatchingFiles(File dir,Set<File> setFile){

		File[] files = dir.listFiles();

		for (File file:files){
			if(file.isDirectory()){
				if(!file.canRead()){
					logger.error(file +"cannot be reader");
				}
				doRetrieveMatchingFiles(file,setFile);
			}else {
				setFile.add(file);
			}
		}
	}
}
