package ganabet.sporttime;

public class Exercise {
    int id;
    String name;
    int pic;
    String sets;
    String reps;

    public Exercise(int id, String name,int pic, String sets, String reps) {
        this.id = id;
        this.name = name;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPic() {
        return pic;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }
}
