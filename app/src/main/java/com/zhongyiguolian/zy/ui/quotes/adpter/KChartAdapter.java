package com.zhongyiguolian.zy.ui.quotes.adpter;

import com.github.tifezh.kchartlib.chart.BaseKChartAdapter;
import com.zhongyiguolian.zy.ui.quotes.beans.KLineEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * k线数据spq
 * @author cdj
 * @date 2020/12/10
 */
public class KChartAdapter extends BaseKChartAdapter {

    /**
     * 数据
     */
    private List<KLineEntity> datas = new ArrayList<>();

    /**
     * 构造函数
     */
    public KChartAdapter() {

    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        return datas.size();
    }

    /**
     * @param position 对应的序号
     * @return
     */
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public Date getDate(int position) {
        try {
            String s = datas.get(position).Date;
            String[] split = s.split("/");
            Date date = new Date();
            date.setYear(Integer.parseInt(split[0]) - 1900);
            date.setMonth(Integer.parseInt(split[1]) - 1);
            date.setDate(Integer.parseInt(split[2]));
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向头部添加数据
     * @param data
     */
    public void addHeaderData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 向尾部添加数据
     * @param data
     */
    public void addFooterData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 改变某个点的值
     * @param position 索引值
     * @param data
     */
    public void changeItem(int position,KLineEntity data)
    {
        datas.set(position,data);
        notifyDataSetChanged();
    }

}
