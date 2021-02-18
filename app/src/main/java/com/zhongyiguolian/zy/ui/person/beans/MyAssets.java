package com.zhongyiguolian.zy.ui.person.beans;

import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class MyAssets {

    /**
     * assetConvert : {"USDT":{"total":"0","cnyPrice":"0","convertSymbol":"","frozenString":"0.00000","usdtTotal":"0","precision":0,"available":"0","frozen":"0","availableString":"0.00000","totalCny":"0.00","usdtTotalString":"42.69110"}}
     * assetsList : [{"symbol":"USDT","lockAssets":"0.00000","hasTag":false,"totalAssets":"0.00000","precision":5,"usedAssets":"0.00000","totalAssetsCnyString":"","totalAssetsUsdtString":"0.00000","frozenAssets":"0.00000","bofuAssets":"0.00000","number":"USDT","tbState":0,"imageUrl":"/currency/1587376646149.png","cbState":0,"currencyId":1},{"symbol":"FIL","lockAssets":"0.00000","hasTag":false,"totalAssets":"2.00025","precision":5,"usedAssets":"0.00000","totalAssetsCnyString":"","totalAssetsUsdtString":"42.69110","frozenAssets":"0.00000","bofuAssets":"2.00025","number":"FIL","tbState":1,"imageUrl":"/currency/1587376646149.png","cbState":1,"currencyId":2},{"symbol":"CNY","lockAssets":"0.00000","hasTag":false,"totalAssets":"0.00000","precision":5,"usedAssets":"0.00000","totalAssetsCnyString":"","totalAssetsUsdtString":"0.00000","frozenAssets":"0.00000","bofuAssets":"0.00000","number":"CNY","tbState":1,"imageUrl":"coin","cbState":0,"currencyId":6}]
     */

    private AssetConvertDTO assetConvert;
    private List<AssetsListDTO> assetsList;

    public AssetConvertDTO getAssetConvert() {
        return assetConvert;
    }

    public void setAssetConvert(AssetConvertDTO assetConvert) {
        this.assetConvert = assetConvert;
    }

    public List<AssetsListDTO> getAssetsList() {
        return assetsList;
    }

    public void setAssetsList(List<AssetsListDTO> assetsList) {
        this.assetsList = assetsList;
    }

    public static class AssetConvertDTO {
        /**
         * USDT : {"total":"0","cnyPrice":"0","convertSymbol":"","frozenString":"0.00000","usdtTotal":"0","precision":0,"available":"0","frozen":"0","availableString":"0.00000","totalCny":"0.00","usdtTotalString":"42.69110"}
         */

        private USDTDTO USDT;

        public USDTDTO getUSDT() {
            return USDT;
        }

        public void setUSDT(USDTDTO USDT) {
            this.USDT = USDT;
        }

        public static class USDTDTO {
            /**
             * total : 0
             * cnyPrice : 0
             * convertSymbol :
             * frozenString : 0.00000
             * usdtTotal : 0
             * precision : 0
             * available : 0
             * frozen : 0
             * availableString : 0.00000
             * totalCny : 0.00
             * usdtTotalString : 42.69110
             */

            private String total;
            private String cnyPrice;
            private String convertSymbol;
            private String frozenString;
            private String usdtTotal;
            private int precision;
            private String available;
            private String frozen;
            private String availableString;
            private String totalCny;
            private String usdtTotalString;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getCnyPrice() {
                return cnyPrice;
            }

            public void setCnyPrice(String cnyPrice) {
                this.cnyPrice = cnyPrice;
            }

            public String getConvertSymbol() {
                return convertSymbol;
            }

            public void setConvertSymbol(String convertSymbol) {
                this.convertSymbol = convertSymbol;
            }

            public String getFrozenString() {
                return frozenString;
            }

            public void setFrozenString(String frozenString) {
                this.frozenString = frozenString;
            }

            public String getUsdtTotal() {
                return usdtTotal;
            }

            public void setUsdtTotal(String usdtTotal) {
                this.usdtTotal = usdtTotal;
            }

            public int getPrecision() {
                return precision;
            }

            public void setPrecision(int precision) {
                this.precision = precision;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getFrozen() {
                return frozen;
            }

            public void setFrozen(String frozen) {
                this.frozen = frozen;
            }

            public String getAvailableString() {
                return availableString;
            }

            public void setAvailableString(String availableString) {
                this.availableString = availableString;
            }

            public String getTotalCny() {
                return totalCny;
            }

            public void setTotalCny(String totalCny) {
                this.totalCny = totalCny;
            }

            public String getUsdtTotalString() {
                return usdtTotalString;
            }

            public void setUsdtTotalString(String usdtTotalString) {
                this.usdtTotalString = usdtTotalString;
            }
        }
    }

    public static class AssetsListDTO {
        /**
         * symbol : USDT
         * lockAssets : 0.00000
         * hasTag : false
         * totalAssets : 0.00000
         * precision : 5
         * usedAssets : 0.00000
         * totalAssetsCnyString :
         * totalAssetsUsdtString : 0.00000
         * frozenAssets : 0.00000
         * bofuAssets : 0.00000
         * number : USDT
         * tbState : 0
         * imageUrl : /currency/1587376646149.png
         * cbState : 0
         * currencyId : 1
         */

        private String symbol;
        private String lockAssets;
        private boolean hasTag;
        private String totalAssets;
        private int precision;
        private String usedAssets;
        private String totalAssetsCnyString;
        private String totalAssetsUsdtString;
        private String frozenAssets;
        private String bofuAssets;
        private String number;
        private int tbState;
        private String imageUrl;
        private int cbState;
        private int currencyId;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getLockAssets() {
            return lockAssets;
        }

        public void setLockAssets(String lockAssets) {
            this.lockAssets = lockAssets;
        }

        public boolean isHasTag() {
            return hasTag;
        }

        public void setHasTag(boolean hasTag) {
            this.hasTag = hasTag;
        }

        public String getTotalAssets() {
            return totalAssets;
        }

        public void setTotalAssets(String totalAssets) {
            this.totalAssets = totalAssets;
        }

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public String getUsedAssets() {
            return BigDecimalUtils.roundDown(usedAssets,4);
        }

        public void setUsedAssets(String usedAssets) {
            this.usedAssets = usedAssets;
        }

        public String getTotalAssetsCnyString() {
            return totalAssetsCnyString;
        }

        public void setTotalAssetsCnyString(String totalAssetsCnyString) {
            this.totalAssetsCnyString = totalAssetsCnyString;
        }

        public String getTotalAssetsUsdtString() {
            return totalAssetsUsdtString;
        }

        public void setTotalAssetsUsdtString(String totalAssetsUsdtString) {
            this.totalAssetsUsdtString = totalAssetsUsdtString;
        }

        public String getFrozenAssets() {
            return frozenAssets;
        }

        public void setFrozenAssets(String frozenAssets) {
            this.frozenAssets = frozenAssets;
        }

        public String getBofuAssets() {
            return bofuAssets;
        }

        public void setBofuAssets(String bofuAssets) {
            this.bofuAssets = bofuAssets;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getTbState() {
            return tbState;
        }

        public void setTbState(int tbState) {
            this.tbState = tbState;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getCbState() {
            return cbState;
        }

        public void setCbState(int cbState) {
            this.cbState = cbState;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }
    }
}
