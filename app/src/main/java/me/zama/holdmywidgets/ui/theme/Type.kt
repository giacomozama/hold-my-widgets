package me.zama.holdmywidgets.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.zama.holdmywidgets.R

val IbmPlexSans = FontFamily(
    Font(R.font.ibm_plex_sans_regular, weight = FontWeight.Normal),
    Font(R.font.ibm_plex_sans_bold, weight = FontWeight.Bold)
)

val RedHatMono = FontFamily(
    Font(R.font.red_hat_mono_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(fontFamily = IbmPlexSans, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    h2 = TextStyle(fontFamily = RedHatMono, fontWeight = FontWeight.Bold, fontSize = 18.sp),
    h3 = TextStyle(fontFamily = IbmPlexSans, fontWeight = FontWeight.Bold, fontSize = 18.sp),
    h4 = TextStyle(fontFamily = IbmPlexSans, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    button = TextStyle(fontFamily = RedHatMono, fontWeight = FontWeight.Bold, fontSize = 16.sp)
)