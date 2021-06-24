package me.kwj1270.study.jpabasic;

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
		Order order = new Order();
		entityManager.persist(order);

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		entityManager.persist(orderItem);
	}

}
