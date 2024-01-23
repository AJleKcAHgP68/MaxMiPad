package com.yifeplayte.maxmipadinput.hook.hooks.android

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.maxmipadinput.hook.hooks.BaseHook
import com.yifeplayte.maxmipadinput.hook.utils.XSharedPreferences.getStringSet

object DisableFixedOrientation : BaseHook() {
    private val shouldDisableFixedOrientationList by lazy {
        getStringSet("should_disable_fixed_orientation_list", mutableSetOf())
    }

    override fun init() {
        loadClass("com.android.server.wm.MiuiFixedOrientationController").methodFinder()
            .filterByName("shouldDisableFixedOrientation").first().createHook {
                before {
                    if (it.args[0] in shouldDisableFixedOrientationList) {
                        it.result = true
                    }
                }
            }
    }
}
