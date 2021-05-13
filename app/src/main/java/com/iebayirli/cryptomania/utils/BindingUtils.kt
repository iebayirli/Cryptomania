package com.iebayirli.cryptomania.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.base.BaseListAdapter
import com.iebayirli.cryptomania.base.ListAdapterItem
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.model.TimeInterval
import com.iebayirli.cryptomania.service.listeners.IQueryTextChangedListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.floor

@BindingAdapter(value = ["tools:setImage"])
fun setImage(imageView: ShapeableImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty().not()) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["tools:setTextCurrent"])
fun setTextForCurrent(textView: MaterialTextView, text: String?) {
    if (text.isNullOrEmpty().not()) {
        textView.text = "US$ $text "
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["tools:setTextChangePercent"])
fun setTextForChangePercent(textView: MaterialTextView, price: Double?) {
    if (price != null) {
        textView.text = "% ${floor(price * 100) / 100}"
        if (price > 0) {
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.colorGreen))
        } else
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.colorRed))
    }
}

@BindingAdapter(value = ["tools:setFavouriteCondition"])
fun setFavouriteCondition(imageView: ShapeableImageView, coin: Coin?) {
    if (coin != null) {
        if (coin.isFavourite) {
            imageView.setImageResource(R.drawable.ic_favourite)
        } else {
            imageView.setImageResource(R.drawable.ic_favourite_border)
        }
    }
}

@BindingAdapter(value = ["tools:queryTextChanged"])
fun setQueryTextChangeFlow(textInputEditText: TextInputEditText, queryTextChangeListener: IQueryTextChangedListener) {
    val query = MutableStateFlow("")

    textInputEditText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // beforeTextChanged
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            query.value = p0.toString()
            queryTextChangeListener.queryTextChanged(query)
        }

        override fun afterTextChanged(p0: Editable?) {
            // afterTextChanged
        }
    })
}

@BindingAdapter(value = ["tools:setButtonBackground"])
fun setBackgroundForSelection(cardView: MaterialCardView, timeInterval: TimeInterval) {
    if (timeInterval.isSelected) {
        cardView.setBackgroundColor(ContextCompat.getColor(cardView.context, R.color.backgroundSelected))
    } else {
        cardView.setBackgroundColor(ContextCompat.getColor(cardView.context, R.color.backgroundNormal))
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter( value = ["tools:submitList"])
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseListAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.submitList(list)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter( value=["tools:setAdapter"])
fun setAdapter(recyclerView: RecyclerView, adapter: BaseListAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let {
        recyclerView.adapter = it
    }
}


