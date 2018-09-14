package com.example.administrator.xiudoufang.open.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.open.adapter.SalesProductListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11
 */

public class SameBarcodeSalesProductDialog extends DialogFragment implements View.OnClickListener {

    private FloatingActionButton mFabAction;
    private FloatingActionButton mFabComplete;

    private SalesProductListAdapter mAdapter;
    private List<SalesProductListBean.SalesProductBean> mList;
    private OnItemClickListener mListener;
    private AnimatorSet menuAnim;
    private boolean mIsShowMenu;

    public static SameBarcodeSalesProductDialog newInstance(int type, ArrayList<SalesProductListBean.SalesProductBean> list) {
        SameBarcodeSalesProductDialog dialog = new SameBarcodeSalesProductDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putParcelableArrayList("list", list);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getDialog().getWindow() != null;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_same_barcode_product, container);
        mFabAction = view.findViewById(R.id.fab_action);
        mFabComplete = view.findViewById(R.id.fab_complete);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mList = getArguments().getParcelableArrayList("list");
        mAdapter = new SalesProductListAdapter(R.layout.layout_list_item_product_list, mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        menuAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.fab_menu_anim);

        mFabAction.setOnClickListener(this);
        mFabComplete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        getDialog().getWindow().setLayout( dm.widthPixels, SizeUtils.dp2px(420));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_action:
                mFabAction.setImageResource(mIsShowMenu ? R.mipmap.ic_close_circle_white : R.mipmap.ic_menu_white);
                mIsShowMenu = !mIsShowMenu;
                if (mIsShowMenu) {
                    mFabComplete.setVisibility(View.VISIBLE);
                    menuAnim.setStartDelay(350);
                    menuAnim.start();
                    for (SalesProductListBean.SalesProductBean bean : mList) {
                        bean.setShowSelect(true);
                    }
                    mAdapter.setNewData(mList);
                } else {
                    mFabComplete.setVisibility(View.GONE);
                    for (SalesProductListBean.SalesProductBean bean : mList) {
                        bean.setShowSelect(false);
                    }
                    mAdapter.setNewData(mList);
                }
                break;
            case R.id.fab_complete:
                ArrayList<SalesProductListBean.SalesProductBean> list = new ArrayList<>();
                for (SalesProductListBean.SalesProductBean bean : mList) {
                    if (bean.isSelected()) {
                        list.add(bean);
                    }
                }
                mListener.onSubmit(list);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mListener != null) mListener.onDismiss();
        super.onDismiss(dialog);
    }

    public SameBarcodeSalesProductDialog setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
        return this;
    }

    public interface OnItemClickListener {
        void onSubmit(ArrayList<SalesProductListBean.SalesProductBean> list);
        void onDismiss();
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            mList.get(position).setSelected(!mList.get(position).isSelected());
            mAdapter.notifyItemChanged(position);
        }
    }
}
