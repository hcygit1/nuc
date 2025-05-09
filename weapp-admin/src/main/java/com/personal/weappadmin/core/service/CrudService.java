package com.personal.weappadmin.core.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.personal.weappadmin.core.repo.CrudRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CrudService<Repo extends CrudRepo<M,PK>,M,PK> {
    @Autowired
    protected Repo curdRepo;

    public M get(PK id){
        return curdRepo.get(id);
    }

    public List<M> find(PageBounds pageBounds){
        return curdRepo.find(pageBounds);
    }

    public void add(M m){
        curdRepo.add(m);
    }

    public void update(M m){
        curdRepo.update(m);
    }

    public void remove(PK id){
        curdRepo.remove(id);
    }

    public void flushCache() {
        curdRepo.flushCache();
        refresh();
    }

    public void refresh() {

    }
}
