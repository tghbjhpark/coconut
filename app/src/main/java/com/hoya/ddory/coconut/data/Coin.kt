package com.hoya.ddory.coconut.data

import androidx.annotation.StringRes
import com.hoya.ddory.coconut.R

enum class Coin(@StringRes val nameResource: Int,) {
    BTC(R.string.name_btc),
    ETH(R.string.name_eth),
    XRP(R.string.name_xrp),
    ADA(R.string.name_ada),
    LINK(R.string.name_link),
    TRX(R.string.name_trx),
    EOS(R.string.name_eos),
    XLM(R.string.name_xlm),
    BCH(R.string.name_bch),
    WEMIX(R.string.name_wemix),
    KLAY(R.string.name_klay)
}
