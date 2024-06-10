package top.szzz666.quickBridger.tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import static top.szzz666.quickBridger.QuickBridgerMian.nkConsole;
import static top.szzz666.quickBridger.QuickBridgerMian.nkServer;

public class Utils {
    /**
     * 复制文件，将File文件复制到另一个文件路径上
     * 不会覆盖文件
     * @param old 复制的文件
     * @param target 复制到的位置
     * @return 是否复制成功
     * */
    public static boolean copyFiles(File old, File target){

        int load = 1;
        File[] files = old.listFiles();
        if(files != null){
            for (File value : files) {
                nkConsole("Coping File ... "+((load / (float)files.length) * 100) +"%",false);
                load++;
                if (value.isFile()) {
                    // 复制文件
                    try {
                        File file1 = new File(target +File.separator + value.getName());
                        if(!file1.exists()){
                            try{
                                file1.createNewFile();
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        copyFile(value, file1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (value.isDirectory()) {
                    // 复制目录
                    String sourceDir = old + File.separator + value.getName();
                    String targetDir = target+ File.separator + value.getName();
                    try {
                        copyDirectiory(sourceDir, targetDir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return true;
    }


    private static void copyFile(File source,File target) throws IOException{

        RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
        FileChannel sourceChannel = sourceFile.getChannel();

        if (!target.isFile()) {
            if (!target.createNewFile()) {
                sourceChannel.close();
                sourceFile.close();
                return;
            }
        }
        RandomAccessFile destFile = new RandomAccessFile(target, "rw");
        FileChannel destChannel = destFile.getChannel();
        long leftSize = sourceChannel.size();
        long position = 0;
        while (leftSize > 0) {
            long writeSize = sourceChannel.transferTo(position, leftSize, destChannel);
            position += writeSize;
            leftSize -= writeSize;
        }
        sourceChannel.close();
        sourceFile.close();
        destChannel.close();
        destFile.close();
    }

    /**
     * 复制文件夹
     * @param sourceDir 复制的文件夹
     * @param targetDir 目标文件夹
     * */
    private static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        File file = new File(targetDir);
        if(!file.exists()) {
            if (!file.mkdirs()) {
                nkServer.getLogger().error("create" + targetDir + "error");
            }
        }
        // 获取源文件夹当前下的文件或目录
        File[] files = (new File(sourceDir)).listFiles();
        if(files != null){
            for (File value : files) {
                if (value.isFile()) {
                    // 源文件
                    // 目标文件
                    File targetFile = new
                            File(new File(targetDir).getAbsolutePath()
                            + File.separator + value.getName());
                    copyFile(value, targetFile);

                }
                if (value.isDirectory()) {
                    // 准备复制的源文件夹
                    String dir1 = sourceDir + File.separator + value.getName();
                    // 准备复制的目标文件夹
                    String dir2 = targetDir + File.separator + value.getName();
                    copyDirectiory(dir1, dir2);
                }
            }
        }

    }


    // 使用java.io.File创建文件夹
    public static void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs(); // 创建多级目录
        }
    }

    //判断是否存在文件夹
    public static boolean isFolder(String path) {
        File folder = new File(path);
        return folder.exists();
    }

}
