package com.example.iwash_app.infra.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iwash_app.MainActivity;
import com.example.iwash_app.R;
import com.example.iwash_app.infra.utils.ConstantsWash;
import com.example.iwash_app.models.Lavanderia;

import org.w3c.dom.Text;

import java.util.List;

public class LavanderiasAdapter extends RecyclerView.Adapter<LavanderiasAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<Lavanderia> lavanderias;

    public LavanderiasAdapter(Activity activity, Context context, List<Lavanderia> lavanderias) {
        this.activity = activity;
        this.context = context;
        this.lavanderias = lavanderias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNomeFantasia;
        private TextView txtEndereco;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomeFantasia = itemView.findViewById(R.id.txtNomeFantasiaLavanderias);
            txtEndereco = itemView.findViewById(R.id.txtEnderecoLavanderias);
        }
    }

    @NonNull
    @Override
    public LavanderiasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_lavanderias, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LavanderiasAdapter.ViewHolder viewHolder, int i) {
        Lavanderia lavanderia = lavanderias.get(i);

        viewHolder.txtNomeFantasia.setText(lavanderia.getNomeFantasia());
        viewHolder.txtEndereco.setText(lavanderia.getEndereco());

        viewHolder.itemView.setOnClickListener(onclick -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(ConstantsWash.LAVANDERIA_SELECT, lavanderia.getId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lavanderias.size();
    }
}
