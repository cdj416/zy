package com.zhongyiguolian.zy.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.ui.home.beans.DeleteCoinType;

public class CoinTypeAdapter extends BaseQuickAdapter<DeleteCoinType, BaseViewHolder> {

    public CoinTypeAdapter(){
        super(R.layout.item_select_coin_type);
    }
    @Override
    protected void convert(BaseViewHolder helper, DeleteCoinType item) {
        helper.setText(R.id.coinName,item.getCoinName())
                .setText(R.id.coinType,item.getCoinType());

        if(item.isSelect()){
            helper.setBackgroundRes(R.id.coinType,R.drawable.shape_radius5_5690ff)
                    .setTextColor(R.id.coinType,mContext.getResources().getColor(R.color.theme_FFFFFF));
        }else {
            helper.setBackgroundRes(R.id.coinType,R.drawable.shape_radius6_ffffff)
                    .setTextColor(R.id.coinType,mContext.getResources().getColor(R.color.theme_333333));
        }

        helper.addOnClickListener(R.id.coinType);
    }
}
