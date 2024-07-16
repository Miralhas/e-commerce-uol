package github.com.miralhas.ecommerce_uol.infrastructure.repository.spec;

import github.com.miralhas.ecommerce_uol.api.dto.filter.OrderFilter;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class OrderSpec {

    public static Specification<SalesOrder> withFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.isMonthly()) {
                var currentYear = OffsetDateTime.now().getYear();
                var currentMonth = OffsetDateTime.now().getMonthValue();
                if (filter.getNumber() != null) currentMonth = filter.getNumber();
                var monthStart = OffsetDateTime.of(currentYear, currentMonth, 1, 0, 0, 0, 0, ZoneOffset.UTC);
                var monthEnd = monthStart.plusMonths(1).minusHours(1).plusMinutes(59);
                predicates.add(builder.between(root.get("createdAt"), monthStart, monthEnd));
                return builder.and(predicates.toArray(new Predicate[]{}));
            }

            if (filter.isWeekly()) {
                var weekStart = OffsetDateTime.now()
                        .toLocalDate()
                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                        .atStartOfDay()
                        .atOffset(ZoneOffset.UTC);
                var weekEnd = weekStart.plusWeeks(1).plusHours(23).plusMinutes(59);
                predicates.add(builder.between(root.get("createdAt"), weekStart, weekEnd));
                return builder.and(predicates.toArray(new Predicate[]{}));
            }

            if (filter.getCreatedAt() != null) {
                var date = filter.getCreatedAt().withHour(0).withMinute(0).withSecond(0).withNano(0);
                var untilDate = date.withHour(23).withMinute(59).withSecond(0).withNano(0);
                predicates.add(builder.between(root.get("createdAt"), date, untilDate));
                return builder.and(predicates.toArray(new Predicate[]{}));
            }

            if (filter.getCreatedAfter() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedAfter()));
            }

            if (filter.getCreatedBefore() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getCreatedBefore()));
            }

            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
