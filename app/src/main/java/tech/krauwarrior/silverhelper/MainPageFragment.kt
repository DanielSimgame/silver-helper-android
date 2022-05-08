package tech.krauwarrior.silverhelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import tech.krauwarrior.silverhelper.databinding.FragmentMainPageBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 在此处绑定按钮点击事件
        super.onViewCreated(view, savedInstanceState)

        // 设置按钮
        binding.buttonMainSettings.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        // Emergency Button / 报警按钮
        binding.buttonMainEmergencyCall.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_emergencyFragment)
        }

        // Medical Button / 看病按钮
        binding.buttonMainDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_medicalFragment)
        }

        // Transport Button / 出行按钮
        binding.buttonMainTransport.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_transportFragment)
        }

        // Health Code / 健康码
        binding.buttonMainHealthQrcode.setOnClickListener {
            openHealthCode()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openHealthCode () {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https://ur.alipay.com/3pBRbxfnGJKk2TlogSuZJq")
        startActivity(intent)
    }
}