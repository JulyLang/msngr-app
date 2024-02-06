package com.apps.hw2.channels.pager.stream.helper

import androidx.annotation.CheckResult
import com.apps.hw2.channels.pager.stream.IStreamItem
import com.apps.hw2.channels.pager.stream.StreamShimmers
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

object StreamsSearchHelper {
    private const val INPUT_TIMEOUT = 200L
    private const val EMPTY_SEARCH = ""
    private val searchSubject: Subject<String> = BehaviorSubject.createDefault(EMPTY_SEARCH)

    fun onQueryTextReceived(input: String) {
        searchSubject.onNext(input)
    }

    fun createSearchQueryObservable(): Observable<String> {
        return searchSubject
            .debounce(INPUT_TIMEOUT, TimeUnit.MILLISECONDS)
            .map { input -> removeWhitespaces(source = input) }
            .distinctUntilChanged()
    }

    @CheckResult
    private fun removeWhitespaces(source: String): String {
        return source.filter { !it.isWhitespace() }
    }
}