package engine.utils;

import java.io.File;
import java.nio.file.Path;

/**
 * 路径实用类
 */
public class PathEx {
    /**
     * 从路径中获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileName(Path path) {
        File file = new File(path.toString());
        String fullName = file.getName();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    /**
     * 从完整文件名中获取不带扩展名的文件名
     *
     * @param fullName
     * @return
     */
    public static String getFileName(String fullName) {
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    /**
     * 获取路径中的文件扩展名
     *
     * @param path
     * @return
     */
    public static String getExtName(Path path) {
        File file = new File(path.toString());
        String fullName = file.getName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }
}
