package me.hsgamer.nbtcommanditems;

public enum NBTEnums {
    LEFT_CLICK("left-click"),
    RIGHT_CLICK("right-click"),
    ONE_TIME_USE("is-one-time-use")
    ;
    String nbttag;
    NBTEnums(String nbttag) {
        this.nbttag = "nbt-commanditems-" + nbttag;
    }

    public String get() {
        return nbttag;
    }
}
