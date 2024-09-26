package com.example.e_commerce_route_c40.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.e_commerce_route_c40.R

abstract class BaseFragment<VB:ViewBinding,
        VM: BaseViewModel> :Fragment(){
    private var _binding :VB? = null

    val binding : VB get() = _binding!!

    lateinit var viewModel:VM

    abstract fun initViewModel():VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    private fun observeMessage() {
        viewModel.uiMessage.observe(viewLifecycleOwner){message->
            handleMessage(message)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMessage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,
            getLayoutId(),
            container,
            false)
        Log.d("ghj", binding.toString())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun getLayoutId():Int

    fun showDialog(
        message: String,
        posText: String? = null,
        posAction: OnDialogClick? = null,
        negText: String? = null,
        negAction: OnDialogClick? = null,
        isCancelable: Boolean = true,
    ) {
        val builder =
            AlertDialog
                .Builder(activity)
                .setMessage(message)
        posText?.let {
            builder.setPositiveButton(
                posText,
            ) { dialog, i ->
                dialog.dismiss()
                posAction?.onClick()
            }
        }
        negText?.let {
            builder.setPositiveButton(
                negText,
            ) { dialog, i ->
                dialog.dismiss()
                negAction?.onClick()
            }
        }

        builder.setCancelable(isCancelable)

        builder.show()
    }

    fun showDialog(
        @StringRes message: Int,
        @StringRes posText: Int? = null,
        posAction: OnDialogClick? = null,
        @StringRes negText: Int? = null,
        negAction: OnDialogClick? = null,
        isCancelable: Boolean = true,
    ) {
        val builder =
            AlertDialog
                .Builder(activity)
                .setMessage(message)
        posText?.let {
            builder.setPositiveButton(
                posText,
            ) { dialog, i ->
                dialog.dismiss()
                posAction?.onClick()
            }
        }
        negText?.let {
            builder.setPositiveButton(
                negText,
            ) { dialog, i ->
                dialog.dismiss()
                negAction?.onClick()
            }
        }

        builder.setCancelable(isCancelable)

        builder.show()
    }

    var loadingDialoge :ProgressDialog?=null
    fun showLoadingDialog(
        @StringRes messageId: Int,
        isCancelable: Boolean = true,
    ) {
       showLoadingDialog(getString(messageId),isCancelable)
    }

    fun showLoadingDialog(
        message: String,
        isCancelable: Boolean = true,
    ) {
        loadingDialoge = ProgressDialog(activity)
        loadingDialoge?.setMessage(message)
        loadingDialoge?.setCancelable(isCancelable)
        loadingDialoge?.show()
    }
    fun hideLoading() {
        loadingDialoge?.dismiss()
    }

    fun handleMessage(uiMessage: UIMessage){
        if(uiMessage.showLoading == false){
            hideLoading()
        }

        if(uiMessage.showLoading == true){
            uiMessage.message?.let {
                showLoadingDialog(message = uiMessage.message,
                    isCancelable = uiMessage.isCancelable)
            }
            uiMessage.messageId?.let {
                showLoadingDialog(messageId = uiMessage.messageId,
                    isCancelable = uiMessage.isCancelable)
            }
        }

        if(uiMessage.showMessage == true)
            showMessage(uiMessage)
    }

    private fun showMessage(uiMessage: UIMessage) {

            showDialog(
                message = uiMessage.message ?:
                if(uiMessage.messageId != null)
                    getString(uiMessage.messageId )
                else getString(R.string.somethin_went_wrong),

                posText = uiMessage.posButtonText ?:
                if(uiMessage.posButtonId !=null )
                    getString(uiMessage.posButtonId)
                else  null,
                posAction = uiMessage.onPosClick,

                negText = uiMessage.negButtonText ?:
                if(uiMessage.negButtonId !=null )
                    getString(uiMessage.negButtonId)
                else  null,

                negAction = uiMessage.onNegClick,

                isCancelable = uiMessage.isCancelable
            )

    }

}