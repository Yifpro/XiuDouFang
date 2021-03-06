package com.example.administrator.xiudoufang.receipt.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.receipt.adapter.SubjectSelectorAdapter;

import java.util.ArrayList;

public class SubjectSelectorDialog extends DialogFragment {

    private OnItemChangedListener mListener;
    private ArrayList<SubjectListBean.AccounttypesBean> list;
    private SubjectSelectorAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static SubjectSelectorDialog newInstance(ArrayList<SubjectListBean.AccounttypesBean> list) {
        SubjectSelectorDialog fragment = new SubjectSelectorDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_subject_selector, container);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        list = getArguments().getParcelableArrayList("data");
        mAdapter = new SubjectSelectorAdapter(R.layout.item_subject_selector, list);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public void setOnItemChangedListener(OnItemChangedListener listener) {
        this.mListener = listener;
    }

    public interface OnItemChangedListener {
        void onItemChanged(String subjectId, String direction, String item);
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (mListener != null) {
                SubjectListBean.AccounttypesBean bean = list.get(position);
                mListener.onItemChanged(bean.getAccountid(), bean.getDirection(), bean.getShow_name());
            }
            dismiss();
        }
    }
}
