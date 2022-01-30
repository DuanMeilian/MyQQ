package com.cqnu.dlzf.utils.enums;

public enum AnimalSign {
    Monkey("猴",0),Rooster("鸡",1),
    Dog("狗",2),Pig("猪",3),
    Rat("鼠",4),OX("牛",5),
    Tiger("虎",6),Rabbit("兔",7),
    Dragon("龙",8),Snake("蛇",9),
    Horse("马",10),Sheep("羊",11);

    // 生肖名
    private String name;

    // 生肖号
    private int index;

    private AnimalSign(String name, int index) {
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
     * 根据年份查询生肖对象
     * @param year
     * @return
     */
    public static AnimalSign getAnimalSign(int year){
        int animalSignIndex = year % 12;
        for (AnimalSign animalSign : AnimalSign.values()) {
            if (animalSign.getIndex() == animalSignIndex) return animalSign;
        }
        return null;
    }
}
