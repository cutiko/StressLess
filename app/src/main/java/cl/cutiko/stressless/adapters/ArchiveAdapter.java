package cl.cutiko.stressless.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.models.Todo;
import cl.cutiko.stressless.views.archive.ArchiveClickListener;

/**
 * Created by cutiko on 27-07-16.
 */
public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ViewHolder> {

    private List<Todo> todos;
    private ArchiveClickListener clickListener;

    private Map<Long, Integer> map = new HashMap<>();
    private List<Long> ids = new ArrayList<>();

    public ArchiveAdapter(List<Todo> todos, ArchiveClickListener clickListener) {
        this.todos = todos;
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
        final Todo todo = todos.get(position);
        final long id = todo.getId();

        CheckBox checkBox = holder.checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    clickListener.selectedItem(1);
                    ids.add(id);
                    map.put(id, position);
                } else {
                    clickListener.selectedItem(-1);
                    int removePosition = map.get(id);
                    ids.remove(removePosition);
                }
            }
        });


        TextView name = holder.name;
        name.setText(todo.getName());
    }

    @Override
    public int getItemCount() {
        return todos.size();
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

    public long[] getIds() {
        long[] longIds = new long[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            longIds[i] = ids.get(i);
        }
        return longIds;
    }

}
