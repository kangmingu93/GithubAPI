package com.programers.githubapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    UserVO userVO;
    ArrayList<ReposVO> items;

    class User_ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName;

        public User_ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.userImage);
            userName = (TextView) itemView.findViewById(R.id.userName);
        }

    }

    class Repos_ViewHolder extends RecyclerView.ViewHolder {

        TextView reposName, reposDescrip, reposStar;

        public Repos_ViewHolder(@NonNull View itemView) {
            super(itemView);
            reposName = (TextView) itemView.findViewById(R.id.reposName);
            reposDescrip = (TextView) itemView.findViewById(R.id.reposDescrip);
            reposStar = (TextView) itemView.findViewById(R.id.reposStar);
        }

    }

    public MyAdapter(UserVO userVO, ArrayList<ReposVO> items) {
        this.userVO = userVO;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
                return new User_ViewHolder(view1);
            default:
                View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repos_item, viewGroup, false);
                return new Repos_ViewHolder(view2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                User_ViewHolder userViewHolder = (User_ViewHolder) viewHolder;

                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_launcher_background);
                requestOptions.error(R.drawable.ic_launcher_background);

                RequestManager requestManager = Glide.with(userViewHolder.userImage.getContext());
                RequestBuilder requestBuilder = requestManager.load(userVO.getAvatar_url());
                requestBuilder.thumbnail(0.5f);
                requestBuilder.apply(requestOptions);
                requestBuilder.into(userViewHolder.userImage);
                userViewHolder.userName.setText(userVO.getLogin());

                break;

            default:
                Repos_ViewHolder reposViewHolder = (Repos_ViewHolder) viewHolder;

                reposViewHolder.reposName.setText(items.get(i).getName());
                reposViewHolder.reposDescrip.setText(items.get(i).getDescription());
                reposViewHolder.reposStar.setText(items.get(i).getStargazers_count());

                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
