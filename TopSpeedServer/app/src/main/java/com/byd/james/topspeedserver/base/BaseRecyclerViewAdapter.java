package com.byd.james.topspeedserver.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by james on 2017/1/5.
 */

public  class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>{
    private RecyclerView mRecyclerView;
    private static final int item_type_normal=0x110;//普通的item
    private static final int item_type_head=0x111;//头视图的item
    private static final int item_type_foot=0x112;//足视图的item

    private View mHeaderView;
    private View mFooterView;
    private Context context;

    //存放数据的集合
    protected List<T> dataList;

    public boolean isHeader=false;
    public boolean isFooter=false;

    public BaseRecyclerViewAdapter(List<T> dataList,Context context) {
        this.dataList=dataList;
        this.context=context;
    }
    public void refreshData(List<T> dataList)
    {
        this.dataList.clear();
        this.dataList=dataList;
        notifyDataSetChanged();
    }
    public void addData(List<T> dataList)
    {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
    /**
     * 添加头布局
     * @param head
     */
    public void  setHeaderView(View head)
    {
        if(head!=null)
        {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            head.setLayoutParams(params);
            mHeaderView=head;
            notifyItemInserted(0);
            isHeader=true;
        }
    }

    /**
     * 添加尾部局
     * @param foot
     */
    public void setFooterView(View foot){
        if(foot!=null) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            foot.setLayoutParams(params);
            mFooterView = foot;
            notifyItemInserted(getItemCount() - 1);
            isFooter = true;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(isHeader&&position==0)
        {
            return item_type_head;
        }else if(isFooter){
            //没有头部有尾部
            return item_type_foot;
        }
        //正常情况
        return item_type_normal;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView!=null&&viewType==item_type_head)
        {
            return new BaseViewHolder(mHeaderView);
        }else if(mFooterView!=null&&viewType==item_type_foot)
        {
            return new BaseViewHolder(mFooterView);
        }
        View view= LayoutInflater.from(context).inflate(getLayoutId(),parent,false);
        return new BaseViewHolder(view);
    }

    /**
     * 添加
     * @param holder
     * @param position
     */
    @Override
    public  void onBindViewHolder(BaseRecyclerViewAdapter.BaseViewHolder holder, int position) {

    }

    protected  void bindData(BaseViewHolder holder, int position){

    }

    @Override
    public int getItemCount() {
        //返回集合中item布局数据的个数
        if(isFooter || isHeader)
        {
            return dataList.size()+1;
        }
        return dataList!=null?dataList.size():0;
    }
    //h获取单条布局的id
    public  int getLayoutId(){
        return 0;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeader || isFooter) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }
    class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
