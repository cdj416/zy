package com.zhongyiguolian.zy.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.MultiItemViewModel;

/**
 * itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysTitleItemViewModel extends MultiItemViewModel<CountrysViewModel> {

    /**
     *
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * @param viewModel
     */
    public CountrysTitleItemViewModel(@NonNull CountrysViewModel viewModel, String letters) {
        super(viewModel);
        entity.set(letters);
    }
}
