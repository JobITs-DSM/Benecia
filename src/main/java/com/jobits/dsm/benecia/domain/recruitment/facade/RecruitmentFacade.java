package com.jobits.dsm.benecia.domain.recruitment.facade;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.domain.recruitment.code.ScreeningProcessCode;
import com.jobits.dsm.benecia.domain.recruitment.code.TechnologyCode;
import com.jobits.dsm.benecia.domain.recruitment.code.WelfareCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringAreaRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguage;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguageRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcess;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcessRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.Tag;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.TagRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.Technology;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.TechnologyRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.Welfare;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.WelfareRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CreateRecruitmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final ProgrammingLanguageRepository programmingLanguageRepository;
    private final TagRepository tagRepository;
    private final ScreeningProcessRepository screeningProcessRepository;
    private final TechnologyRepository technologyRepository;
    private final WelfareRepository welfareRepository;
    private final HiringAreaRepository hiringAreaRepository;

    public void addProgrammingLanguage(ProgrammingLanguageCode code, Recruitment recruitment) {
        programmingLanguageRepository.save(ProgrammingLanguage.builder()
                .code(code)
                .recruitment(recruitment)
                .build());
    }

    public void addTag(String name, Recruitment recruitment) {
        tagRepository.save(Tag.builder()
                .name(name)
                .recruitment(recruitment)
                .build());
    }

    public void addScreeningProcess(ScreeningProcessCode code, Integer procedure, Recruitment recruitment) {
        screeningProcessRepository.save(ScreeningProcess.builder()
                .code(code)
                .gradations(procedure)
                .recruitment(recruitment)
                .build());
    }

    public void addTechnology(TechnologyCode code, Recruitment recruitment) {
        technologyRepository.save(Technology.builder()
                .code(code)
                .recruitment(recruitment)
                .build());
    }

    public void addWelfare(WelfareCode code, Recruitment recruitment) {
        welfareRepository.save(Welfare.builder()
                .code(code)
                .recruitment(recruitment)
                .build());
    }

    public void addHiringArea(CreateRecruitmentRequest.HiringArea hiringArea, Recruitment recruitment) {
        hiringAreaRepository.save(HiringArea.builder()
                .code(hiringArea.getCode())
                .task(hiringArea.getTask())
                .count(hiringArea.getCount())
                .recruitment(recruitment)
                .build());
    }
}
