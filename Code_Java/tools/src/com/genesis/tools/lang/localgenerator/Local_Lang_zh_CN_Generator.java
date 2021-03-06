package com.genesis.tools.lang.localgenerator;

import com.genesis.tools.lang.item.LangItem;
import com.genesis.tools.lang.Constants;
import com.genesis.tools.lang.util.LangUtil;

import java.util.List;
import java.util.Map;

public class Local_Lang_zh_CN_Generator {

    /** 本地中文lang文件，用于本地测试 */
    public static final String LOCAL_ZN_CN_LANG_FILE_NAME = "local_zh_CN_lang.xlsx";

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Map<Integer, String> newData = LangUtil.loadNewData();
        List<LangItem> newDataList = LangUtil.convertToList(newData);
        LangUtil.createLangExcel(newDataList, 0,
                Constants.LANG_FILE_PATH + LOCAL_ZN_CN_LANG_FILE_NAME);
    }
}
