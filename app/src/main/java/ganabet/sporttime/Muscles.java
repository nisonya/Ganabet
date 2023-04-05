package ganabet.sporttime;

public class Muscles {
    private String name;
    private int pic;
    private int id;

    public Muscles(String name, int pic, int id) {
        this.name = name;
        this.pic = pic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPic() {
        return pic;
    }
    public int getId() {
        return id;
    }
}