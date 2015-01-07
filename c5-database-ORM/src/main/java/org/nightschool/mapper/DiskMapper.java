package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface DiskMapper {

    @Select("select * from item")
    public List<Disk> getDisks();

    @Insert("insert into item(name,img_url,description,price,count) values(#{name}, #{imgUrl}, #{desc}, #{price}, #{number})")
    public void insertDisk(Disk disk);

    @Update("update item set name=#{newName} where name=#{oldName}")
    public void updateDisk(@Param("oldName") String oldName, @Param("newName") String newName);

    @Select("select * from item where name=#{name}")
    public List<Disk> getDiskByName(String name);

    @Delete("delete from item where name=#{name}")
    public void deleteDiskByName(String name);
}
