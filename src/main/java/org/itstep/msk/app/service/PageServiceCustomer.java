package org.itstep.msk.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.PagingCustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class PageServiceCustomer {
    public static final int USER_PER_PAGE = 5;
    private final PagingCustomerRepo pagingCustomer;

    public Page<Customer> listByPage(int pageNum, @NotNull String sortField, String sorDir) {
        log.info("it will sort of page according incoming requests");
        Sort sort = Sort.by(sortField);
        sort = sorDir.equals("asd") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, USER_PER_PAGE, sort);
        return pagingCustomer.findAll(pageable);
    }
}
