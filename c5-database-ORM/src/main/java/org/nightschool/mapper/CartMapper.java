package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface CartMapper {

    @Select("select * from cart")
    ArrayList<Disk> getCart();

    @Insert("insert into cart(name,img_url,description,price,count) values(#{name}, #{img_url}, #{description}, #{price}, #{count})")
    void insertDisk(Disk disk);

    @Update("update cart set count = #{count} where id = #{id}")
    void setDisksNumber(@Param("id")int id, @Param("count")int count);

    @Delete("delete from cart where name = #{name}")
    void deleteDiskByName(String name);

    @Update("update cart set count = #{count} where name = #{name}")
    void updateDiskCountByName(@Param("name")String name, @Param("count")int count);
}
