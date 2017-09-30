package com.wh.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {

    public static void main(String[] args) {
        System.out.println(FileUtil.getFileDirectory("c:\\23123\\23124214.txt"));
        System.out.println(FileUtil.rename("c:\\23123\\yangkai.txt", UUIDUtil.getUUID()));
    }

    /**
     * 功能描述：删除单个文件<BR>
     */
    public static void deleteFile(File file) {
        file.delete();
    }

    public static String getFileExtendName(String path) {
        return StringUtils.right(path, path.length() - path.lastIndexOf(".") - 1);
    }

    /**
     * 文件重命名
     * 
     * @param oldName 旧文件名称，包含扩展名
     * @param newName 新文件名称，不需要包含扩展名
     */
    public static String rename(String oldName, String newName) {
        String extendName = getFileExtendName(oldName);
        if (StringUtils.isNotBlank(extendName)) {
            return newName + "." + extendName;
        }
        return newName;
    }

    /**
     * 带有路径的文件重命名
     * 
     * @param filePath 文件路径
     * @param newName 新文件名称，不需要扩展名
     */
    public static String renameWithPath(String filePath, String newName) {
        String extendName = getFileExtendName(filePath);
        if (StringUtils.isNotBlank(extendName)) {
            return getFileDirectory(filePath) + File.separator + newName + "." + extendName;
        }
        return getFileDirectory(filePath) + File.separator + newName;
    }

    public static String getFileDirectory(String path) {
        return new File(path).getParent();
    }

    /**
     * 功能描述：删除目录，并删除该目录下的所有文件<BR>
     * 
     */
    public static boolean deleteDirectory(File dir) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new RuntimeException("要删除的目录不存在，或者不是目录");
        }

        File[] files = dir.listFiles();
        int sz = files.length;

        for (int i = 0; i < sz; i++) {
            if (files[i].isDirectory()) {
                if (!deleteDirectory(files[i])) {
                    return false;
                }
            } else {
                if (!files[i].delete()) {
                    return false;
                }
            }
        }

        if (!dir.delete()) {
            return false;
        }
        return true;
    }

    /**
     * description: Copy file<br>
     * 
     */
    public static void copyFile(String src, String target)
        throws IOException {
        FileInputStream in = new FileInputStream(src);
        File file = new File(target);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        FileOutputStream out = new FileOutputStream(file);
        int c;
        byte buffer[] = new byte[1024];
        while ((c = in.read(buffer)) != -1) {
            for (int i = 0; i < c; i++)
                out.write(buffer[i]);
        }
        in.close();
        out.close();
    }

    /*
     * private static BufferedImage resize(BufferedImage source, int targetW, int targetH) { // targetW，targetH分别表示目标长和宽
     * int type = source.getType(); BufferedImage target = null; double sx = (double) targetW / source.getWidth();
     * double sy = (double) targetH / source.getHeight(); // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放 // 则将下面的if
     * else语句注释即可 // if (sx > sy) { // sx = sy; // targetW = (int) (sx * source.getWidth()); // } else { // sy = sx; //
     * targetH = (int) (sy * source.getHeight()); // } if (type == BufferedImage.TYPE_CUSTOM) { // handmade ColorModel
     * cm = source.getColorModel(); WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH); boolean
     * alphaPremultiplied = cm.isAlphaPremultiplied(); target = new BufferedImage(cm, raster, alphaPremultiplied, null);
     * } else target = new BufferedImage(targetW, targetH, type); Graphics2D g = target.createGraphics(); // smoother
     * than exlax: g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
     * g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy)); g.dispose(); return target; }
     */
    /**
     * 功能描述：生成缩略图,并保留原图，新图片以：原图片名_宽_高.图片格式 保存<BR>
     * 
     * @param fromFile 原file
     */
    public static String resizeImg(File fromFile, int width, int height) {
        return resizeImgForEqual(fromFile, width, height, false);
    }

    public static String resizeImgForEqual(File fromFile, int width, int height, boolean equal) {
        String newFileName = null;
        try {
            String name = fromFile.getName();
            String imgType = name.substring(name.lastIndexOf(".") + 1).toLowerCase();

            String path = fromFile.getPath();
            String target = path.substring(0, path.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
            new ScaleImage().saveImageAsJpg(fromFile.getPath(), target, width, height, equal);
            newFileName = name.substring(0, name.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
        } catch (Exception e) {
            throw new RuntimeException("生成缩略图时发生错误！");
        }
        return newFileName;
    }

    /**
     * 功能描述：强制生成的缩略图的长宽等与提供的长宽<BR>
     * 
     * @param fromFile
     * @param width
     */
    public static void resizeForEqual(File fromFile, int width, int height, boolean equal) {
        try {
            new ScaleImage().saveImageAsJpg(fromFile.getPath(), fromFile.getPath(), width, height, equal);
        } catch (Exception e) {
            throw new RuntimeException("生成缩略图时发生错误！");
        }
    }

    /**
     * 功能描述：强制生成的缩略图的长宽等与提供的长宽 新图片以：原图片名_宽_高.图片格式 保留原图<BR>
     * 
     * @param fromFile
     */
    public static String resizeForEqualJpegImg(File fromFile, int width, int height, boolean equal) {
        String newFileName = null;
        try {
            String name = fromFile.getName();
            String imgType = name.substring(name.lastIndexOf(".") + 1).toLowerCase();

            String path = fromFile.getPath();
            String target = path.substring(0, path.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
            new ScaleImage().saveImageAsJpg(fromFile.getPath(), target, width, height, equal);
            newFileName = name.substring(0, name.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
        } catch (Exception e) {
            throw new RuntimeException("生成缩略图时发生错误！");
        }
        return newFileName;
    }
    
    
    
    
    /**
     * 功能描述：强制生成的缩略图的长宽等与提供的长宽 新图片以：原图片名_宽_高.图片格式 保留原图<BR>
     * 
     * @param fromFile
     * @param width
     */
    public static String resizeForEqualImg(File fromFile, int width, int height, boolean equal) {
        String newFileName = null;
        try {
            String name = fromFile.getName();
            String imgType = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
            String path = fromFile.getPath();
            String target = path.substring(0, path.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
            new ScaleImage().saveImage(fromFile.getPath(), target, width, height, equal,imgType);
            newFileName = name.substring(0, name.lastIndexOf(".")) + "_" + width + "_" + height + "." + imgType;
        } catch (Exception e) {
            throw new RuntimeException("生成缩略图时发生错误！");
        }
        return newFileName;
    }
    
    
    

    /**
     * 功能描述：生成缩略图，用生成后的缩略图替代原图<BR>
     * 
     * @param fromFile 原file
     * @param width
     */
    public static void resize(File fromFile, int width, int height) {
        resizeForEqual(fromFile, width, height, false);
    }

    /**
     * 功能描述：根据传入的路径，取得该路径的缩略图<BR>
     * 
     * @param path
     * @param width
     */
    public static String getImg(String path, int width, int height) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        String[] splitPath = path.split("\\.");
        String result = splitPath[0] + "_" + width + "_" + height + "." + splitPath[1].toLowerCase();
        return result;
    }

    /**
     * 功能描述：读取文件内容<BR>
     * 
     * @param file
     */
    @SuppressWarnings("unused")
    public static String file2String(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            } else {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            // 将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        // 返回转换结果
        if (writer != null)
            return writer.toString();
        else
            return null;
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public static void uploadFileWithFullPath(HttpServletRequest request, String fullPath, String[] allowTypes) {
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath(fullPath);
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;
        // 最大文件大小
        long maxSize = 1000000;

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("没有可供上传的文件");
        }
        // 检查目录
        File uploadDir = new File(savePath).getParentFile();
        if (!uploadDir.isDirectory()) {
            throw new RuntimeException("目录不存在");
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            throw new RuntimeException("目录没有写权限");
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator itr = items.iterator();
        String newFileName = null;
        while (itr.hasNext()) {
            FileItem item = (FileItem)itr.next();
            String fileName = item.getName();
            long fileSize = item.getSize();
            if (!item.isFormField()) {
                // 检查文件大小
                if (item.getSize() > maxSize) {
                    throw new RuntimeException("上传文件超过最大限制，最大100M");
                }
                if (fileTypes != null) {
                    // 检查扩展名
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!Arrays.<String> asList(fileTypes).contains(fileExt.toLowerCase())) {
                        throw new RuntimeException("文件类型不允许上传");
                    }
                }
                try {
                    File uploadedFile = new File(savePath);
                    item.write(uploadedFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("文件上传失败");
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public static String uploadFile(HttpServletRequest request, String path, String[] allowTypes) {
        // 文件保存目录路径
       // String savePath = request.getSession().getServletContext().getRealPath("/") + path + "/";
        String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;
        // 最大文件大小
        long maxSize = 1000000;

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("没有可供上传的文件");
        }
        // 检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.isDirectory()) {
            throw new RuntimeException("目录不存在");
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            throw new RuntimeException("目录没有写权限");
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator itr = items.iterator();
        String newFileName = null;
        while (itr.hasNext()) {
            FileItem item = (FileItem)itr.next();
            String fileName = item.getName();
            long fileSize = item.getSize();
            if (!item.isFormField()) {
                // 检查文件大小
                if (item.getSize() > maxSize) {
                    throw new RuntimeException("上传文件超过最大限制，最大100M");
                }
                // 检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (!Arrays.<String> asList(fileTypes).contains(fileExt.toLowerCase())) {
                    throw new RuntimeException("文件类型不允许上传");
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try {
                    File uploadedFile = new File(savePath, newFileName);
                    item.write(uploadedFile);
                } catch (Exception e) {
                    throw new RuntimeException("文件上传失败");
                }
            }
        }
        return newFileName;
    }

    /**
     * SpringMVC 单文件上传
     * 
     * @param request HttpServletRequest对象 <br/>
     * @param formFieldName 表单中file组件的name，如：<input type="file" name="xxx" /> <br/>
     * @param path 服务器端保存的路径，相对于ROOT的路径如：/userfile/images/ <br/>
     * @param allowTypes 运行上传的文件类型，数组，请用小写标识 <br/>
     * @return 文件路径 /userfile/images/20130912134523_134.png
     */
    public static String uploadFile4SpringMVC(HttpServletRequest request, String formFieldName, String path, String[] allowTypes)
        throws MyFileUploadException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;

        MultipartFile multipartFile = multipartRequest.getFile(formFieldName);

        return _uploadFile(path, savePath, fileTypes, multipartFile,100);
    }

    /**
     * SpringMVC 批量文件上传
     * 
     * @param request HttpServletRequest对象 <br/>
     * @param formFieldName 表单中file组件的name，如：<input type="file" name="xxx" /> <br/>
     * @param path 服务器端保存的路径，相对于ROOT的路径如：/userfile/images/ <br/>
     * @param allowTypes 运行上传的文件类型，数组，请用小写标识 <br/>
     * @return 文件路径 <fieldName,serverFieldPath> /userfile/images/20130912134523_134. png
     */
    public static Map<String, String> uploadFile4SpringMVC(HttpServletRequest request, String path, String[] allowTypes)
        throws MyFileUploadException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;

        Iterator<String> fileNameIterator = multipartRequest.getFileNames();
        Map<String, String> map = new HashMap<String, String>();
        for (Iterator<String> iterator = fileNameIterator; iterator.hasNext();) {
            String name = iterator.next();
            MultipartFile multipartFile = multipartRequest.getFile(name);
            String serverPath = _uploadFile(path, savePath, fileTypes, multipartFile,100);
            if (StringUtils.isNotBlank(serverPath)) {
                map.put(name, serverPath);
            }
        }

        return map;
    }

    /**
     * SpringMVC 批量文件上传
     * 
     * @param request HttpServletRequest对象 <br/>
     * @param formFieldName(单个input上传多个图片) 表单中file组件的name，如：<input type="file" name="xxx" /> <br/>
     * @param path 服务器端保存的路径，相对于ROOT的路径如：/userfile/images/ <br/>
     * @param allowTypes 运行上传的文件类型，数组，请用小写标识 <br/>
     * @return 文件路径 <fieldName,serverFieldPath> /userfile/images/20130912134523_134. png
     */
    public static List<String> uploadFiles4SpringMVC(HttpServletRequest request, String path, String[] allowTypes)
        throws MyFileUploadException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;
        Iterator<String> fileNameIterator = multipartRequest.getFileNames();
        List<String> list = new ArrayList<String>();
        for (Iterator<String> iterator = fileNameIterator; iterator.hasNext();) {
            String name = iterator.next();
            List<MultipartFile> mList = multipartRequest.getFiles(name);
            for (MultipartFile multipartFile : mList) {
                String serverPath = _uploadFile(path, savePath, fileTypes, multipartFile,100);
                if (StringUtils.isNotBlank(serverPath)) {
                    list.add(serverPath);
                }
            }
        }
        return list;
    }
    
    /**
     * 
     * @Title: uploadFiles4SpringMVC 
     * @Description: 批量文件上传
     * @author wd
     * @Date 2016年11月28日  上午9:57:23 
     * @param request HttpServletRequest对象
     * @param path 服务器端保存的路径
     * @param allowTypes 允许上传的文件类型，数组，请用小写标识
     * @param allowSize 图片大小限制，最大值(兆)
     * @return
     * @throws MyFileUploadException
     * @return List<String>    返回类型
     */
    public static List<String> uploadFiles4SpringMVC(HttpServletRequest request, String path, String[] allowTypes,int allowSize)
	  throws MyFileUploadException {
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		  // 文件保存目录路径
		  String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
		  // 定义允许上传的文件扩展名
		  String[] fileTypes = allowTypes;
		  Iterator<String> fileNameIterator = multipartRequest.getFileNames();
		  List<String> list = new ArrayList<String>();
		  for (Iterator<String> iterator = fileNameIterator; iterator.hasNext();) {
		      String name = iterator.next();
		      List<MultipartFile> mList = multipartRequest.getFiles(name);
		      for (MultipartFile multipartFile : mList) {
		          String serverPath = _uploadFile(path, savePath, fileTypes, multipartFile,allowSize);
		          if (StringUtils.isNotBlank(serverPath)) {
		              list.add(serverPath);
		          }
		      }
		  }
		  return list;
	}
    
    
    /**
     * 校园任务图片上传（上传一份原图一份压缩之后的图片）
     * @Title: uploadFiles4SpringMVC 
     * @Description: 批量文件上传
     * @author wd
     * @Date 2016年11月28日  上午9:57:23 
     * @param request HttpServletRequest对象
     * @param path 服务器端保存的路径
     * @param allowTypes 允许上传的文件类型，数组，请用小写标识
     * @param allowSize 图片大小限制，最大值(兆)
     * @return
     * @throws MyFileUploadException
     * @return List<String>    返回类型
     */
    public static List<String> uploadFiles4SpringMVCBigAndSmall(HttpServletRequest request, String path, String[] allowTypes,int allowSize)
	  throws MyFileUploadException {
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		  // 文件保存目录路径
		  String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
		  // 定义允许上传的文件扩展名
		  String[] fileTypes = allowTypes;
		  Iterator<String> fileNameIterator = multipartRequest.getFileNames();
		  List<String> list = new ArrayList<String>();
		  for (Iterator<String> iterator = fileNameIterator; iterator.hasNext();) {
		      String name = iterator.next();
		      List<MultipartFile> mList = multipartRequest.getFiles(name);
		      for (MultipartFile multipartFile : mList) {
		          String serverPath = _uploadFiles(path, savePath, fileTypes, multipartFile,allowSize);
		          if (StringUtils.isNotBlank(serverPath)) {
		              list.add(serverPath);
		          }
		      }
		  }
		  return list;
	}
    
    /**
     * SpringMVC 批量文件上传
     * 
     * @param request HttpServletRequest对象 <br/>
     * @param path 服务器端保存的路径，相对于ROOT的路径如：/userfile/images/ <br/>
     * @param allowTypes 运行上传的文件类型，数组，请用小写标识 <br/>
     * @return Map<文件名称,文件保存路径>
     */
    public static List<FileInfo> uploadFiles(HttpServletRequest request, String path, String[] allowTypes)
        throws MyFileUploadException {
        List<FileInfo> infos = new ArrayList<FileInfo>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath(path) + File.separator;
        // 定义允许上传的文件扩展名
        String[] fileTypes = allowTypes;
        Iterator<String> fileNameIterator = multipartRequest.getFileNames();
        // Map<String, String> map = new HashMap<String, String>();
        for (Iterator<String> iterator = fileNameIterator; iterator.hasNext();) {
            String name = iterator.next();
            List<MultipartFile> multipartFiles = multipartRequest.getFiles(name);
            for (MultipartFile multipartFile : multipartFiles) {
                FileInfo info = new FileInfo();
                String fileName = multipartFile.getOriginalFilename();
                String serverPath = _uploadFile(path, savePath, fileTypes, multipartFile,100);
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                info.setFieldName(name);
                info.setExtName(fileExt);
                info.setFileSize(String.valueOf(multipartFile.getSize()));
                info.setOriginalFilename(fileName);
                if (StringUtils.isNotBlank(serverPath)) {
                    info.setFilePath(serverPath);
                }
                infos.add(info);
            }
        }
        return infos;
    }

    public static class FileInfo {
        private String fieldName;

        private String originalFilename;

        private String extName;

        private String fileSize;

        private String filePath;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getOriginalFilename() {
            return originalFilename;
        }

        public void setOriginalFilename(String originalFilename) {
            this.originalFilename = originalFilename;
        }

        public String getExtName() {
            return extName;
        }

        public void setExtName(String extName) {
            this.extName = extName;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }
    }

    private static String _uploadFile(String path, String savePath, String[] fileTypes, MultipartFile multipartFile,int allowSize)
        throws MyFileUploadException {
        // 最大文件大小100M,1M=1024K=1024B*1024
    	long maxSize = 1024 * 1024 * 100;
    	if(allowSize != 0){
    		maxSize = 1024 * 1024 * allowSize;
    	}

        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String fileName = multipartFile.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (fileTypes != null) {
            // 检查扩展名
            if (!Arrays.<String> asList(fileTypes).contains(fileExt.toLowerCase())) {
                throw new MyFileUploadException("文件类型:" + fileExt + " 不允许上传，只允许格式：" + Arrays.<String> asList(fileTypes).toString());
            }
        }
        if (maxSize < multipartFile.getSize()) {
            throw new MyFileUploadException("文件超出最大文件上传限制:"+allowSize+"M，实际上传文件：" + multipartFile.getSize() / 1024 / 1024 + "M");
        }

        // 检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            throw new MyFileUploadException("目录没有写权限");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        //
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(10000) + "." + fileExt;

        String newFilePath = savePath + newFileName;
        try {
            FileUtils.writeByteArrayToFile(new File(newFilePath), multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyFileUploadException("上传文件失败：" + e.getMessage());
        }

        if (path.endsWith("/")) {
            return path + newFileName;
        } else {
            return path + "/" + newFileName;
        }
    }
    
    
    private static String _uploadFiles(String path, String savePath, String[] fileTypes, MultipartFile multipartFile,int allowSize)
            throws MyFileUploadException {
            // 最大文件大小100M,1M=1024K=1024B*1024
        	long maxSize = 1024 * 1024 * 100;
        	if(allowSize != 0){
        		maxSize = 1024 * 1024 * allowSize;
        	}

            if (multipartFile == null || multipartFile.isEmpty()) {
                return null;
            }

            String fileName = multipartFile.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (fileTypes != null) {
                // 检查扩展名
                if (!Arrays.<String> asList(fileTypes).contains(fileExt.toLowerCase())) {
                    throw new MyFileUploadException("文件类型:" + fileExt + " 不允许上传，只允许格式：" + Arrays.<String> asList(fileTypes).toString());
                }
            }
            if (maxSize < multipartFile.getSize()) {
                throw new MyFileUploadException("文件超出最大文件上传限制:"+allowSize+"M，实际上传文件：" + multipartFile.getSize() / 1024 / 1024 + "M");
            }

            // 检查目录
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 检查目录写权限
            if (!uploadDir.canWrite()) {
                throw new MyFileUploadException("目录没有写权限");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            //
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(10000) + "." + fileExt;
            String nfName = newFileName.replace(".", ",");
            String[] newFileNames = nfName.split(",");
            String newFileName1 = newFileNames[0]+"s."+newFileNames[1];
            String newFilePath = savePath + newFileName;
            String newFilePath1 = savePath + newFileName1;
            
            try {
                FileUtils.writeByteArrayToFile(new File(newFilePath), multipartFile.getBytes());
                FileUtil.zipWidthHeightImageFile(new File(newFilePath), new File(newFilePath1), 60, 60, 0);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyFileUploadException("上传文件失败：" + e.getMessage());
            }
            
            String originalImagePath = "";
            String thumbnail ="";
            if (path.endsWith("/")) {
            	originalImagePath = path + newFileName;
            	thumbnail = path + newFileName1;
            } else {
            	originalImagePath = path + "/" + newFileName;
            	thumbnail = path + newFileName1;
            }
            
            return originalImagePath+","+thumbnail;
        }

    /**
     * 按设置的宽度高度压缩图片文件<br> 先保存原文件，再压缩、上传
     * @param oldFile  要进行压缩的文件全路径
     * @param newFile  新文件
     * @param width  宽度
     * @param height 高度
     * @param quality 质量
     * @return 返回压缩后的文件的全路径
     */
    public static String zipWidthHeightImageFile(File oldFile,File newFile, int width, int height,float quality) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);

            String srcImgPath = newFile.getAbsoluteFile().toString();
            System.out.println(srcImgPath);
            String subfix = "jpg";
            subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());

            BufferedImage buffImg = null;
            if(subfix.equals("png")){
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            }else{
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }

            Graphics2D graphics = buffImg.createGraphics();
            graphics.setBackground(new Color(255,255,255));
            graphics.setColor(new Color(255,255,255));
            graphics.fillRect(0, 0, width, height);
            graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(buffImg, subfix, new File(srcImgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }
}
