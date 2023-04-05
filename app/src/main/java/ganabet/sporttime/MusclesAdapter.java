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

import com.google.android.material.chip.Chip;

import java.util.List;

public class MusclesAdapter extends RecyclerView.Adapter<MusclesAdapter.MusclesViewHoler> {

    interface OnMusclesClickListener{
        void onMusclesClick(Muscles musclesItem);
    }
    private List<Muscles> mMuscles;
    private final OnMusclesClickListener onClickListener;
    private Context parent;
    public MusclesAdapter(List<Muscles> muscles, Context parent, OnMusclesClickListener onClickListener){
        mMuscles =muscles;
        this.onClickListener = onClickListener;
        this.parent = parent;
    }


    @NonNull
    @Override
    public MusclesViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListitem = R.layout.muscle_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(layoutIdForListitem, parent, false);
        MusclesViewHoler viewHolder = new MusclesViewHoler(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusclesViewHoler holder, int position) {
        holder.bind(position);
        Muscles musclesItem = mMuscles.get(position);
        holder.nameMuscle.setText(musclesItem.getName());
        holder.imageMuscle.setImageResource(musclesItem.getPic());
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int positionIndex = getAbsoluteAdapterPosition();
                onClickListener.onMusclesClick(musclesItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMuscles.size();
    }

    class MusclesViewHoler extends RecyclerView.ViewHolder {

        ImageView imageMuscle;
        TextView nameMuscle;
        ConstraintLayout cl;

        public MusclesViewHoler(@NonNull View itemView) {
            super(itemView);
            imageMuscle = itemView.findViewById(R.id.musclePicture);
            nameMuscle = itemView.findViewById(R.id.muscleName);
            cl = itemView.findViewById(R.id.muscleLayot);

        }
        void bind(int listIndex){
        }
    }
}

