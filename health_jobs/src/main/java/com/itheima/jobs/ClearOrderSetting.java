package com.itheima.jobs;

import com.itheima.service.OrderSettingService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ClearOrderSetting {

    @Autowired
    private OrderSettingService orderSettingService;

    public void clearOrderSetting() throws Exception {
        Date date = new Date();
        String today = DateUtils.parseDate2String(date);
        orderSettingService.clearOrderSetting(today);
    }
}
