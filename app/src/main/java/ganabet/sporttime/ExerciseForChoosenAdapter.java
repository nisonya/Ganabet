package ganabet.sporttime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseForChoosenAdapter extends RecyclerView.Adapter<ExerciseForChoosenAdapter.ExerciseViewHoler> {

    interface OnExerciseClickListener{
        void onExersiseClick(Exercise exerciseItem);
    }
    private List<Exercise> mExercise;
    private final ExerciseForChoosenAdapter.OnExerciseClickListener onClickListener;
    private Context parent;
    public ExerciseForChoosenAdapter(List<Exercise> exercise, Context parent, ExerciseForChoosenAdapter.OnExerciseClickListener onClickListener){
        mExercise =exercise;
        this.onClickListener = onClickListener;
        this.parent = parent;
    }


    @NonNull
    @Override
    public ExerciseViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListitem = R.layout.exersise_for_choose_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(layoutIdForListitem, parent, false);
        ExerciseViewHoler viewHolder = new ExerciseViewHoler(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHoler holder, int position) {
        holder.bind(position);
        Exercise exerciseitem = mExercise.get(position);
        holder.nameMuscle.setText(String.valueOf(exerciseitem.getName()));
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int positionIndex = getAbsoluteAdapterPosition();
                onClickListener.onExersiseClick(exerciseitem);
                if(holder.checked.getVisibility()==View.INVISIBLE) holder.checked.setVisibility(View.VISIBLE);
                else holder.checked.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExercise.size();
    }

    class ExerciseViewHoler extends RecyclerView.ViewHolder {

        TextView nameMuscle;
        ImageView checked;
        ConstraintLayout cl;

        public ExerciseViewHoler(@NonNull View itemView) {
            super(itemView);
            nameMuscle = itemView.findViewById(R.id.exersiseForChooseTV);
            cl = itemView.findViewById(R.id.exersiseForChooseLayot);
            checked = itemView.findViewById(R.id.ivCheck);
        }
        void bind(int listIndex){
        }
    }
}

