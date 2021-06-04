package ge.space.extensions

import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import androidx.core.widget.TextViewCompat

fun TextView.setAutoSizeWithWrapContent(
    title: String?,
    minSizeDimen: Int,
    maxSizeDimen: Int,
    granularity: Int = 1,
    onTextSizeChanged: (() -> Unit)? = null
){
    if(Build.VERSION.SDK_INT < 26) {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE)

        this.text = title
        this.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(maxSizeDimen))

        this.post {
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                this,
                resources.getDimensionPixelSize(minSizeDimen),
                resources.getDimensionPixelSize(maxSizeDimen),
                granularity, TypedValue.COMPLEX_UNIT_PX
            )
            onTextSizeChanged?.invoke()
        }
    }
    else{
        this.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_NONE)

        this.text = title
        this.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(maxSizeDimen))

        this.post {
            this.setAutoSizeTextTypeUniformWithConfiguration(
                resources.getDimensionPixelSize(minSizeDimen),
                resources.getDimensionPixelSize(maxSizeDimen),
                granularity, TypedValue.COMPLEX_UNIT_PX
            )
            onTextSizeChanged?.invoke()
        }
    }
}

fun TextView.setTextStyle(styleRes : Int) {
    if (Build.VERSION.SDK_INT < 23)
        TextViewCompat.setTextAppearance(this, styleRes)
    else
        this.setTextAppearance(styleRes)
}