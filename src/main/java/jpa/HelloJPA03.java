package jpa;

import model.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class HelloJPA03 {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // 사원 데이터 조회 - TypedQuery
            // createQuery(질의문, 리턴될 객체종류)
            String jpql="select e from Employee as e";
            List<Employee> emps = em.createQuery(jpql, Employee.class).getResultList();
//            for (Employee emp : emps)
//                System.out.println(emp);

            // 사원 데이터 조회 - Query : 이름, 부서번호, 입사일
            // createQuery(질의문)
            jpql="select fname, detpid, hdate from Employee e";
            List<Object[]> items = em.createQuery(jpql).getResultList();
//            for (Object[] item : items)
//                System.out.println(item[0]+ "/" + item[1]+ "/"+ item[2]);

            // 사원 직책 조회 - jobid가  IT_PROG인 사원
            // 파라메터 바인딩 - :파라메터명, ?순번
            jpql="select e from Employee e where jobid = :jobid";      //이름 기반
            //jpql="select e from Employee e where jobid = ?1";         //위치 기반
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("jobid","IT_PROG");
            //query.setParameter("1","IT_PROG");
//            emps = query.getResultList();
//            for (Employees emp : emps)
//                  System.out.println(emp);

            // 사원 평균 연봉 조회 - TypedQuery
            jpql="select avg(sal) from Employee e ";
            Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();

            System.out.println(avgsal);

            //사원 직책수 조회
            jpql="select count (distinct jobid) from Employee e ";
            long count = em.createQuery(jpql, Long.class).getSingleResult();
            System.out.println(count);

            // jobid로 정렬후 3페이지 조회 :  페이징 (페이지당 출력건수 : 15)
            //setFirstResult(startpos)  : 페이징 시작 위치
            //setMaxResult(getdatacnt)  : 조회할 데이터 수
            jpql="select e from Employee e order by jobid";
             List<Employee> pemps = em.createQuery(jpql, Employee.class)
                     .setFirstResult(30).setMaxResults(15).getResultList();
            for (Employee emp : pemps)
                  System.out.println(emp);

            // 직책별 평균 연봉 , 사원수 조회
            jpql="select jobid, avg(sal) , count(jobid) from Employee e group by jobid";
            List<Object[]> jobavgs = em.createQuery(jpql).getResultList();
            for (Object[] jobavg: jobavgs)
                System.out.println(jobavg[0] + " / " + jobavg[1]+ " / " + jobavg[2]);

            // 사원이름, 직책, 부서명 조회 : join
            jpql="select e.fname,e.jobid, e.detpid,d.dname from Employee e  inner join e.department d";
            List<Object[]> joins = em.createQuery(jpql).getResultList();
            for (Object[] join: joins)
                System.out.println(join[0] + " / " + join[1]+ " / " + join[2]+ " / " + join[3]);

            // 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회 : join
            jpql="select e.fname,e.jobid,d.dname from Employee e " +
                    " inner join e.department d where e.detpid = 60 ";
            List<Object[]> join60s = em.createQuery(jpql).getResultList();
            for (Object[] join60: join60s)
                System.out.println(join60[0] + " / " + join60[1]+ " / " + join60[2]);

            // 부서명이 IT인 사원의 사번과 입사일 조회 : 서브쿼리
            jpql="select empid, hdate from Employee e where detpid = " +
                    "(select deptid from Department d where dname = 'IT') ";
            List<Object[]> ITs = em.createQuery(jpql).getResultList();
            for (Object[] it: ITs)
                System.out.println(it[0] + " / " + it[1]);

            // 제공된 이름, 직책, 연봉으로 사원 조회 : 동적 쿼리
            //  직책이 IT_PROG 인 사원 조회
            //  연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname=null;
            String jobid="IT_PROG";
            Integer sal =6000;  // null 체크를 위해서 클래스형으로 선언

            jpql = "select e from Employee e ";
            List<String> cndtns = new ArrayList<>();    // 조건절 저장 변수

            if (fname != null){
                cndtns.add(" fname like concat('%',:fanme,'%') ");
            }
            if (jobid != null){
                cndtns.add(" jobid = :jobid  ");
            }
            if (sal != null){
                cndtns.add(" sal >= :sal ");
            }
            if (!cndtns.isEmpty()){     // 조건식이 하나라도 존재한다면
                jpql += " where " + String.join(" and ", cndtns);
            }

            // 매개변수에 값 넣기
            TypedQuery<Employee> query1 = em.createQuery(jpql, Employee.class);
            if (fname != null){
                query1.setParameter("fname", fname);
            }
            if (jobid != null){
                query1.setParameter("jobid", jobid);
            }
            if (sal != null){
                query1.setParameter("sal", sal);
            }

            List<Employee> Employee = query1.getResultList();
            for (Employee e: Employee)
                System.out.println(e);


        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }
}
