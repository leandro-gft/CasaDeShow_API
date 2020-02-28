//package br.com.gft.casadeshowapi.repository.evento;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import br.com.gft.casadeshowapi.domain.Evento;
//import br.com.gft.casadeshowapi.repository.filter.EventoFilter;
//
//public class EventoRepositoryImpl implements EventoRepositoryQuery {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<Evento> filtrar(EventoFilter eventoFilter) {
//
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Evento> criteria = builder.createQuery(Evento.class);
//		Root<Evento> root = criteria.from(Evento.class);
//
//		// criar restricoes
//		Predicate[] predicates = criarRestricoes(eventoFilter, builder, root);
//		criteria.where(predicates);
//		TypedQuery<Evento> query = manager.createQuery(criteria);
//		return query.getResultList();
//	}
//
//	private Predicate[] criarRestricoes(EventoFilter eventoFilter, CriteriaBuilder builder, Root<Evento> root) {
//
//		List<Predicate> predicates = new ArrayList<>();
//		if (eventoFilter.getCasa().getNomeCasa() != null) {
//			predicates.add(builder.like(builder.lower(root.get("casa").get("nomeCasa")),
//					"%" + eventoFilter.getCasa().getNomeCasa().toLowerCase() + "%"));
//		}
//
//		if (eventoFilter.getDataDe() != null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("data"), eventoFilter.getDataDe()));
//
//		}
//
//		if (eventoFilter.getDataAte() != null) {
//			predicates.add(builder.lessThanOrEqualTo(root.get("data"), eventoFilter.getDataAte()));
//
//		}
//
//		return predicates.toArray(new Predicate[predicates.size()]);
//	}
//
//}
