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
import cl.cutiko.stressless.models.ToDo;
import cl.cutiko.stressless.views.main.todoList.ClickListener;

/**
 * Created by cutiko on 27-07-16.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<ToDo> toDos;
    private ClickListener clickListener;

    public TodoAdapter(List<ToDo> toDos, ClickListener clickListener) {
        this.toDos = toDos;
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
        final ToDo toDo = toDos.get(position);

        CheckBox checkBox = holder.checkBox;
        checkBox.setChecked(toDo.isDone());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isDone(position);
                }
            }
        });


        TextView name = holder.name;
        name.setText(toDo.getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClicked(toDo.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDos.size();
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

    private void isDone(final int position){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toDos.remove(position);
                notifyDataSetChanged();
            }
        }, 1200);
    }

    public void add(ToDo toDo) {
        toDos.add(0, toDo);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        toDos.remove(position);
        notifyDataSetChanged();
    }

}
