package tech.krauwarrior.silverhelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import tech.krauwarrior.silverhelper.databinding.FragmentTransportBinding

class TransportFragment : Fragment() {
    private lateinit var binding: FragmentTransportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransportBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
//        var animation: TranslateAnimation? = null
//        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
//            animation = if (enter) {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 1.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f
//                )
//            } else {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, -1.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f
//                )
//            }
//        } else if (transit == FragmentTransaction.TRANSIT_FRAGMENT_CLOSE) {
//            animation = if (enter) {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, -1.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f
//                )
//            } else {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 1.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f
//                )
//            }
//        }
//
//        if (animation == null) {
//            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f)
//        }
//        animation.duration = 300
//
////        return super.onCreateAnimation(transit, enter, nextAnim)
//        return animation
//    }
}