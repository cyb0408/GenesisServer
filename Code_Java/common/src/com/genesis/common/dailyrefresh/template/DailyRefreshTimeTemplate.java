package com.genesis.common.dailyrefresh.template;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import com.genesis.core.template.annotation.ExcelRowBinding;
import com.genesis.core.template.exception.TemplateConfigException;
import com.genesis.core.util.TimeUtils;

import org.joda.time.LocalTime;

import java.util.List;
import java.util.Set;

@ExcelRowBinding
public class DailyRefreshTimeTemplate extends DailyRefreshTimeTemplateVO {
    private List<LocalTime> timeList = Lists.newArrayList();

    @Override
    public void patchUp() throws Exception {
        super.patchUp();
        for (String tempTime : this.getTimeArray()) {
            String time = tempTime.trim();
            if (time.length() > 0) {
                timeList.add(TimeUtils.parseLocalTime(time));
            }
        }
    }

    @Override
    public void check() throws TemplateConfigException {
        if (this.timeList.isEmpty()) {
            throwTemplateException("配置错误，刷新时间点1 为必填项");
        }

        boolean isFinished = false;
        Set<String> isExist = Sets.newHashSet();
        for (String tempTime : this.getTimeArray()) {
            String time = tempTime.trim();
            if (time.length() > 0) {
                if (isFinished) {
                    throwTemplateException("模板配置错误，时间点必须连续配置");
                }
                if (isExist.contains(time)) {
                    throwTemplateException("模板配置错误，同类型的刷新时间点不能相同");
                }

                isExist.add(time);
            } else {
                isFinished = true;
            }
        }
    }

    public List<LocalTime> getTimeList() {
        return this.timeList;
    }
}
