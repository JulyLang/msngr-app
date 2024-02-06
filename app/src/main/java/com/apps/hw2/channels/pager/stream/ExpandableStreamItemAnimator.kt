package com.apps.hw2.channels.pager.stream

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class ExpandableStreamItemAnimator : DefaultItemAnimator() {

    override fun recordPreLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        changeFlags: Int,
        payloads: MutableList<Any>
    ): ItemHolderInfo {
        return if (viewHolder is StreamItemViewHolder) {
            StreamItemInfo().also {
                it.setFrom(viewHolder)
            }
        } else {
            super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
        }
    }

    override fun recordPostLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder
    ): ItemHolderInfo {
        return if (viewHolder is StreamItemViewHolder) {
            StreamItemInfo().also {
                it.setFrom(viewHolder)
            }
        } else {
            super.recordPostLayoutInformation(state, viewHolder)
        }
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        holder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {
        if (preInfo is StreamItemInfo &&
            postInfo is StreamItemInfo &&
            holder is StreamItemViewHolder) {
            ObjectAnimator
                .ofFloat(
                    holder.arrow,
                    View.ROTATION,
                    preInfo.arrowRotation,
                    postInfo.arrowRotation
                )
                .also {
                    it.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            holder.arrow.rotation = postInfo.arrowRotation
                            dispatchAnimationFinished(holder)
                        }
                    })
                    it.start()
                }
        }
        return super.animateChange(oldHolder, holder, preInfo, postInfo)
    }

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
        return true
    }
}