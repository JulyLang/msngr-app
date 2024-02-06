package com.apps.hw2.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.bottomsheet.adapter.EmojiBottomSheetAdapter
import com.apps.hw2.bottomsheet.adapter.EmojiClickListener
import com.apps.hw2.emoji.EmojiItem
import com.apps.hw2.emoji.Emojis
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetDialog : BottomSheetDialogFragment(), EmojiClickListener {

    private val emojis = Emojis.emojis
    private val emojisAdapter = EmojiBottomSheetAdapter(emojis, this)

    override fun getTheme() = R.style.EmojiBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modal_emoji_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emojiRV: RecyclerView = requireView().findViewById(R.id.emojiRecyclerView)
        emojiRV.layoutManager = GridLayoutManager(context, 7)
        emojiRV.adapter = emojisAdapter
        emojisAdapter.notifyDataSetChanged()
    }

    override fun onClick(emojiItem: EmojiItem) {
        parentFragmentManager.setFragmentResult(RESULT, Bundle()
            .apply { putParcelable(EMOJI_RESULT_KEY, emojiItem) }
        )
        dismiss()
    }

    companion object {
        const val TAG = "EmojiBottomSheetDialog"
        const val RESULT = "${TAG}_RESULT"
        const val EMOJI_RESULT_KEY = "${TAG}_EMOJI_RESULT_KEY"
    }
}