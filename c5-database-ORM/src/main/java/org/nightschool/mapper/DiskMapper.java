package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface DiskMapper {

    @Select("select * from disks order by id")
    public List<Disk> getDisks();

    @Insert("insert into disks(name,img_url,description,price,count) values(#{name}, #{img_url}, #{description}, #{price}, #{count})")
    public void insertDisk(Disk disk);

    @Update("update disks set name=#{name}, img_url=#{img_url, description=#{description}, price=#{price}, count=#{count}}where id=#{id}")
    public void updateDisk(Disk disk);

    @Select("select * from disks where name=#{name}")
    public List<Disk> getDiskByName(String name);

    @Delete("delete from disks where name=#{name}")
    public void deleteDiskByName(@Param("name") String name);

    @Delete("delete from disks where id=#{index}")
    public void deleteDiskByIndex(int index);

    @Update("update disks set count = #{count} where id = #{id}")
    public void setCountByName(@Param("id") int id, @Param("count") int count);
}
