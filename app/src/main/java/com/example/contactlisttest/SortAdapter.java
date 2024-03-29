package com.example.contactlisttest;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;


public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list=null;
    private Context mContext;

    public SortAdapter(Context context,List<SortModel> list){
        this.mContext=context;
        this.list=list;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr=list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            System.out.println("firsetChar===>"+firstChar);
            System.out.println("section====>"+section);
            if (firstChar==section){
                System.out.println("firstChar==section======>"+(firstChar==section));
                System.out.println("i========>"+i);
                return i;
            }
        }
        return  -1;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {  //没有item从屏幕移除
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sort_listview, arg2,false);//尽量让root不为空
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);//内容
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);//大写字母
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();//获取view的内容
        }

        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getName());

        return view;

    }

    @Override
    public Object[] getSections() {
        return null;
    }



    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }
}

