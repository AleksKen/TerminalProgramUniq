package tasks;

public class PrefixAndStr {

    private Integer pref;
    private String str;

    //конструктор
    public PrefixAndStr(Integer pref, String str) {
        this.pref = pref;
        this.str = str;
    }

    public PrefixAndStr() {

    }

    public void add(Integer num, String line) {
        pref = num;
        str = line;
    }

    public void change(Integer num) {
        pref = num;
    }

    public int getPref() {
        return pref;
    }

    public String getStr() {
        return str;
    }

    public String getWithPref() {
        return pref.toString() + " " + str;
    }

    public String getNotPref() {
        return str;
    }
}
