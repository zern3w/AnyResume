package com.testanymind.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.imageview.ShapeableImageView


fun View.getCollapseAnimation(): Animation {
    val initialHeight = measuredHeight
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation?
        ) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }

        override fun cancel() {
            super.cancel()
            visibility = View.GONE
        }
    }

    animation.duration = 200
    return animation
}

fun View.getExpandAnimation(): Animation {
    measure(
        View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST)
    )
    val targetHeight = measuredHeight

    layoutParams.height = 1
    visibility = View.VISIBLE
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            transformation: Transformation?
        ) {
            layoutParams.height =
                if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }

        override fun cancel() {
            super.cancel()
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }

    animation.duration = 200
    return animation
}

@BindingAdapter("add_chips")
fun ChipGroup.addChips(list: List<String>) {
    list.forEach {
        val chip = Chip(this.context).apply {
            text = it
            isClickable = false
            isCheckable = false
            isCloseIconVisible = false
        }
        this.addView(chip)
    }
}

@BindingAdapter("add_stroke_chips")
fun ChipGroup.addStrokeChips(list: List<String>) {
    list.forEach {
        val chip = Chip(this.context).apply {
            text = it
            setChipBackgroundColorResource(android.R.color.white)
            chipStrokeWidth = 1f
            isClickable = false
            isCheckable = false
            isCloseIconVisible = false
        }
        this.addView(chip)
    }
}

@BindingAdapter("glideImageUrl", "glidePlaceholder")
fun setImage(image: ShapeableImageView, url: String, placeHolder: Drawable) {

    if (url.isNotEmpty()) {
        Glide.with(image.context)
            .load(url)
            .into(image)
    } else {
        image.apply {
            val res = image.context.resources
            val contentPadding = res.getDimensionPixelSize(R.dimen.spacing_xsmall)
            setContentPadding(
                contentPadding,
                contentPadding,
                contentPadding,
                contentPadding
            )
            setImageDrawable(placeHolder)
            imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(image.context, R.color.grey_400))
        }

    }
}

fun ShapeableImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}