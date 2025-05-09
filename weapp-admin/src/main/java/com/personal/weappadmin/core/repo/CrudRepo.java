package com.personal.weappadmin.core.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;


public interface CrudRepo<T,PK>{
    public T get(PK id);
    public void add(T t);
    public void update(T t);
    public void remove(PK id);
    public List<T> find(PageBounds pageBounds);
    public void flushCache();

    void updateId(Long id);
}
