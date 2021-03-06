package com.example.fb.weather.event;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static String TAG = "RxBus";
    private static RxBus _RxBus;
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    public static RxBus getBus() {
        if (_RxBus == null) {

            _RxBus = new RxBus();

        }
        return _RxBus;
    }


    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }

    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }

    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }

    public <T> Observable<T> register(@NonNull Class<T> clazz) {
        String tag = clazz.getName();
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }

    public void unregister(@NonNull String tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject) observable);
            if (subjects.size() == 0) {
                subjectMapper.remove(tag);
            }
        }


    }


    public void post(@NonNull Object o) {
        post(o.getClass().getName(), o);
    }

    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (subjectList == null)
            return;

        if (subjectList.size() > 0) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
    }
}