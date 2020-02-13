import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmModel
import io.realm.RealmResults


/**
 * This class represents a RealmResults wrapped inside a LiveData.
 *
 * Realm will always keep the RealmResults up-to-date whenever a change occurs on any thread,
 * and when that happens, the observer will be notified.
 *
 * The RealmResults will be observed until it is invalidated - meaning all local Realm instances on this thread are closed.
 *
 * @param <T> the type of the RealmModel
</T> */
class LiveRealmResults<T : RealmModel?> @MainThread constructor(results: RealmResults<T>) :
    LiveData<List<T>?>() {
    private val results: RealmResults<T>
    // The listener will notify the observers whenever a change occurs.
// The results are modified in change. This could be expanded to also return the change set in a pair.
    private val listener =
        OrderedRealmCollectionChangeListener<RealmResults<T>> { results, changeSet ->
            this@LiveRealmResults.value = results
        }
    // We should start observing and stop observing, depending on whether we have observers.
    /**
     * Starts observing the RealmResults, if it is still valid.
     */
    override fun onActive() {
        super.onActive()
        if (results.isValid) { // invalidated results can no longer be observed.
            results.addChangeListener(listener)
        }
    }

    /**
     * Stops observing the RealmResults.
     */
    override fun onInactive() {
        super.onInactive()
        if (results.isValid) {
            results.removeChangeListener(listener)
        }
    }

    init {
        requireNotNull(results) { "Results cannot be null!" }
        require(results.isValid) { "The provided RealmResults is no longer valid, the Realm instance it belongs to is closed. It can no longer be observed for changes." }
        this.results = results
        if (results.isLoaded) { // we should not notify observers when results aren't ready yet (async query).
// however, synchronous query should be set explicitly.
            value = results
        }
    }
}