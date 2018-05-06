package com.example.root.stalkersqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class RegsAdapter extends RecyclerView.Adapter<RegsViewHolder>{

    private RegsListener listener;
    private Vitima[] vit;
    private int count;

    public interface RegsListener {
        void onClickRegs(Vitima vit);
    }

    public RegsAdapter(RegsListener listener) throws DBStalkerException {
        this.listener = listener;
        this.vit = DBStalker.vitimasToArray();
        this.count = 0;
    }

    /* Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view */
    @NonNull
    @Override
    public RegsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID = R.layout.activity_regs_item_holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = inflater.inflate(layoutID, parent, attachToParent);
        RegsViewHolder viewHolder = new RegsViewHolder(view, this.listener);
        this.count++;
        return viewHolder;
    }

    /* Método que recebe o ViewHolder e a posição da lista.
    Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder. */
    @Override
    public void onBindViewHolder(RegsViewHolder holder, int position) {
        Vitima vitima = this.vit[position];
        holder.bind(vitima);
    }

    /* Método que deverá retornar quantos itens há na lista.  */
    @Override
    public int getItemCount() { return this.vit.length; }
}


