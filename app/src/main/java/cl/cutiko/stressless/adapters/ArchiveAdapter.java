package cl.cutiko.stressless.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.models.Pending;
import cl.cutiko.stressless.views.archive.ArchiveClickListener;

/**
 * Created by cutiko on 27-07-16.
 */
public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ViewHolder> {

    private List<Pending> pendings;
    private ArchiveClickListener clickListener;

    public ArchiveAdapter(List<Pending> pendings, ArchiveClickListener clickListener) {
        this.pendings = pendings;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_todo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        Pending pending = pendings.get(position);

        CheckBox checkBox = holder.checkBox;

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    clickListener.selectedItem(1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            remove(position);
                        }
                    }, 100);
                }

            }
        });


        TextView name = holder.name;
        name.setText(pending.getName());
    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.todoCb);
            name = (TextView) itemView.findViewById(R.id.todoName);
        }
    }

    private void remove(int position){
        pendings.get(position).undo();
        pendings.remove(position);
        notifyDataSetChanged();
    }

}
