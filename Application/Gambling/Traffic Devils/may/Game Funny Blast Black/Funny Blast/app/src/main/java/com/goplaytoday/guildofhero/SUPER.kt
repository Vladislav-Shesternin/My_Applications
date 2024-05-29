package com.goplaytoday.guildofhero

import com.goplaytoday.guildofhero.databinding.ActivityMainBinding
import com.goplaytoday.guildofhero.util.LottieUtil
import com.goplaytoday.guildofhero.util.Once
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object SUPER {

    lateinit var binding : ActivityMainBinding

    lateinit var lottie: LottieUtil

    val onceExit  = Once()
    val coroutine = CoroutineScope(Dispatchers.Default)

}