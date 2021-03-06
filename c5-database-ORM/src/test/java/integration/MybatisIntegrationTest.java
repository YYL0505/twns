package integration;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;
import org.nightschool.demo.MybatisUtils;
import org.nightschool.mapper.CartMapper;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public class MybatisIntegrationTest {

    private SqlSession session;

    @Test
    public void shouldGetDiskList() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        DiskMapper mapper = session.getMapper(DiskMapper.class);

        List<Disk> disks = mapper.getDisks();

        assertThat(disks.size(), is(3));

    }

    @Test
    public void shouldInsertDisk() throws Exception {

        session = MybatisUtils.getFactory().openSession();

        DiskMapper mapper = session.getMapper(DiskMapper.class);

        List<Disk> disks = mapper.getDisks();

        int disksLength = disks.size();

        Disk disk = new Disk("AAA", "BBB", "CCC", 1, 2);
        mapper.insertDisk(disk);

        session.commit();

        disks = mapper.getDisks();

        assertThat(disks.size(), is(disksLength + 1));
    }

    @Test
    public void shouldGetDiskByName() throws Exception {

        session = MybatisUtils.getFactory().openSession();

        DiskMapper mapper = session.getMapper(DiskMapper.class);

        List<Disk> disks = mapper.getDiskByName("AAA");

        assertThat(disks.get(0).getName(), is("AAA"));
    }

    @Test
    public void shouldUpdateDiskName() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        DiskMapper mapper = session.getMapper(DiskMapper.class);

        mapper.updateDisk("AAA", "aaa");

        session.commit();

        List<Disk> disks = mapper.getDiskByName("aaa");

        assertThat(disks.get(0).getName(), is("aaa"));
    }

    @Test
    public void shouldDeleteDiskByName() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        DiskMapper mapper = session.getMapper(DiskMapper.class);

        mapper.deleteDiskByName("aaa");

        session.commit();

        List<Disk> disks = mapper.getDiskByName("aaa");

        assertThat(disks.size(), is(0));
    }

    @Test
    public void shouldGetDisksFromCart() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        CartMapper mapper = session.getMapper(CartMapper.class);

        ArrayList<Disk> disks = mapper.getCart();

        assertThat(disks.size(), is(2));
    }

    @Test
    public void shouldAddDiskToCart() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        CartMapper mapper = session.getMapper(CartMapper.class);

        ArrayList<Disk> disks = mapper.getCart();

        int length = disks.size();

        Disk disk = new Disk("AAA", "BBB", "CCC", 1, 2);

        mapper.insertDisk(disk);

        session.commit();

        disks = mapper.getCart();

        assertThat(disks.size(), is(length + 1));
    }

    @Test
    public void shouldUpdateDiskCountByName() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        CartMapper mapper = session.getMapper(CartMapper.class);

        mapper.updateDiskCountByName("AAA", 200);

        session.commit();

        ArrayList<Disk> disks = mapper.getCart();

        assertThat(disks.get(disks.size() - 1).getCount(), is(200));

    }

    @Test
    public void shouldDeleteDiskFromCart() throws Exception {
        session = MybatisUtils.getFactory().openSession();

        CartMapper mapper = session.getMapper(CartMapper.class);

        ArrayList<Disk> disks = mapper.getCart();

        int length = disks.size();

        mapper.deleteDiskByName("AAA");
        session.commit();

        disks = mapper.getCart();

        assertThat(disks.size(), is(length - 1));
    }

    @After
    public void tearDown() throws Exception {
//        session.rollback();
    }
}
