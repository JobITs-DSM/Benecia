package com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.QHiringArea.hiringArea;

@RequiredArgsConstructor
public class HiringAreaRepositoryCustomImpl implements HiringAreaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HiringAreaVO> getHiringInfoList(String receptionYear, Enterprise registrationNumber) {
        return queryFactory
                .select(new QHiringAreaVO(
                        hiringArea.id,
                        hiringArea.code,
                        hiringArea.count
                ))
                .from(hiringArea)
                .where(hiringArea.recruitment.recruitmentId.receptionYear.eq(receptionYear),
                        hiringArea.recruitment.registrationNumber.eq(registrationNumber))
                .fetch();
    }
}
