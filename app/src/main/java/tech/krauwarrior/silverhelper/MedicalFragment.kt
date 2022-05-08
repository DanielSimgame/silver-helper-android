package tech.krauwarrior.silverhelper

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import tech.krauwarrior.silverhelper.databinding.FragmentMedicBinding
import tech.krauwarrior.silverhelper.helpers.HPermission

class MedicalFragment : Fragment() {
    private lateinit var binding: FragmentMedicBinding

    private lateinit var hPermission: HPermission
    private lateinit var alertDialog: AlertDialog.Builder

    /**
     * Call the phone number
     * @author Changming Mo
     * @param phoneNumber The phone number to call
     * */
    private fun makeDial(phoneNumber: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    /**
     * Generate a TextView dynamically, with the given text.
     * @author Changming Mo
     * @since 2022-05-08
     * @param text The text to be displayed
     * @return View A TextView to be appended
     * */
    fun makeTextView(text: String): View? {
        val textView = TextView(requireContext())
        textView.width = ViewGroup.LayoutParams.MATCH_PARENT
        textView.height = ViewGroup.LayoutParams.WRAP_CONTENT
        textView.text = text
        textView.textSize = 30f
        textView.setTextColor(resources.getColor(R.color.black))
        // Set the text view's margin

//        textView.layoutParams =
//            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        val lp = textView.layoutParams as LinearLayout.LayoutParams
//        lp.setMargins(0, 0, 0, 10)
//        textView.layoutParams = lp

        return textView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hPermission = HPermission()
        alertDialog = AlertDialog.Builder(requireContext())
        binding = FragmentMedicBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Fix the problem that the text is not displayed
        // loop for 5 times, generate 5 fake textViews
        for (i in 0 until 5) {
            val tv = makeTextView("This is a fake textView $i")
            // Append new textViews into linearLayout medicTipsList
            binding.medicTipsList.addView(tv)
        }

        binding.buttonMedicCallDoctor.setOnClickListener {
            makeDial("027-88426013")
        }

        binding.buttonMedicCallClinic.setOnClickListener {
            makeDial("027-88426013")
        }
    }

}