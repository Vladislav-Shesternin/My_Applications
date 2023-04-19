package com.pharhaslo.slo7.fragment.verification

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.analytics.FirebaseAnalytics
import com.pharhaslo.slo7.MainActivity
import com.pharhaslo.slo7.MainViewModel
import com.pharhaslo.slo7.R
import com.pharhaslo.slo7.databinding.FragmentVerificationEnterCodeBinding
import com.pharhaslo.slo7.util.Resource
import com.pharhaslo.slo7.util.logEvent
import com.pharhaslo.slo7.util.logFragment
import com.pharhaslo.slo7.util.toast
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class VerificationEnterCodeFragment
    : Fragment(R.layout.fragment_verification_enter_code){
    private lateinit var binding : FragmentVerificationEnterCodeBinding
    private lateinit var viewModel: MainViewModel
    private val args:  VerificationEnterCodeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationEnterCodeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableOnBackPressed()
        with(binding){
            codeInputView.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI

            codeInputView.addOnCompleteListener { codeInput ->
                FirebaseAnalytics.getInstance(requireContext()).logEvent("send_code")
                val sharedPrefs =  requireActivity().getSharedPreferences("save", 0)
                val visitorId = sharedPrefs.getString("visitor_id", "") ?: ""

                buttonResendCode.isClickable = false
                buttonResendCode.isFocusable = false
                val preparedPhoneNumber = viewModel.phoneMask.value?.countryCode?.filterNot { it == '+' } +
                        viewModel.editTextPhone.value.toString().filterNot { it == '-' }

                Log.d("testretest", preparedPhoneNumber)
                viewModel.isSMSCodeValid(codeInput, preparedPhoneNumber, visitorId)

                viewModel.codeVerified.observe(viewLifecycleOwner){ code ->
                    when(code){
                        is Resource.Loading ->{
                            buttonResendCode.isClickable = false
                            buttonResendCode.isFocusable = false
                        }

                        is Resource.Success ->{
                            viewModel.saveUser()
                            activity?.toast(getString(R.string.registration_success))
                            sharedPrefs.edit().putBoolean("phoneChecked", true).apply()
                            view.findNavController().navigate(R.id.navigation_main)
                        }

                        is Resource.Error -> {
                            buttonResendCode.isClickable = true
                            buttonResendCode.isFocusable = true
                            codeInputView.code = ""
                            codeInputView.setEditable(true)

                            activity?.toast(code.message)
                        }
                    }
                }
            }

            buttonBackToVerification.setOnClickListener {
                view.findNavController().navigate(R.id.navigation_verification)
            }

            buttonResendCode.setOnClickListener {
                viewModel.sendSMS( args.phoneNumber, viewModel.getVisitorId())
                Toast.makeText(requireContext(), getString(R.string.code_sent), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun disableOnBackPressed() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        findNavController().clearBackStack(R.id.navigation_game)
        findNavController().navigate(R.id.navigation_verification_enter_code)
    }

    override fun onStart() {
        super.onStart()
        FirebaseAnalytics.getInstance(requireContext()).logFragment(this)
    }

}