package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface DiskMapper {

    @Select("select * from disks")
    public List<Disk> getDisks();

    @Insert("insert into disks(name,img_url,description,price,count) values(#{name}, #{img_url}, #{description}, #{price}, #{count})")
    public void insertDisk(Disk disk);

    @Update("update disks set name=#{newName} where name=#{oldName}")
    public void updateDisk(@Param("oldName") String oldName, @Param("newName") String newName);

    @Select("select * from disks where name=#{name}")
    public List<Disk> getDiskByName(String name);

    @Delete("delete from disks where name=#{name}")
    public void deleteDiskByName(@Param("name") String name);

    @Delete("delete from disks where id=#{index}")
    public void deleteDiskByIndex(int index);
}
