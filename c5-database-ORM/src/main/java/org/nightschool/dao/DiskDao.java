package org.nightschool.dao;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.demo.MybatisUtils;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;

import java.util.ArrayList;

/**
 * Created by Thoughtworks on 12/26/14.
 */
public class DiskDao {
    private SqlSession session;
    private DiskMapper diskMapper;


    public DiskDao() {
        session = MybatisUtils.getFactory().openSession();
        diskMapper = session.getMapper(DiskMapper.class);
    }

    public ArrayList<Disk> listDisks() {
        ArrayList<Disk> disks = new ArrayList<>();

        try {
            disks = (ArrayList)diskMapper.getDisks();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
        return disks;
    }

    public void add(Disk disk) {

        try {
            diskMapper.insertDisk(disk);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }

    public void remove(String name) {
        try {
            diskMapper.deleteDiskByName(name);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }

    public ArrayList<Disk> getDisksByName(String name) {
        ArrayList<Disk> disks = new ArrayList<>();

        try {
            disks = (ArrayList<Disk>)diskMapper.getDiskByName(name);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }

        return disks;
    }

    public void setCount(int id, int count) {
        try {
            diskMapper.setCountByName(id, count);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }

    public void updateDisk(Disk disk) {
        try {
            diskMapper.updateDisk(disk);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }
}
