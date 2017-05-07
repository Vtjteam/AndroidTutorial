package vtj.ir.mcimtn;

/**
 * Created by javadroid on 5/7/17.
 */

public class ModelContact {
    private String name;
    private String number;
    public boolean isMtn = false;

    public ModelContact() {
    }

    public ModelContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
