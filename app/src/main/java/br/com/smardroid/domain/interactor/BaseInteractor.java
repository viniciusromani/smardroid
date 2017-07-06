package br.com.smardroid.domain.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.smardroid.domain.executor.ThreadExecutor;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

/**
 * Created by viniciusromani on 05/07/17.
 */

public abstract class BaseInteractor {

    private ThreadExecutor subscriberOn;
    private ThreadExecutor observerOn;
    private List<Disposable> disposables = new ArrayList<>();
    private Flowable flowable;

    /**
     * Constructor
     */
    public BaseInteractor(final ThreadExecutor subscriberOn, final ThreadExecutor observerOn) {
        this.disposables = new ArrayList<>();
        this.subscriberOn = subscriberOn;
        this.observerOn = observerOn;
    }

    protected Flowable withFlowable(final Flowable flowable) {
        this.flowable = flowable;
        return this.flowable;
    }

    /**
     * Subscribing methods
     */
    // Subscribe the subscriber to the registered observable
    public void execute(final DisposableSubscriber subscriber) {
        getDisposable(flowable, subscriber);
    }
    // Subscribe the subscriber to the observable
    public <T> void execute(Flowable<T> flowable, DisposableSubscriber<T> subscriber) {
        getDisposable(flowable, subscriber);
    }
    // Subscribe the onNext action to the observable
    public <T> void execute(Flowable<T> flowable, Consumer<T> onNext) {
        getDisposable(flowable, onNext, message -> Log.d("BaseInteractor", "Message " + message));
    }
    // Subscribe onNext and onError actions to the observable
    public <T> void execute(Flowable<T> flowable, Consumer<T> onNext, Consumer<Throwable> onError) {
        getDisposable(flowable, onNext, onError);
    }

    /**
     * Unsubscribe method
     */
    public void dispose() {
        disposeAll();
    }
    public void disposeAll() {
        if (disposables == null) {
            return;
        }

        Stream.of(disposables)
                .filter(item -> !item.isDisposed())
                .forEach(Disposable::dispose);

        disposables.clear();
    }

    /**
     * Disposable methods (Subscriptions)
     */
    private <T> Disposable getDisposable(Flowable<T> flowable, final Consumer<T> onNext, final Consumer<Throwable> onError) {

        Disposable disposable = flowable
                .subscribeOn(subscriberOn.getScheduler())
                .observeOn(observerOn.getScheduler())
                .subscribe(onNext, onError);

        addDisposable(disposable);
        return disposable;
    }

    private <T> void getDisposable(Flowable<T> flowable, final DisposableSubscriber<T> subscriber) {

        flowable.subscribeOn(subscriberOn.getScheduler())
                .observeOn(observerOn.getScheduler())
                .subscribe(subscriber);
    }

    private void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposables.removeAll(Stream.of(disposables).filter(Disposable::isDisposed).collect(Collectors.toList()));
            disposables.add(disposable);
        }
    }
}
