package com.pharhaslo.slo7.fragment.verification

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NavUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.pharhaslo.slo7.MainActivity
import com.pharhaslo.slo7.MainViewModel
import com.pharhaslo.slo7.R
import com.pharhaslo.slo7.databinding.FragmentVerificationBinding
import com.pharhaslo.slo7.util.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class VerificationFragment : Fragment(R.layout.fragment_verification) {
    private lateinit var binding : FragmentVerificationBinding
    private lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableOnBackPressed()
        binding.apply {
            buttonPlayGuest.setOnClickListener {
                findNavController().navigate(R.id.navigation_game)
            }
            viewModel.editTextPhone.observe(viewLifecycleOwner){
                editTextPhone.text = it
            }
            viewModel.phoneMask.observe(viewLifecycleOwner){ phoneMask ->
                editTextPhone.hint = phoneMask.phoneMask
                textViewCountryCode.text = phoneMask.countryCode
                Picasso.get()
                    .load(phoneMask.flagImageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageViewFlag)

                val mask = editTextPhone.hint.toString()
                val hyphenIndexes = mask.indexesOf("-")
                var lastLength = 0
                editTextPhone.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        lastLength = p0?.length ?: 0
                    }

                    override fun afterTextChanged(afterText: Editable?) {

                        try{
                            val isHyphenDelete = lastLength > afterText?.length ?: 0 && afterText?.last() ?: 0 == '-'
                            if(isHyphenDelete) {
                                afterText?.delete(afterText.length -1, afterText.length)
                            }

                            val isHyphenInput = hyphenIndexes.contains(afterText.toString().toCharArray().lastIndex)
                            if(isHyphenInput){
                                afterText?.insert(afterText.length-1, "-")
                            }

                            val isPasteFromClipboard = lastLength + 2 < afterText?.length ?: 0
                            if(isPasteFromClipboard){
                                hyphenIndexes.forEach{
                                    afterText?.insert(it, "-")
                                }
                            }

                        } catch (e: Exception){
                            e.printStackTrace()
                        }

                        if(afterText?.filterNot { it == '-' }?.length == mask.filterNot { it == '-'}.length){
                            confirmAccess(mask)
                        }
                    }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                buttonConfirmAccess.setOnClickListener { confirmAccess(mask) }
            }
        }
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    private fun confirmAccess(mask : String){
        FirebaseAnalytics.getInstance(requireContext()).logEvent("send_sms")

        binding.apply {
            val isNumberCorrect = editTextPhone.text.length == mask.length
            if (isNumberCorrect) {
                val phoneNumber = textViewCountryCode.text.toString().filterNot { it == '+' } +
                        editTextPhone.text.toString().filterNot { it == '-' }

                viewModel.sendSMS(phoneNumber, viewModel.getVisitorId())

                viewModel.messageSent.observe(viewLifecycleOwner) { message ->
                    when (message) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            val bundle = bundleOf("phoneNumber" to phoneNumber)
                            viewModel.editTextPhone.postValue(binding.editTextPhone.editableText)
                            findNavController().navigate(R.id.navigation_verification_enter_code, bundle)
                        }
                        is Resource.Error -> {
                            activity?.toast(message.message)
                            editTextPhone.setText("")
                        }
                    }
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        viewModel.editTextPhone.postValue(binding.editTextPhone.editableText)
        findNavController().clearBackStack(R.id.navigation_game)
        findNavController().navigate(R.id.navigation_verification)
    }


    override fun onStart() {
        super.onStart()
        FirebaseAnalytics.getInstance(requireContext()).logFragment(this)
    }

    private fun disableOnBackPressed() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })
    }

}