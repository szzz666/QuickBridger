package top.szzz666.quickBridger.tools;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtil {
    public static void loadRecourseFromJarByFolder(String folderPath, String targetFolderPath, Class<?> clazz) throws IOException {
        URL url = clazz.getResource(folderPath);
        if (url == null) {
            throw new FileNotFoundException("文件夹 " + folderPath + " 在 JAR 中未找到。");
        }

        URLConnection urlConnection = url.openConnection();
        if (urlConnection instanceof JarURLConnection) {
            copyJarResources((JarURLConnection) urlConnection, folderPath, targetFolderPath, clazz);
        } else {
            copyFileResources(url, folderPath, targetFolderPath, clazz);
        }
    }

    private static void copyFileResources(URL url, String folderPath, String targetFolderPath, Class<?> clazz) throws IOException {
        File root = new File(url.getPath());
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        loadRecourseFromJarByFolder(folderPath + "/" + file.getName(), targetFolderPath, clazz);
                    } else {
                        loadRecourseFromJar(folderPath + "/" + file.getName(), targetFolderPath, clazz);
                    }
                }
            }
        }
    }

    private static void copyJarResources(JarURLConnection jarURLConnection, String folderPath, String targetFolderPath, Class<?> clazz) throws IOException {
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> entrys = jarFile.entries();
        while (entrys.hasMoreElements()) {
            JarEntry entry = entrys.nextElement();
            if (entry.getName().startsWith(jarURLConnection.getEntryName()) && !entry.getName().endsWith("/")) {
                loadRecourseFromJar("/" + entry.getName(), targetFolderPath, clazz);
            }
        }
        jarFile.close();
    }

    public static void loadRecourseFromJar(String path, String recourseFolder, Class<?> clazz) throws IOException {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("路径必须是绝对路径（以 '/' 开头）。");
        }

        if (path.endsWith("/")) {
            throw new IllegalArgumentException("路径不能以 '/' 结尾。");
        }

        int index = path.lastIndexOf('/');
        String filename = path.substring(index + 1);
        String folderPath = recourseFolder + path.substring(0, index + 1);

        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        filename = folderPath + filename;
        File file = new File(filename);

        if (!file.exists() && !file.createNewFile()) {
            System.err.println("创建文件：" + filename + " 失败");
            return;
        }

        byte[] buffer = new byte[1024];
        int readBytes;

        URL url = clazz.getResource(path);
        if (url == null) {
            throw new FileNotFoundException("文件 " + path + " 在 JAR 中未找到。");
        }

        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        OutputStream os = new FileOutputStream(file);
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            os.close();
            is.close();
        }
    }
}