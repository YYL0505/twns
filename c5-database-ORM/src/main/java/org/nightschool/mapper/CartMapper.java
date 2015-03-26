package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface CartMapper {

    @Select("select id,name,price,count from cart where user_name = #{user_name}")
    ArrayList<Disk> getCart(String user_name);

    @Insert("insert into cart(name,price,count,user_name) values(#{name},  #{price}, #{count}, #{user_name})")
    void insertDisk(@Param("name")String name, @Param("price")double price,@Param("count")int count, @Param("user_name")String user_name);

    @Update("update cart set count = #{count} where name = #{name} and user_name = #{user_name}")
    void setDisksNumber(@Param("name")String name, @Param("user_name")String user_name, @Param("count")int count);

    @Delete("delete from cart where name = #{name}")
    void deleteDiskByName(String name);

    @Update("update cart set count = #{count} where name = #{name}")
    void updateDiskCountByName(@Param("name")String name, @Param("count")int count);

    @Delete("delete from cart where id = #{id}")
    void removeDisk(int id);
}
