package com.real_estate.lead_grow.ui.loan_calculator

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.real_estate.lead_grow.R
import com.real_estate.lead_grow.databinding.LoanCalculatorFragment2Binding
import kotlin.math.pow

class Loan_CalculatorFragment : Fragment() {


    private lateinit var viewModel: LoanCalculatorViewModel
    private lateinit var binding: LoanCalculatorFragment2Binding

    private var principal: Double = 0.00
    private var intrest: Double = 0.00
    private var time: Double = 0.00
    private var EMI : Double = 0.00
    private var totalIntrest = 0.00
    private var totalPayment = 0.00

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.loan__calculator_fragment2, container, false)
        viewModel = ViewModelProviders.of(this).get(LoanCalculatorViewModel::class.java)


        binding.resetFields.setOnClickListener {
            binding.loanAmount.text.clear()
            binding.intrestValue.text.clear()
            binding.yearsValue.text.clear()
            binding.monthValue.text.clear()
            binding.EMIShow.text = "-"
            binding.IntrestShow.text = "-"
            binding.totalAmountShow.text = "-"
        }


        binding.calculateEMI.setOnClickListener {

            if(binding.loanAmount.text.isEmpty() || binding.intrestValue.text.isEmpty() || (binding.yearsValue.text.isEmpty() && binding.monthValue.text.isEmpty()) )
                Toast.makeText(context , "Please enter all fields" , Toast.LENGTH_SHORT).show()
            else{
                principal = binding.loanAmount.text.toString().toDouble()
                intrest = binding.intrestValue.text.toString().toDouble()
                var months = 0.0

                if(binding.yearsValue.text.isNotEmpty())
                    months += binding.yearsValue.text.toString().toDouble()*12
                if(binding.monthValue.text.isNotEmpty())
                    months += binding.monthValue.text.toString().toDouble()

                time = months

                binding.EMIShow.text =  String.format("%.2f", getEMI(principal , intrest/12 , time) )
                binding.IntrestShow.text = String.format("%.2f", getIntrest() )
                binding.totalAmountShow.text = String.format("%.2f", getTotalAmt() )
            }


        }

        return binding.root
    }

    private fun getTotalAmt(): Double {
        totalPayment = principal + totalIntrest
        return totalPayment
    }

    private fun getIntrest(): Double {
        totalIntrest =  EMI*time - principal

        return totalIntrest
    }

    private fun getEMI(p: Double, r: Double, n: Double): Double {

        val step =  (1+r/100).pow(n)


        val emi =  (p *  (r/100) * step ) / (step-1)

        EMI = emi

        return emi
    }


}
