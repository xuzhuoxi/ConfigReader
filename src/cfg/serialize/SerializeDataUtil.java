package cfg.serialize;

import java.nio.charset.Charset;
import java.util.List;

import cfg.serialize.cfgdata.ISheetHandler;
import cfg.serialize.cfgdata.SheetHandlerFactory;
import cfg.serialize.exceptions.SheetDataException;
import cfg.serialize.exceptions.SheetDefineException;
import cfg.settings.Settings;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.lang.StringUtil;

public class SerializeDataUtil {
    /**
     * 导出数据<br>
     * 如果fieldRange对应的字段数为0，则直接跳过
     *
     * @param sheetInfo    Excel中的一个sheet数据对象
     * @param fileFormat   输出文件格式
     * @param fieldRange   导出的字段范围类型
     * @param outputFolder 文件输出目录
     * @param fieldKey     字段使用的键值类型
     * @throws SheetDataException 数据异常信息
     */
    public static void serializeData(SheetInfo sheetInfo, OutputDataFormat fileFormat, FieldRangeType fieldRange,
                                     String outputFolder, FieldKey fieldKey) throws SheetDataException {
        if (sheetInfo.getDefine().isFieldRangeEmpty(fieldRange)) {// 跳过无索引字段的表
            return;
        }
        ISheetHandler handler = SheetHandlerFactory.getSheetHandler(fileFormat);
        handler.config(sheetInfo);
        Object out = handler.serialize(fieldRange, fieldKey);
        String fileName = sheetInfo.getDefine().getExportInfo(fieldRange).getFileName();
        if (null == fileName || fileName.length() == 0) {
            Exception e = new SheetDefineException("FileName is Empty: " + sheetInfo.getSheetNamed());
            e.printStackTrace();
            return;
        }
        String outputFilePath = outputFolder + "/" + fileName + "." + fileFormat.getValue();
        if (fileFormat.isTextFile()) {
            System.out.println("\t输出数据文件(字符)(" + Settings.getInstance().getSysSettings().getTargetEncoding() + ")："
                    + outputFilePath);
            Charset outputCharset = Settings.getInstance().getSysSettings().getTargetCharset();
            String outJsonStr = (String) out;
            if (!Settings.getInstance().getSysSettings().isEncodingConsistent()) {
                outJsonStr = StringUtil.changeCharset(outJsonStr,
                        Settings.getInstance().getSysSettings().getSourceCharset(), outputCharset);
            }
            FileUtils.writeTextFile(outputFilePath, outJsonStr, outputCharset);
            // FileUtils.writeTextFile(outputFilePath, (String) out,
            // outputCharset);
            // System.out.println(out);
        } else {
            System.out.println("\t输出数据文件(字节):" + outputFilePath);
            FileUtils.writeBinaryFile(outputFilePath, (byte[]) out);
        }
    }

    /**
     * 批量导出数据
     *
     * @param sheetInfos   Excel中的一个sheet数据对象组成的数组
     * @param fileFormat   输出文件格式
     * @param fieldRange   导出的字段范围类型
     * @param outputFolder 文件输出目录
     * @param fieldKey     字段使用的键值类型
     * @throws SheetDataException 数据异常信息
     */
    public static void serializeData(List<SheetInfo> sheetInfos, OutputDataFormat fileFormat, FieldRangeType fieldRange,
                                     String outputFolder, FieldKey fieldKey) throws SheetDataException {
        for (SheetInfo sheetInfo : sheetInfos) {
            System.out.println("Start Serialize Data: [" + sheetInfo.getSheetNamed() + "," + fileFormat + "]");
            serializeData(sheetInfo, fileFormat, fieldRange, outputFolder, fieldKey);
        }
    }
}
