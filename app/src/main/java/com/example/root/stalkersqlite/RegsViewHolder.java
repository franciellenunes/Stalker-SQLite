package com.example.root.stalkersqlite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RegsViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private TextView regsTextView;
    private RegsAdapter.RegsListener listener;
    private Vitima vitima;

    public RegsViewHolder(View view, RegsAdapter.RegsListener listener){
        super(view);
        this.regsTextView = (TextView) view.findViewById(R.id.regs_text_view);
        this.listener = listener;
        view.setOnClickListener(this);
    }

    public void bind(Vitima vitima){
        this.vitima = vitima;
        this.regsTextView.setText(vitima.getNome());
    }

    @Override
    public void onClick(View v) {
        listener.onClickRegs(this.vitima);
    }
}
