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
import cl.cutiko.stressless.data.Todos;
import cl.cutiko.stressless.models.Pending;
import cl.cutiko.stressless.views.main.todoList.ClickListener;

/**
 * Created by cutiko on 27-07-16.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<Pending> pendings = new Todos().pendings();
    private ClickListener clickListener;

    public TodoAdapter(ClickListener clickListener) {
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
        final Pending pending = pendings.get(position);

        CheckBox checkBox = holder.checkBox;
        checkBox.setChecked(pending.isDone());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isDone(position);
                        }
                    }, 100);
                }
            }
        });


        TextView name = holder.name;
        name.setText(pending.getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClicked(pending.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View colorHolder;
        CheckBox checkBox;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            colorHolder = itemView.findViewById(R.id.colorHolder);
            checkBox = (CheckBox) itemView.findViewById(R.id.todoCb);
            name = (TextView) itemView.findViewById(R.id.todoName);
        }
    }

    private void isDone(final int position){
        Pending pending = pendings.get(position);
        pending.setDone(true);
        pending.save();
        pendings.remove(position);
        notifyDataSetChanged();
    }

    public void add(Pending pending) {
        pendings.add(0, pending);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        pendings.remove(position);
        notifyDataSetChanged();
    }

    public void byName(String name) {
        pendings.clear();
        List<Pending> byName = new Todos().byName(name);
        if (byName != null && byName.size() > 0) {
            pendings.addAll(byName);
        }
        notifyDataSetChanged();
    }

    public void reset() {
        pendings.clear();
        pendings.addAll(new Todos().pendings());
        notifyDataSetChanged();
    }

}
