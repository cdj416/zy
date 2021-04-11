package com.zhongyiguolian.zy.ui.person.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.ui.person.beans.PledgBalanceListBean;

/**
 * 说明：
 * 作者：cdj
 */
public class PopSelcetSericeAdapter extends BaseQuickAdapter<PledgBalanceListBean.MinListDTO, BaseViewHolder> {

    public PopSelcetSericeAdapter(){
        super(R.layout.pop_item_service);
    }

    @Override
    protected void convert(BaseViewHolder helper, PledgBalanceListBean.MinListDTO item) {

        if(helper.getAdapterPosition() == (getData().size() - 1)){
            helper.setVisible(R.id.bottomLine,false);
        }else{
            helper.setVisible(R.id.bottomLine,true);
        }

        helper.setText(R.id.sName,"编号"+item.getOrderNumber()+",容量"+item.getCalculationPower()+"T");

        if(item.isSelect()){
            helper.setVisible(R.id.checkImg,true);
            helper.setTextColor(R.id.sName,0xFF1A66B2);
        }else{
            helper.setVisible(R.id.checkImg,false);
            helper.setTextColor(R.id.sName,0xFF666666);
        }

        helper.addOnClickListener(R.id.root);
    }
}
