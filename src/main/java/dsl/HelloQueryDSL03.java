package dsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;
import model.QDepartment;
import model.QEmployee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class HelloQueryDSL03 {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); //데이타 추가 ,삭제 할때는 있어야함

        try {
            // 사원 추가
//            tx.begin();
//            Employee e = new Employee();
//            em.persist(e);
//            tx.commit();

            //사원 수정
            //쿼리 객체 준비
//            QEmployee qemp = QEmployee.employee;
//            JPAQueryFactory query = new JPAQueryFactory(em);
//            tx.begin();
//            query.update(qemp)
//                    .set(qemp.fname, "asd123")
//                    .set(qemp.lname, "789123")
//                    .set(qemp.sal, 1500)
//                    .set(qemp.jobid, "Game_QC")
//                    .where(qemp.empid.eq(207L)) // Long 은 숫자뒤에 L 붙여줌
//                    .execute(); //execute() 실행하기
//            tx.commit();

            // 데이터 삭제
            QEmployee qemp = QEmployee.employee;
            JPAQueryFactory query = new JPAQueryFactory(em);
            tx.begin();
            query.delete(qemp)
                    .where(qemp.empid.eq(207L)) // Long 은 숫자뒤에 L 붙여줌
                    .execute(); //execute() 실행하기
            tx.commit();


        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
