package sample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:TODO
 * Create Time:2020/7/31 0031 下午 4:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FileUtil {
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    /**
     * 文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    /**
     * 复制文件
     *
     * @param inputFile
     * @param outputFile
     * @throws FileNotFoundException
     */
    public static void copyFile(String inputFile, String outputFile) {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(sFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int temp = 0;
        try {
            while ((temp = fis.read()) != -1) {
                fos.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建目录
     *
     * @param dir
     * @return
     */
    public static boolean createDir(String dir) {
        try {
            boolean flag = true;
            File file = new File(dir.toString());
            if (!file.exists()) {
                flag = file.mkdir();
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 删除文件
     *
     * @param strFilePath
     * @return
     */
    public static boolean removeFile(String strFilePath) {
        boolean result = true;
        if (strFilePath == null || "".equals(strFilePath)) {
            return result;
        }
        File file = new File(strFilePath);
        if (file.isFile() && file.exists()) {
            result = file.delete();
        }
        return result;
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+ File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
