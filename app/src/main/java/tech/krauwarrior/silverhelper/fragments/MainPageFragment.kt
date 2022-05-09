package tech.krauwarrior.silverhelper.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.krauwarrior.silverhelper.R
import tech.krauwarrior.silverhelper.databinding.FragmentMainPageBinding
import tech.krauwarrior.silverhelper.libs.HAppCaller


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private lateinit var hAppCaller: HAppCaller

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        hAppCaller = HAppCaller()
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
            // 打开健康码URL
            openUrl("https://ur.alipay.com/3pBRbxfnGJKk2TlogSuZJq")
        }

        // Payment Code / 付款码
        binding.buttonMainPaymentQrcode.setOnClickListener {
            openApp(HAppCaller.getPkgAlipay())
        }

        // Anti Fraud / 国家反诈中心
        binding.buttonMainAntiFraud.setOnClickListener {
            openApp(HAppCaller.getPkgAntiFraud())
        }

        // Shopping / 购物
        binding.buttonMainShopping.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_shoppingFragment)
        }

        // Family / 找亲人
        binding.buttonMainChildren.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_familyFragment)
        }

        // Pandemic Title / 疫情标题
        binding.textViewPandemicTitle.setOnClickListener {
            openUrl("https://www.baidu.com/s?wd=%E7%96%AB%E6%83%85&ie=utf-8")
        }

        // Video Recommendation Title & Image / 视频推荐标题 & 图片
        binding.ImgVideoRecommend.setOnClickListener {
            openUrl("https://www.bilibili.com/video/BV1c34y1X7sr")
        }
        binding.textViewVideosRecommendation.setOnClickListener {
            openUrl("https://www.bilibili.com/video/BV1c34y1X7sr")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openUrl (url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun openApp (apkName: String) {
        if (apkName.isEmpty()) {
            return
        }
        val isAppExist = hAppCaller.isApkInstalled(requireContext(), apkName)
        if (isAppExist) {
            hAppCaller.callAppDirectly(requireContext(), apkName)
        } else {
            hAppCaller.showInAppMarket(requireContext(), apkName)
        }
    }
}