package com.recipess.app.core.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun ImageView.setImageSrcFromUrl(url: String?, placeholder:Int, context: Context) {
    Glide.with(context).load(url)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.setImageSrcFromDrawable(drawable: Int, context: Context) {
    Glide.with(context).load(drawable).into(this)
}

fun TextView.removeDrawable() {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}

fun View.showKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun TextView.setColor(context: Context, color:Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.underline(text: String) {
    val auxText = SpannableString(text)
    auxText.setSpan(UnderlineSpan(), 0, auxText.length, 0)
    this.text = auxText
}

fun String.upperCaseFirstLetter(): String {
    return this.substring(0, 1).uppercase() + this.substring(1).lowercase()
}

private fun getFormat(minuteOfHour: Int):String {
    when(minuteOfHour) {
        12,13,14,15,16,17,18,19,20,21,22,23 -> { return "PM"}
        0,1,2,3,4,5,6,7,8,9,10,11 -> { return "AM" }
        else -> { return "PM" }
    }
}
