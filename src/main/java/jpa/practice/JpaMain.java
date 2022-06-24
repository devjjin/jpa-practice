package jpa.practice;

import jpa.practice.domain.Member;
import jpa.practice.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

            Member member = new Member();
            member.setUsername("memberA");
            member.setTeam(team);   // 주인인 곳에 값 입력해줘야한다!
            // team.getMembers().add(member);   // 주인이 아닌 곳에만 값 입력시 - FK가 NULL

            em.persist(member);

            // 순수 객체 상태를 고려해서 양방향으로 값 입력해주자

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for(Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
