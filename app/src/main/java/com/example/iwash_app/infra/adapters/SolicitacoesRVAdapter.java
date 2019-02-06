package com.example.iwash_app.infra.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwash_app.R;
import com.example.iwash_app.models.Solicitacao;

import java.util.List;

public class SolicitacoesRVAdapter extends RecyclerView.Adapter<SolicitacoesRVAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<Solicitacao> solicitacoes;

    public SolicitacoesRVAdapter(Activity activity, Context context, List<Solicitacao> solicitacoes) {
        this.activity = activity;
        this.context = context;
        this.solicitacoes = solicitacoes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDescricao;
        private TextView txtLavanderiaDataSolicitacao;
        private ImageView imgStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDescricao = itemView.findViewById(R.id.txtDescricaoSolicitacao);
            txtLavanderiaDataSolicitacao = itemView.findViewById(R.id.txtLavanderiaDataSolicitacao);
            imgStatus = itemView.findViewById(R.id.imgStatusSolicitacao);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_solicitacoes, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Solicitacao solicitacao = solicitacoes.get(i);

        viewHolder.txtDescricao.setText(solicitacao.getServicoSolicitado());
        viewHolder.txtLavanderiaDataSolicitacao.setText(solicitacao.getLavanderia() + " em 24 de Fevereiro");

        if (solicitacao.getStatus().equals("A")){
            viewHolder.imgStatus.setImageResource(R.drawable.icons8_watch_96);
        }else if (solicitacao.getStatus().equals("C")){
            viewHolder.imgStatus.setImageResource(R.drawable.icons8_checked_96);
        }else{
            viewHolder.imgStatus.setImageResource(R.drawable.icons8_cancel_96);
        }
    }

    @Override
    public int getItemCount() {
        return solicitacoes.size();
    }
}
