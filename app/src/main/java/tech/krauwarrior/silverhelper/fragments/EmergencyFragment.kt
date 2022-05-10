package tech.krauwarrior.silverhelper.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.krauwarrior.silverhelper.libs.HPermission
import tech.krauwarrior.silverhelper.databinding.FragmentEmergencyBinding

class EmergencyFragment : Fragment() {

    private lateinit var binding: FragmentEmergencyBinding

    private lateinit var hPermission: HPermission

    private fun makeCall(phoneNumber: String) {
        if (hPermission.checkPermission(requireContext(), HPermission.PERMISSION_CALL_PHONE)) {
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        } else {
            hPermission.requestPermission(
                requireActivity(),
                HPermission.PERMISSION_CALL_PHONE,
                HPermission.Request_CALL_PHONE
            )
            makeCall(phoneNumber)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hPermission = HPermission()
        val alertDialog = AlertDialog.Builder(requireContext())
        binding = FragmentEmergencyBinding.inflate(inflater, container, false)

        // on view created, check the CALL_PHONE permission
        if (!hPermission.checkPermission(requireContext(), HPermission.PERMISSION_CALL_PHONE))
        {
            alertDialog.setTitle("需要您同意")
            alertDialog.setMessage("需要您同意直接拨打电话的权限，否则无法使用此功能")
            alertDialog.setPositiveButton("同意") { _, _ ->
                run {
                    hPermission.requestPermission(
                        requireActivity(),
                        HPermission.PERMISSION_CALL_PHONE,
                        HPermission.Request_CALL_PHONE
                    )
                }
            }
            alertDialog.setNegativeButton("拒绝") { _, _ ->
                run {
                    // do nothing
                }
            }
            alertDialog.show()

        }

        binding.buttonEmergency120.setOnClickListener {
            // check permission, if granted, call emergency number, if not, request permission and call emergency number
            makeCall("911")
//            makeCall("120")
        }

        binding.buttonEmergency110.setOnClickListener {
            makeCall("911")
//            makeCall("110")
        }

        binding.buttonEmergency119.setOnClickListener {
            makeCall("911")
//            makeCall("119")
        }

        binding.buttonEmergency112.setOnClickListener {
            makeCall("911")
//            makeCall("112")
        }

//        binding.emergencyButton.setOnClickListener {
//            findNavController().navigate(R.id.action_emergencyFragment_to_emergencyCallFragment)
//        }
        return binding.root
    }
}