package org.unitcase.srv;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 转换navicat导出的mysql的建表语句为h2的语法
 * 
 * 主要的要注意的点是:
 * 
 * 1.设置H2为mysql模式, 可以通过 SET MODE MYSQL;语句来实现
 * 
 * 2.'`'全部要去掉
 * 
 * 3.字段的字符集设置'COLLATE utf8_bin'不支持, 需要删除, 如这样的'`operator` varchar(10) COLLATE utf8_bin NOT NULL'
 * 
 * 4.注释按道理也没问题的, 但是没有用, 所以删除了.
 * 
 * 5.'ENGINE=InnoDB'设置不支持, 删掉
 * 
 * 6.'DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP'不支持, 修改为H2类似的'AS CURRENT_TIMESTAMP'
 * 
 * 7.H2的索引名必须要全局唯一, 所以需要替换所有的索引名为全局唯一
 * 
 * @author tudesheng
 * @since 2016年6月20日 下午8:37:52
 *
 */
public class TransformMysqlToH2 {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\test_io\\user.sql");
        String content = getFileContent(file);

        content = "SET MODE MYSQL;\n\n" + content;

        content = content.replaceAll("`", "");
        content = content.replaceAll("COLLATE.*(?=D)", "");
        content = content.replaceAll("COMMENT.*'(?=,)", "");
        content = content.replaceAll("\\).*ENGINE.*(?=;)", ")");
        content = content.replaceAll("DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", " AS CURRENT_TIMESTAMP");

        content = uniqueKey(content);

        System.out.println(content);
    }

    /**
     * h2的索引名必须全局唯一
     * 
     * @param content sql建表脚本
     * @return 替换索引名为全局唯一
     */
    private static String uniqueKey(String content) {
        int inc = 0;
        Pattern pattern = Pattern.compile("(?<=KEY )(.*?)(?= \\()");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group() + inc++);
        }
        matcher.appendTail(sb);
        content = sb.toString();
        return content;
    }

    /**
     * 读取文件里内容
     * @param file
     * @return
     * @throws Exception
     */
	private static String getFileContent(File file) throws Exception {
		if (file.length() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		byte[] b = new byte[(int) file.length()];
		InputStream in = new FileInputStream(file);
		try {
			in.read(b);
		} finally {
			if (null != in) {
				in.close();
			}
		}
		return new String(b, "UTF-8");
	}
}