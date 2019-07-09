package com.example.proficiencytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proficiencytest.R;
import com.example.proficiencytest.model.User;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private List<User> userList;
    Context context;

    public RecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.lastNametxt.setText(user.getLastName());
        holder.firstNametxt.setText(user.getFirstName());
        Picasso.with(context)
                .load(user.getAvatar())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public void setData(List<User> users) {
        this.userList.addAll(users);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView firstNametxt, lastNametxt;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            firstNametxt = view.findViewById(R.id.first_name_text);
            lastNametxt = view.findViewById(R.id.last_name_text);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
