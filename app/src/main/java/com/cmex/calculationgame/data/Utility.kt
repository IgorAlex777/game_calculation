package com.cmex.calculationgame.data


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmex.calculationgame.R


import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar


fun AppCompatActivity.utilOpenFragment(fragment: Fragment,name: String?){
    if(supportFragmentManager.fragments.isNotEmpty()){
        if(supportFragmentManager.fragments[0].javaClass==fragment.javaClass){
            return
        }
    }
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
        .replace(R.id.container,fragment)
        .addToBackStack(name)
        .commit()
}

 fun Fragment.utilOpenFragment(fragment: Fragment,name:String?){
     myLog("name=$name")
     (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
         .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
         .replace(R.id.container,fragment)
         .addToBackStack(name)
         .commit()
 }

fun Fragment.myToast(string: String) {
    Toast.makeText(activity, string, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.myToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Fragment.myToastLong( string: String) {
    Toast.makeText(activity, string, Toast.LENGTH_LONG).show()
}
fun AppCompatActivity.myToastLong( string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}


fun utilCheckingPermission(context: Context, permission: String): Boolean {
    return when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(context, permission) -> true
        else -> false
    }
}

fun myLog(text: String) {
    Log.d("CMEX", text)
}

fun utilUriPermission(uriImage: Uri, context: Context) {
    context.contentResolver.takePersistableUriPermission(
        uriImage,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )
}

fun utilGetTime(timeInMileSec: Long): String {
    val timeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMileSec
    // timeFormat.timeZone= TimeZone.getTimeZone("")
    return timeFormat.format(calendar.time)
}

fun utilOnSnack(context: Context, view: View, textSnack: String) {
    val behavior = BaseTransientBottomBar.Behavior().apply {
        setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
    }
    //   val sbStyle= ContextThemeWrapper(context,R.style.MySnackBar)
    val snackBar = Snackbar.make(view, textSnack, Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction(utilImageInsertEndText(context, "")) {}
    snackBar.behavior = behavior
    snackBar.setBackgroundTint(context.getColor(R.color.grey))
    val snackBarView = snackBar.view
    snackBarView.setBackgroundResource(R.drawable.fon_layout) //не работает только углы скругляет работает style
    val snackBarTV =
        snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    snackBarTV.textSize = 16F
    snackBarTV.setPadding(150, 0, 0, 0)
    snackBarTV
    snackBarTV.setTextColor(Color.RED)
    val snackBarActionTv =
        snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
    snackBarActionTv.setTextColor(Color.YELLOW)
    snackBar.show()
}

fun utilImageInsertEndText(context: Context, text: String): SpannableStringBuilder {
    val spanText = SpannableStringBuilder()
    spanText.append("$text-")
    spanText.setSpan(
        ImageSpan(context, R.drawable.exit),
        spanText.indexOf("-"),
        spanText.indexOf("-") + 1,
        0
    )
    return spanText
}

