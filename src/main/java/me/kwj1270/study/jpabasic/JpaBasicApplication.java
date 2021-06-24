package me.kwj1270.study.jpabasic;

import me.kwj1270.study.jpabasic.item.Book;
import me.kwj1270.study.jpabasic.order.Order;
import me.kwj1270.study.jpabasic.orderitem.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaBasicApplication {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();
			businessLogic(entityManager);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}

	private static void businessLogic(EntityManager entityManager) {
		Book book = new Book();
		book.setName("JPA");
		book.setAuthor("김우재");
		entityManager.persist(book);
	}

}
