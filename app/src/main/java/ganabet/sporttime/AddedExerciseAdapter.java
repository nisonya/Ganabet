package ganabet.sporttime;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddedExerciseAdapter extends RecyclerView.Adapter<AddedExerciseAdapter.AddedExerciseViewHoler> {


    private List<Exercise> mExercise;
    private Context parent;
    public AddedExerciseAdapter(List<Exercise> exercise, Context parent){
        mExercise =exercise;
        this.parent = parent;
    }


    @NonNull
    @Override
    public AddedExerciseAdapter.AddedExerciseViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListitem = R.layout.exercise_for_add;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(layoutIdForListitem, parent, false);
        AddedExerciseAdapter.AddedExerciseViewHoler viewHolder = new AddedExerciseAdapter.AddedExerciseViewHoler(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddedExerciseAdapter.AddedExerciseViewHoler holder, int position) {
        holder.bind(position);
        Exercise exerciseitem = mExercise.get(position);
        holder.nameExercise.setText(exerciseitem.getName());
        holder.exerciseImage.setImageResource(exerciseitem.getPic());
        holder.edSets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                exerciseitem.setSets(holder.edSets.getText().toString());
                System.out.println(holder.edSets.getText());

            }
        });

        holder.edReps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                exerciseitem.setReps(holder.edReps.getText().toString());
                System.out.println(holder.edReps.getText());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExercise.size();
    }

    public List<Exercise> getData() {
        return mExercise;
    }

    class AddedExerciseViewHoler extends RecyclerView.ViewHolder {

        TextView nameExercise;
        ImageView exerciseImage;
        EditText edSets, edReps;

        public AddedExerciseViewHoler(@NonNull View itemView) {
            super(itemView);
            nameExercise = itemView.findViewById(R.id.tvExerName);
            exerciseImage = itemView.findViewById(R.id.ivMuscleGroup);
            edSets = itemView.findViewById(R.id.edSets);
            edReps = itemView.findViewById(R.id.edReps);
        }
        void bind(int listIndex){
        }
    }
}

