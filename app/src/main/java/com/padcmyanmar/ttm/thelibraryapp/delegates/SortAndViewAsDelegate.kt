package com.padcmyanmar.ttm.thelibraryapp.delegates

interface SortAndViewAsDelegate {

    fun callbackViewAsFunc(checkedViewAsRadioButtonText:String)
    fun callbackSortFunc(checkedViewAsRadioButtonText:String)

    fun callViewAsBottomSheetDialog(changeListViewType: String)
    fun callSortByBottomSheetDialog(changeSortType: String)
    fun callFilterByCategory(chipNameList: ArrayList<String>)


}