package ganabet.sporttime;

import java.util.Arrays;
import java.util.List;

public class Base {
    static int arms = R.drawable.arms;
    static int chest = R.drawable.chest;
    static int legs = R.drawable.legs;
    public static List<String> name_muscle= Arrays.asList("Shoulders","Traps", "Quads", "Hamstrings", "Calves", "Chest",
            "Triceps", "Obliques", "Biceps", "Traps (mid-back)", "Lower back","Lats");

    public static List<Integer> muscle_id= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
    public static List<Integer> muscleImage = Arrays.asList(arms,arms,arms,arms,arms,chest,chest,chest,legs,legs,legs,legs);
    public static List<String> name_exer= Arrays.asList("Barbell Overhead Press","Dumbbell Seated Overhead Press",
            "Cable Lateral Raise", "Barbell Upright Row","Dumbbell Seated Shrug","Dumbbell Shrug","Barbell Silverback Shrug","Barbell Squat","Forward Lunges",
            "Machine Leg Extension","Kettlebell Step Up","Barbell Stiff Leg Deadlifts","Machine Hamstring Curl","Machine Standing Calf Raises",
            "Barbell Calf Raises","Kettlebell Single Leg Calf Raise","Barbell Bench Press","Push Up","Dumbbell Incline Bench Press",
            "Dumbbell Incline Chest Flys","Cable Push Down","Barbell Close Grip Bench Press","Dumbbell Skullcrusher","Bench Dips","Cable Pallof Press","Hand Side Plank",
            "Dumbbell Wood Chopper","Barbell Landmine Side Bend","Barbell Curl","Chin Ups","Dumbbell Curl","Dumbbell Hammer Curl ",
            "Pull Ups","Barbell Deadlift","Dumbbell Row Unilateral","Kettlebell Incline Shrug","Barbell Deadlift","Machine 45 Degree Back Extension",
            "Supermans","Barbell Bent Over Row","Machine Pulldown");
    public static List<Integer> id_musle_for_ex=  Arrays.asList(1,1,1,1,2,2,2,3,3,3,3,4,4,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,12,12);
    public static List<String> getName_muscle() {
        return name_muscle;
    }

    public static List<Integer> getMuscle_id() {
        return muscle_id;
    }

    public static List<String> getName_exer() {
        return name_exer;
    }

    public static List<Integer> getId_musle_for_ex() {
        return id_musle_for_ex;
    }

    public static List<Integer> getMuscleImage() {
        return muscleImage;
    }
}
