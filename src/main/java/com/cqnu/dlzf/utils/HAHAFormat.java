package com.cqnu.dlzf.utils;

import com.cqnu.dlzf.utils.enums.AnimalSign;
import com.cqnu.dlzf.utils.enums.StartSign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class HAHAFormat {

    /**
     * 格式化UserHAHA的最新消息的Date对象，规则返回：
     * 如果是今天，则返回当天的时分，比如"17:20"；
     * 如果是昨天，则返回"昨天"；
     * 如果是今年，则返回月和日，比如"6-20"；
     * 如果不是今年，则返回年月日，比如"2020-11-16"
     * @param time
     * @return
     */
    public String latestTimeFormat(Date time){

        if (time == null) return null;

        Calendar timeCalender = Calendar.getInstance();
        timeCalender.setTime(time);

        Calendar nowCalender = Calendar.getInstance();
        nowCalender.setTime(new Date(System.currentTimeMillis()));

        // 不是今年
        if (timeCalender.get(Calendar.YEAR) != nowCalender.get(Calendar.YEAR)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(time);
        }

        // 今天
        if (timeCalender.get(Calendar.MONTH) == nowCalender.get(Calendar.MONTH) &&
                timeCalender.get(Calendar.DATE) == nowCalender.get(Calendar.DATE)){

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(time);
        }

        // 昨天
        if (timeCalender.get(Calendar.MONTH) == nowCalender.get(Calendar.MONTH) &&
                nowCalender.get(Calendar.DATE) - timeCalender.get(Calendar.DATE) == 1){

            return "昨天";
        }

        // 今年
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(time);
    }

    /**
     * 格式化聊天信息的时间，规则如下：
     * 如果是今天，返回当天的时分秒，比如"8:48:09"；
     * 其余都返回年月日时分秒，比如“2021/12/30 23:21:32”
     * @param time
     * @return
     */
    public String chartTimeFormat(Date time){
        if (time == null) return null;

        Calendar timeCalender = Calendar.getInstance();
        timeCalender.setTime(time);

        Calendar nowCalender = Calendar.getInstance();
        nowCalender.setTime(new Date(System.currentTimeMillis()));

        // 今天
        if (timeCalender.get(Calendar.MONTH) == nowCalender.get(Calendar.MONTH) &&
                timeCalender.get(Calendar.DATE) == nowCalender.get(Calendar.DATE)){

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(time);
        }
        // 其余
        else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf.format(time);
        }
    }

    /**
     * 格式化用户信息:性别 年龄 生日 星座 生肖
     * @param gender 性别
     * @param birthday 生日
     * @return
     */
    public String userInfoFormat(String gender, Date birthday){
        StringBuilder info = new StringBuilder("");

        if (gender != null && !"".equals(gender)){
            info.append(gender);
            info.append(" ");
        }

        if (birthday != null){
            info.append(getAgeByBirthday(birthday));
            info.append("岁 ");

            SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
            String formatBirthDay = sdf.format(birthday);
            info.append(formatBirthDay);
            info.append("（公历）");

            try {
                String startSign = StartSign.date2StartSign(birthday).getName();
                info.append(" ");
                info.append(startSign);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar birthdayCalendar = Calendar.getInstance();
            birthdayCalendar.setTime(birthday);
            int birthdayYear = birthdayCalendar.get(Calendar.YEAR);

            String animalSign = Objects.requireNonNull(AnimalSign.getAnimalSign(birthdayYear)).getName();
            info.append(" ");
            info.append("属");
            info.append(animalSign);
        }
        return info.toString();
    }

    /**
     * 根据生日获取年龄
     * @param birthday
     * @return
     */
    public int getAgeByBirthday(Date birthday){
        if (birthday == null) return -1;
        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date(System.currentTimeMillis()));

        int birthdayYear = birthdayCalendar.get(Calendar.YEAR);
        int nowYear = nowCalendar.get(Calendar.YEAR);
        return nowYear - birthdayYear;
    }

    /**
     * 获取年份列表
     * @param startYear
     * @return
     */
    public List<String> getYears(int startYear) {
        List<String> years = new ArrayList<String>();
        years.add("");

        Date now = new Date(System.currentTimeMillis());
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(now);

        for (int i = startYear; i <= nowCalendar.get(Calendar.YEAR); i++) {
            years.add(i + "年");
        }
        return years;
    }


    /**
     * 获取月份列表
     * @return
     */
    public List<String> getMonths() {
        List<String> months = new ArrayList<String>();
        months.add("");

        for (int i = 1; i <= 12; i++) {
            months.add(i + "月");
        }
        return months;
    }

    /**
     * 获取日期列表
     * @return
     */
    public List<String> getDays() {
        List<String> days = new ArrayList<String>();
        days.add("");

        for (int i = 1; i <= 31; i++) {
            days.add(i + "日");
        }
        return days;
    }

    /**
     * 判断是否为闰年
     * 条件：①：非整百年数除以4，无余为闰，有余为平；②整百年数除以400，无余为闰有余平。
     * @param year
     * @return
     */
    public boolean isLeap(int year) {
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0)) return true;
        return false;
    }
}
