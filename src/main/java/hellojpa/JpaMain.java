package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("myTeam");
            em.persist(team);

            Member member = new Member();
            member.setName("kwj1270");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println(findTeam.getName());

            Team newTeam = new Team();
            newTeam.setName("hello");
            em.persist(newTeam);

            findMember.setTeam(newTeam);
            Member findMember2 = em.find(Member.class, member.getId());
            System.out.println(findMember2.getTeam().getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
