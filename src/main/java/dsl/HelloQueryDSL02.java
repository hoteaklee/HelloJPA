package dsl;

import com.querydsl.core.FilteredClause;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.*;
import model.QDepartment;
import model.QEmployee;
import org.apache.commons.lang.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class HelloQueryDSL02 {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            //쿼리 객체 준비
            QEmployee qemp = QEmployee.employee;
            QDepartment qdept = QDepartment.department;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 조회 - 전체사원
//            List<Employee> emps =  query.selectFrom(qemp).fetch();
//            for (Employee e : emps)
//                System.out.println(e);

            // 조회 - 일부 사원 , 페이징 (offset, limit)
//            List<Employee> emps =  query.selectFrom(qemp)
//                    .offset(30).limit(15).fetch();
//            for (Employee e : emps)
//                System.out.println(e);

            // 사원 데이터 조회 - select(컬럼명,...) : 이름, 부서번호, 입사일
//            List<Tuple> items = query.select(qemp.fname, qemp.detpid, qemp.hdate)
//                                    .from(qemp).fetch();
//            for (Tuple item : items)
//                System.out.println(item);

            // 정렬 : orderby , 부서번호 기준
//            query = new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).orderBy(qemp.detpid.desc()).fetch();
//
//            System.out.println(emps);

            // 조건 검색 : where - 직책인 IT_PROG인 사원 조회
//            query = new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();
//            for (Employee e : emps)
//                System.out.println(e);

            // 조건 검색: 연봉이 20000 이상인 사원 조회  ( 이상 - goe )
//            query =new JPAQueryFactory(em);
//            List<Employee> emps = query.selectFrom(qemp).where(qemp.sal.goe(14000)).fetch();
//            for (Employee e : emps)
//             System.out.println(e);

            // 직책수 조회 1
//            query= new JPAQueryFactory(em);
//            List<Long> cnt =  query.select(qemp.jobid.count()).from(qemp).fetch();
//
//            System.out.println(cnt);

            // 직책수 조회 2
//            query= new JPAQueryFactory(em);
//            Long cnt2 =  query.select(qemp.jobid).from(qemp).fetchCount();
//
//            System.out.println(cnt2);

            /// 직책수 조회 3
//            query= new JPAQueryFactory(em);
//            List<Long> cnt =  query.select(qemp.jobid.countDistinct() ).from(qemp).fetch();
//
//            System.out.println(cnt);

            /// 직책수 조회 4
//            query= new JPAQueryFactory(em);
//            Long cnt =  query.select(qemp.jobid ).distinct().from(qemp).fetchCount();
//
//            System.out.println(cnt);

            // 그룹핑 : 직책별 최대, 최소, 평균 연봉, 직책수 조회
//            query = new JPAQueryFactory(em);
//            List<Tuple> grouping = query.select(qemp.jobid, qemp.sal.max(), qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count())
//                    .from(qemp).groupBy(qemp.jobid).fetch();
//
//            for (Tuple g : grouping)
//             System.out.println(g);

            // 그룹핑 : 직책별 최대, 최소, 평균 연봉, 직책수  정렬 조회
//            StringPath jbcnt = Expressions.stringPath("jbcnt"); // 별칭정의
//            query = new JPAQueryFactory(em);
//            List<Tuple> grouping = query.select(
//                    qemp.jobid, qemp.sal.max(), qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count().as("jbcnt"))
//                    .from(qemp).groupBy(qemp.jobid)
//                    .orderBy( jbcnt.desc() ).fetch();
//
//            for (Tuple g : grouping)
//                System.out.println(g);

            // 서브쿼리 1 : 평균연봉보다 작게 받는 사원들 조회
//                query = new JPAQueryFactory(em);
//
//                // 서브쿼리 - 평균연봉은?
//                JPQLQuery<Double> subqry =  JPAExpressions.select( qemp.sal.avg()).from(qemp);
//
//                // 주쿼리 - 사원 조회
//                List<Employee> emp = query.selectFrom(qemp).where(qemp.sal.lt(subqry)).fetch(); // lt=작은
//
//                for (Employee e : emp)
//                    System.out.println(e);

            // 서브쿼리2 : IT 부서에 근무중인 사원들의 이름,직책, 급여 조회
//                query = new JPAQueryFactory(em);
//
//                // 서브 쿼리 - IT 부서 인 사원 조회
//                JPQLQuery<Long> subqry =  JPAExpressions
//                        .select(qdept.deptid).from(qdept).where(qdept.dname.eq("IT"));
//
//                // 주쿼리
//                List<Tuple> items =  query.select(qemp.fname, qemp.jobid, qemp.sal).from(qemp)
//                        .where(qemp.detpid.eq(subqry)).fetch();
//
//                System.out.println(items);


            // join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
//            query = new JPAQueryFactory(em);
//            List<Tuple> items =
//            query.select(qemp.fname, qemp.jobid, qdept.dname).from(qemp)
//                    .join(qemp.department, qdept).where(qemp.detpid.eq(60L)).fetch();   // 60L = new Long(60)
//
//            System.out.println(items);

            // 동적 쿼리
            // 직책이 IT_PROG 인 사원 조회
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = "Ste";
            String jobid = null;
            Integer sal = null;

            query = new JPAQueryFactory(em);
            List<Employee> emps = query.selectFrom(qemp)
                    .where(
                            (fname != null) ? qemp.fname.contains(fname) : null ,
                            StringUtils.isNotEmpty(jobid) ? qemp.jobid.eq(jobid):null ,
                            (sal != null) ? qemp.sal.gt(sal) : null
                    ).fetch();

            for (Employee e : emps)
              System.out.println(e);












        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }
}
