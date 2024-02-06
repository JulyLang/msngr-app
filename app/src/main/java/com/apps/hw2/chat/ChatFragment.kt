package com.apps.hw2.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CheckResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.BottomNavigationViewHolder
import com.apps.hw2.R
import com.apps.hw2.app.App
import com.apps.hw2.bottomsheet.EmojiBottomSheetDialog
import com.apps.hw2.emoji.EmojiItem
import com.apps.hw2.chat.elm.*
import com.apps.hw2.chat.paging.LoadNewPageCallback
import com.apps.hw2.chat.paging.PagingScrollListener
import com.apps.hw2.chat.usecase.*
import com.apps.hw2.list.adapter.MessageClickListener
import com.apps.hw2.list.adapter.MessagesAdapter
import com.apps.hw2.list.model.BaseMessage
import com.apps.hw2.list.model.DateMessage
import com.apps.hw2.list.model.IEmojisMessage
import com.apps.hw2.model.EmojiCounter
import com.apps.hw2.util.TimeUtil
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import vivid.money.elmslie.android.base.ElmFragment
import vivid.money.elmslie.core.ElmStoreCompat
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChatFragment : ElmFragment<Event, Effect, State>(), MessageClickListener,
    LoadNewPageCallback {

    private val userChatRV: RecyclerView by lazy { requireView().findViewById(R.id.userChatRV) }
    private val messageInputET: EditText by lazy { requireView().findViewById(R.id.messageInputEditText) }
    private val sendMessageIV: ImageView by lazy { requireView().findViewById(R.id.sendMessageIV) }

    private var messages: List<BaseMessage> = emptyList()
    private var clickedMessage: BaseMessage? = null
    private val rvAdapter by lazy {
        MessagesAdapter(
            context = requireContext(),
            messageClickListener = this
        )
    }

    private var streamId: Int = -1
    private var topicName: String = ""

    @Inject
    lateinit var observeMessagesUseCase: IObserveMessagesUseCase

    @Inject
    lateinit var syncMessagesUseCase: ISyncMessagesUseCase

    @Inject
    lateinit var sendMessageUseCase: ISendMessageUseCase

    @Inject
    lateinit var addEmojiReactionUseCase: IAddEmojiReactionUseCase

    @Inject
    lateinit var deleteEmojiReactionUseCase: IDeleteEmojiReactionUseCase

    private var addOrDeleteEmojiReactionDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        streamId = getStreamId(arguments)
        topicName = getTopicName(arguments)
        (requireActivity().application as App).chatComponent.injectChatFragment(this)
        childFragmentManager.setFragmentResultListener(
            EmojiBottomSheetDialog.RESULT,
            this
        ) { _, bundle ->
            val emojiItem: EmojiItem? =
                bundle.getParcelable(EmojiBottomSheetDialog.EMOJI_RESULT_KEY)

            if (clickedMessage != null && emojiItem != null) {
                onEmojiSelected(
                    clickedMessage!!,
                    emojiName = emojiItem.name,
                    emojiCode = emojiItem.code,
                    selectedFromBottomView = true
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val topicNameTV: TextView = view.findViewById(R.id.topicNameTV)
        topicNameTV.text = requireContext().getString(R.string.topic_name, topicName)
        (requireActivity() as? BottomNavigationViewHolder)?.changeVisibility(View.GONE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as? BottomNavigationViewHolder)?.changeVisibility(View.VISIBLE)
        destroyAddOrDeleteEmojiReactionDisposable()
    }

    override fun loadNewPage() {
        Log.d(TAG, "loadNewPage")
//        store.accept(Event.Ui.LoadNewMessagesPage)//todo restore for paging
    }

    override fun onClick(message: BaseMessage) {
        store.accept(Event.Ui.OnMessageClicked(message))
    }

    override fun onLongClick(message: BaseMessage) {
        store.accept(Event.Ui.OnMessageLongClicked(message))
    }

    override fun onEmojiClick(message: BaseMessage, emojiName: String, emojiCode: String) {
    //    store.accept(Event.Ui.OnEmojiClicked(message))todo use store (ELM)
        onEmojiSelected(clickedMessage = message, emojiName, emojiCode, selectedFromBottomView = false)
    }

    override val initEvent: Event = Event.Ui.Init

    override fun createStore(): Store<Event, Effect, State> {
        return ElmStoreCompat(
            initialState = State(),
            reducer = Reducer(),
            actor = Actor(
                streamId,
                topicName,
                observeMessagesUseCase,
                syncMessagesUseCase,
                sendMessageUseCase
            )
        )
    }

    override fun render(state: State) {
        this.messages = state.messages
        updateMessagesOnScreen(newMessages = addDates(messages))
        if (state.showSendMessagePlane) {
            sendMessageIV.setImageResource(R.drawable.ic_plane)
        } else {
            sendMessageIV.setImageResource(R.drawable.ic_attach)
        }
    }

    override fun handleEffect(effect: Effect): Unit = when (effect) {
        is Effect.OnClick -> {
            Toast.makeText(activity, "onClick ${effect.message}", Toast.LENGTH_SHORT).show()
        }
        is Effect.OnLongClick -> {
            clickedMessage = effect.message
            val dialog = EmojiBottomSheetDialog()
            dialog.show(childFragmentManager, EmojiBottomSheetDialog.TAG)
        }
        is Effect.ShowError -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(activity)
        userChatRV.layoutManager = layoutManager
        userChatRV.adapter = rvAdapter
        rvAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                Log.i(TAG, "onChanged")
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                Log.i(TAG, "onItemRangeChanged positionStart=$positionStart, itemCount=$itemCount")
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                super.onItemRangeChanged(positionStart, itemCount, payload)
                Log.i(
                    TAG,
                    "onItemRangeChanged positionStart=$positionStart, itemCount=$itemCount, payload=$payload"
                )
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                Log.i(TAG, "onItemRangeInserted positionStart=$positionStart, itemCount=$itemCount")
                if (itemCount > 0) scrollToBottom()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                Log.i(TAG, "onItemRangeRemoved positionStart=$positionStart, itemCount=$itemCount")
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                Log.i(
                    TAG,
                    "onItemRangeMoved fromPosition=$fromPosition, toPosition=$toPosition, itemCount=$itemCount"
                )
            }
        })

        userChatRV.addOnScrollListener(
            PagingScrollListener(
                layoutManager = layoutManager,
                loadNewPageCallback = this
            )
        )

        messageInputET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                store.accept(Event.Ui.OnMessageTextChanged(s))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sendMessageIV.setOnClickListener {
            val inputText: String = messageInputET.text.toString()
            if (inputText.isNotBlank()) {
                messageInputET.text.clear()
                sendMessage(inputText)
            }
        }
    }

    @CheckResult
    private fun addDates(messages: List<BaseMessage>): List<BaseMessage> {
        val newMessages: MutableList<BaseMessage> = messages.toMutableList()
        val iterator = newMessages.listIterator()
        var currentDate = 0L
        for (message in iterator) {
            if (TimeUtil.isMoreThanDay(message.dateCreated, currentDate)) {
                if (iterator.hasPrevious()) {
                    iterator.previous()
                    iterator.add(DateMessage(message.dateCreated))
                    iterator.next()
                } else {
                    iterator.add(DateMessage(message.dateCreated))
                }
                currentDate = message.dateCreated
            }
        }
        return newMessages
    }

    private fun updateMessagesOnScreen(
        newMessages: List<BaseMessage>,
        scrollToBottom: Boolean = false
    ) {
        if (scrollToBottom) {
            rvAdapter.submitList(newMessages) {
                userChatRV.post { scrollToBottom() }
            }
        } else {
            rvAdapter.submitList(newMessages)
        }
    }

    private fun sendMessage(inputText: String) {
        store.accept(Event.Ui.SendNewMessage(messageText = inputText))
    }

    private fun onEmojiSelected(
        clickedMessage: BaseMessage,
        emojiName: String,
        emojiCode: String,
        selectedFromBottomView: Boolean
    ) {
        if (clickedMessage is IEmojisMessage) {
            val emojiCounter: EmojiCounter? = clickedMessage.findEmojiCounterByCode(emojiCode)
            val add: Boolean = selectedFromBottomView || emojiCounter?.isSelected != true
            addOrDeleteEmojiReaction(
                messageId = clickedMessage.messageId,
                emojiName = emojiName,
                emojiCode = emojiCode,
                add = add
            )
        }
    }

    private fun addOrDeleteEmojiReaction(
        messageId: Int,
        emojiName: String,
        emojiCode: String,
        add: Boolean
    ) {
        destroyAddOrDeleteEmojiReactionDisposable()
        if (add) {
            addOrDeleteEmojiReactionDisposable = addEmojiReactionUseCase
                .execute(
                    messageId = messageId,
                    emojiName = emojiName,
                    emojiCode = emojiCode,
                    reactionType = "unicode_emoji"
                )
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { Log.d(TAG, "addEmojiReactionUseCase success") },
                    onError = { Log.e(TAG, "addEmojiReactionUseCase error", it) }
                )
        } else {
            addOrDeleteEmojiReactionDisposable = deleteEmojiReactionUseCase
                .execute(
                    messageId = messageId,
                    emojiName = emojiName,
                    emojiCode = emojiCode,
                    reactionType = "unicode_emoji"
                )
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { Log.d(TAG, "deleteEmojiReactionUseCase success") },
                    onError = { Log.e(TAG, "deleteEmojiReactionUseCase error", it) }
                )
        }
    }

    private fun destroyAddOrDeleteEmojiReactionDisposable() {
        addOrDeleteEmojiReactionDisposable?.dispose()
        addOrDeleteEmojiReactionDisposable = null
    }

    private fun scrollToBottom(smooth: Boolean = false) {
        Log.d(TAG, "scrollToBottom smooth=$smooth")
        if (smooth) {
            userChatRV.smoothScrollToPosition(rvAdapter.itemCount - 1)
        } else {
            userChatRV.scrollToPosition(rvAdapter.itemCount - 1)
        }
    }

    companion object {
        private const val STREAM_ID_ARG = "stream_id_arg"
        private const val TOPIC_NAME_ARG = "topic_name_arg"

        const val TAG = "ChatFragment"

        fun newInstance(streamId: Int, topicName: String): ChatFragment {
            val args: Bundle = Bundle().also {
                it.putInt(STREAM_ID_ARG, streamId)
                it.putString(TOPIC_NAME_ARG, topicName)
            }
            return ChatFragment().also { it.arguments = args }
        }

        fun getStreamId(bundle: Bundle?): Int {
            return bundle?.getInt(STREAM_ID_ARG, -1) ?: -1
        }

        fun getTopicName(bundle: Bundle?): String {
            return bundle?.getString(TOPIC_NAME_ARG, "") ?: ""
        }
    }
}