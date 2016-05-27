package com.algaworks.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction txn = manager.getTransaction();
		boolean criador = false;

		try {
			if (!txn.isActive()) {
				txn.begin();
				txn.rollback();
				
				txn.begin();
				criador = true;
			}

			return context.proceed();
		
		} catch (Exception e) {
			if (txn != null && criador) {
				txn.rollback();
			}
			throw e;
		} finally {
			if (txn != null && txn.isActive() && criador) {
				txn.commit();
			}
		}
		
	}

}
