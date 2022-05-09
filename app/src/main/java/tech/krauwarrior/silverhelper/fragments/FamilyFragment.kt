package tech.krauwarrior.silverhelper.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.krauwarrior.silverhelper.databinding.FragmentFamilyBinding
import tech.krauwarrior.silverhelper.libs.HPermission

class FamilyFragment : Fragment() {
    private lateinit var binding: FragmentFamilyBinding

    private lateinit var hPermission: HPermission
    private lateinit var alertDialog: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFamilyBinding.inflate(inflater, container, false)

        hPermission = HPermission()
        alertDialog = AlertDialog.Builder(requireContext())
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}