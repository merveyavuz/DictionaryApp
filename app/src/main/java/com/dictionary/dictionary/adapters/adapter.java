package com.dictionary.dictionary.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dictionary.dictionary.R;
import com.dictionary.dictionary.listener.listener;
import com.dictionary.dictionary.models.Meaning;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.adapterViewHolder>{

    List<Meaning> list;
    Context context;
    listener listener;

    public adapter(List<Meaning> list, Context context, listener l) {
        this.list = list;
        this.context=context;
        this.listener = l;
    }

    @NonNull
    @Override
    public adapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new adapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.adapterViewHolder holder, int position) {
        Meaning meaning = list.get(position);
        if (meaning.getPhrase()!=null)
          holder.txtText.setText(meaning.getPhrase().getText().toString());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class adapterViewHolder extends RecyclerView.ViewHolder{

        public TextView txtText;
        public CardView cardView;

        public adapterViewHolder(final View itemView) {
            super(itemView);

           txtText = (TextView) itemView.findViewById(R.id.text);
           cardView = (CardView) itemView.findViewById(R.id.card_view);
//
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onDetail(itemView,getAdapterPosition());
//                }
//            });
        }
    }
}
