package hbm;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.List;


public class HelloHBM01 {
    public static void main(String[] args) {
        // SessionFactiory 초기화
        String fpath = "src/main/resources/mapping/sungjuk.hbm.xml";
        Configuration cfg = new Configuration().configure();    // hibernate.cfg.xml 를 불러온다
        SessionFactory sf = cfg.addFile(fpath).buildSessionFactory();
        Session sess = sf.openSession();

        try {
            // hql을 이용한 조회 (전체 데이터 조회)
            Query query =  sess.createQuery("from SungJuk");
            List sjs = query.list();

            System.out.println(sjs);

            // 이름으로 조회
            query = sess.createQuery("from SungJuk where name = ?1");
            query.setParameter(1, "지현");
            sjs = query.list();

            System.out.println(sjs);



        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            sess.close();
            sf.close();
        }

    }
}
