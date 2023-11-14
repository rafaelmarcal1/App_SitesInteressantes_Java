package br.edu.ifsp.dmo.sitesinteressantes.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo.sitesinteressantes.R;
import br.edu.ifsp.dmo.sitesinteressantes.model.Site;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder>{

    private List<Site> dataset;

    public SiteAdapter(List<Site> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTextView.setText(dataset.get(position).getTitle());
        holder.urlTextView.setText(dataset.get(position).getUrl());
        holder.tagTextView.setText(dataset.get(position).getTag().getTag());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView urlTextView;
        public TextView tagTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textview_title);
            urlTextView = itemView.findViewById(R.id.textview_url);
            tagTextView = itemView.findViewById(R.id.textview_tag_site);
        }
    }
}