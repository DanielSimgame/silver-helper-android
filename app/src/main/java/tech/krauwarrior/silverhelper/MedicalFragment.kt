package tech.krauwarrior.silverhelper

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.krauwarrior.silverhelper.helpers.HPermission
import tech.krauwarrior.silverhelper.databinding.FragmentMedicBinding

class MedicalFragment : Fragment() {
    private lateinit var binding: FragmentMedicBinding

    private lateinit var hPermission: HPermission
    private lateinit var alertDialog: AlertDialog.Builder

    private fun makeDial(phoneNumber: String) {
        if (hPermission.checkPermission(requireContext(), HPermission.PERMISSION_CALL_PHONE)) {
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        } else {
            hPermission.requestPermission(
                requireActivity(),
                HPermission.PERMISSION_CALL_PHONE,
                HPermission.Request_CALL_PHONE
            )
            makeDial(phoneNumber)
        }
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

        binding.buttonMedicCallDoctor.setOnClickListener {
            makeDial("027-88426013")
        }

        binding.buttonMedicCallClinic.setOnClickListener {
            makeDial("027-88426013")
        }
    }

}