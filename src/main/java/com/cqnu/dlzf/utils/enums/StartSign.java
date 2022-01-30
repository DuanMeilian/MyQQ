package com.cqnu.dlzf.utils.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum StartSign {
    Aquarius("水瓶座",1), Pisces("双鱼座",2),
    Aries("白羊座",3),Taurus("金牛座",4),
    Gemini("双子座",5),Cancer("巨蟹座",6),
    Leo("狮子座",7),Virgo("处女座",8),
    Libra("天秤座",9),Scorpio("天蝎座",10),
    Sagittarius("人马座",11),Capricorn("摩羯座",12);

    // 星座名
    private String name;

    // 星座号
    private int index;

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

    private StartSign(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 通过日期获得星座
     * 水瓶座：1月21日—2月19日。
     * 双鱼座：2月20日—3月20日。
     * 白羊座：3月21日—4月20日。
     * 金牛座：4月21日—5月21日。
     * 双子座：5月22日—6月21日。
     * 巨蟹座：6月22日—7月22日。
     * 狮子座：7月23日—8月23日。
     * 处女座：8月24日—9月23日。
     * 天秤座：9月24日—10月23日。
     * 天蝎座：10月24日—11月22日。
     * 射手座：11月23日—12月21日。
     * 魔羯座：12月22日—1月20日。
     * @param birthDay
     * @return
     */
    public static StartSign date2StartSign(Date birthDay) throws ParseException {
        String nowTime = sdf.format(birthDay);

        if (isWithin("01-21","02-19",nowTime)) return Aquarius;
        else if (isWithin("02-20","03-20",nowTime)) return Pisces;
        else if (isWithin("03-21","04-20",nowTime)) return Aries;
        else if (isWithin("04-21","05-21",nowTime)) return Taurus;
        else if (isWithin("05-22","06-21",nowTime)) return Gemini;
        else if (isWithin("06-22","07-22",nowTime)) return Cancer;
        else if (isWithin("07-23","08-23",nowTime)) return Leo;
        else if (isWithin("08-24","09-23",nowTime)) return Virgo;
        else if (isWithin("09-24","10-23",nowTime)) return Libra;
        else if (isWithin("10-24","11-22",nowTime)) return Scorpio;
        else if (isWithin("11-23","12-21",nowTime)) return Sagittarius;
        else return Capricorn;
    }

    /**
     * 判断某个日期是否在两个日期范围之内(格式为MM-dd)
     * @param startTime
     * @param endTime
     * @param nowTime
     * @return
     * @throws ParseException
     */
    public static boolean isWithin(String startTime,
                                   String endTime,
                                   String nowTime) throws ParseException {
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        Date now = sdf.parse(nowTime);

        if(now.getTime() >= start.getTime() && now.getTime() <= end.getTime()){
            return true;
        }
        return false;
    }
}
