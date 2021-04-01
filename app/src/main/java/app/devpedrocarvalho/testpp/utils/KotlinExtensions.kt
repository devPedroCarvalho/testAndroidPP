package app.devpedrocarvalho.testpp

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImage(imageUrl: String,
              imageView: ImageView,
              context: View
) {
    Glide.with(context)
        .load(imageUrl)
        .error(R.drawable.ic_profile_empty)
        .circleCrop()
        .into(imageView)
}