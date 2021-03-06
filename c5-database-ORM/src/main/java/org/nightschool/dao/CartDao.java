package org.nightschool.dao;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.demo.MybatisUtils;
import org.nightschool.mapper.CartMapper;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/26/14.
 */
public class CartDao {
    private ArrayList<Disk> cart = new ArrayList<>();

    private SqlSession session;
    private CartMapper cartMapper;

    public CartDao() {
        session = MybatisUtils.getFactory().openSession();
        cartMapper = session.getMapper(CartMapper.class);
    }


    public void add(Disk disk, String user_name) {

        try {
            cart = cartMapper.getCart(user_name);
            int i = 0;
            for(i = 0; i < cart.size(); i++)
                if(cart.get(i).getName().equals(disk.getName()))
                    break;

            if(i == cart.size())
                cartMapper.insertDisk(disk.getName(), disk.getPrice(), disk.getCount(), user_name);
            else
                cartMapper.setDisksNumber(disk.getName(), user_name, cart.get(i).getCount() + disk.getCount());

        } catch (Exception e) {
            System.out.println(e);
            session.rollback();
        } finally {
            session.commit();
        }

    }

    public ArrayList<Disk> cartList(String user_name) {

        try {
            cart = cartMapper.getCart(user_name);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }

        return cart;
    }

    public void removeDisk(int id) {
        try {
            cartMapper.removeDisk(id);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }
}
